package Day8Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

	private String dateiname;
	private String way;	//Instructions, where to go now
	public int step;
	private ArrayList<Node> allNodes = new ArrayList<Node>();
	
	public String startingPosition ="AAA";
	public String currentPosition;
	public String destination = "ZZZ";
	public int nextDirectionNr;
	
	public Parser(String dateiname) {//i.o.
		this.dateiname = dateiname;
	}

	public void parseFile() throws FileNotFoundException {
		File textfile = new File(dateiname);
		Scanner scanner = new Scanner (textfile);
		
		int lineNumber = 1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ( lineNumber == 1) {
				setWay(line); 
			} else {
				if( lineNumber > 2)
				parseLine(line);
			}
			lineNumber++;
		}
		scanner.close();
	}

	private void parseLine(String line) {//i.O.
		//line: AAA = (BBB, BBB)
		String id = line.split(" = ")[0]; 	//i.O.
				
		//conns:(BBB, CCC)
		String conn = line.split(" = ")[1].strip(); //i.O.
		String [] conns = new String [2];
		conns[0] = conn.substring(1, 4);
		conns[1] = conn.substring(6,9);
		
		Node n = new Node (id, conns);
		
		allNodes.add(n);
	}
	

	private void setWay(String line) {
		this.way = line; 
	}

	public void findResult() {
		System.out.println("Way: " + way);
		System.out.println("Länge: " + way.length());
		
		System.out.println("Anzahl Nodes: " + allNodes.size());
		System.out.println();
		
		findWayFromAToZ();
		printResult(); //how many steps are required from AAA to ZZZ?
	}

	private void printResult() {//i.o.
		System.out.println("Anzahl der benötigten Schritte: " + step);
	}

	private void findWayFromAToZ() {
		step =0;
		currentPosition = new String(startingPosition);
		do {
			try {
				currentPosition = findNextPosition(currentPosition, destination);
			} catch (Exception e) {
				e.printStackTrace();
			}
				step++;
		} while (!currentPosition.equals(destination));
	}

	private String findNextPosition(String currentPosition, String destination) {//i.o.
		nextDirectionNr = findNextDirection(); 		
		String nextPosition;
		
		for (int j = 0; j<allNodes.size(); j++) {
			if (allNodes.get(j).getId().contains(currentPosition)) {
				if (Character.compare(way.charAt(nextDirectionNr), 'L')==0) { //'L' means left: conn[0]
					nextPosition = allNodes.get(j).getConn()[0];
					return nextPosition;
				} else { //('R') --> 'R' means right: conn[1] 
					nextPosition = allNodes.get(j).getConn()[1];
					return nextPosition;
				}
			}
		} 
		return null;
	}
	
	private int findNextDirection() {
		if (step < way.length()) {
			return step;
		}
		else return step % way.length();
	}
}