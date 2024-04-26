package Day8Part1;
/**
 * Problem description: https://adventofcode.com/2023/day/8
 * 
 */
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		Parser p = new Parser("Input08.txt");
		try {
			p.parseFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		p.findResult();
	}

}
