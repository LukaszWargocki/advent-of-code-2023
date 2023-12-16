import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.regex.*;


public class day02 {
	final static int RED = 12;
	final static int GREEN = 13;
	final static int BLUE = 14;
	
	public static int mostBalls(String line, Pattern ballPattern) {
		Matcher matcher = ballPattern.matcher(line);
		int maxBalls = 0;
		while (matcher.find()) {
			maxBalls = Math.max(maxBalls, Integer.parseInt(matcher.group().split(" ")[0]));
		}
		return maxBalls;
	}
	
	public static void main(String[] args) {
		String inputPath = new File("").getAbsolutePath().concat("\\input\\day02.txt");
		System.out.println(inputPath);
		
		BufferedReader reader;
		try {
			int result1 = 0;
			reader = new BufferedReader(new FileReader(inputPath));
			String line = reader.readLine();
			Pattern numberPattern = Pattern.compile("\\d+");
			Pattern bluePattern = Pattern.compile("\\d+ blue");
			Pattern redPattern = Pattern.compile("\\d+ red");
			Pattern greenPattern = Pattern.compile("\\d+ green");
			int sumOfPowers = 0;
			while (line != null) {
				System.out.println(line);
				String balls = line.split(":")[1];
				int mostRed = mostBalls(balls, redPattern);
				int mostBlue = mostBalls(balls, bluePattern);
				int mostGreen = mostBalls(balls, greenPattern);
				sumOfPowers += mostRed * mostBlue * mostGreen;
				if (mostRed > RED) {
					line = reader.readLine();
					continue;
				} else if (mostBlue > BLUE) {
					line = reader.readLine();
					continue;
				} else if (mostGreen > GREEN) {
					line = reader.readLine();
					continue;
				} else {
					String game = line.split(":")[0];
					Matcher gameMatcher = numberPattern.matcher(game);
					gameMatcher.find();
					int gameId = Integer.valueOf(gameMatcher.group());
					result1 += gameId;
					line = reader.readLine();
				}
			}
			reader.close();
			
			System.out.print("First part result: " + result1 + '\n');
			System.out.print("Second part result: " + sumOfPowers + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
