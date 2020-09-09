//https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
package Algorithm;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class BoyerMooreAlgorithm {
	public static void main(String[] args) throws Exception {
		// parsing a CSV file into Scanner class constructor
		StringBuilder str = new StringBuilder("");
		Scanner sc = new Scanner(
				new File("/home/nehachopra/Downloads/Unsampled data 5 to 13 Report segment 2 - part 2 (1).csv"));
		sc.useDelimiter(","); // sets the delimiter pattern
		while (sc.hasNext()) // returns a boolean value
		{
			String strg = sc.next();
			if(strg != "") {
//				System.out.println("'" + sc.next() + "',");
				str.append("'" + sc.next().trim() + "',");
			}
		}
		System.out.print(str.toString());
		sc.close();
		
		PrintWriter pw = new PrintWriter(new File("/home/nehachopra/Downloads/device.csv"));
		pw.append(str.toString());
		pw.close();
	}
}


