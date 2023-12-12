
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.regex.*;


public class day01 {
	// converts "word" digit string into a digit
	static String wordToDigit(String word) {
		switch(word) {
			case "one": return "1";
			case "two": return "2";
			case "three": return "3";
			case "four": return "4"; 
			case "five": return "5";
			case "six": return "6";
			case "seven": return "7";
			case "eight": return "8";
			case "nine": return "9";
		}
		return word;
	}
	
	// tests if a string consists of digits
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		// create a relative path to an input
		String inputPath = new File("").getAbsolutePath();
		inputPath = inputPath.concat("\\input\\day01.txt");
		
		// make a handle and read lines
		BufferedReader reader;
		try {
			int score = 0;
			reader = new BufferedReader(new FileReader(inputPath));
			String line = reader.readLine();
			// regex to find digits/words digits
			Pattern pattern = Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine");
			// read all lines
			while (line != null) {
				Matcher matcher = pattern.matcher(line);
				matcher.find();
				// fist match is the first number
				String first = matcher.group();
				String last = matcher.group();
				int start = 0;
				// find last match, while manually stepping by one index to not miss the overlaps
				while (matcher.find(start)) {
					last = matcher.group();
					start = matcher.start() + 1;
				}
				// convert words to digits and integers
				first = wordToDigit(first);
				last = wordToDigit(last);
				int number = Integer.valueOf(first.concat(last));
				// increment the score and read the next line
				score += number;
				line = reader.readLine();
			}
			System.out.println(score);
			reader.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
}
