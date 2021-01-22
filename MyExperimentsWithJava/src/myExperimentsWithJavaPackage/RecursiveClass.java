package myExperimentsWithJavaPackage;

import java.util.*;

public class RecursiveClass {

	// testing after MFAt
	// recursive print method that prints spaces and hashes that is supplied as str
	private static void printRecursively(int n, String str) {
		
		if(n > 0)
		{
			System.out.print(str);
			printRecursively(n-1, str);
		}
		
	}
	// recursive call method that calls recurive print method to print spaces and hashes
	private static void callPrintRecursively(int n, int stairsCount) {
		int spacesCount, hashesCount;
		if(n < stairsCount)
		{
			spacesCount = stairsCount - n -1;
			hashesCount = n + 1;
			
			printRecursively(spacesCount, " ");
			printRecursively(hashesCount, "#");
			System.out.print("\n");
			callPrintRecursively(n+1, stairsCount);
		}
	}
	
	// staircase method
	private static void staircase(int n) {
		//recursive method to call two more recursive functions to print spaces and hashes
		callPrintRecursively(0,n);
	}
	
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// user input
		System.out.println("Number of staircases you would like to see");
		int n = scanner.nextInt();
		
		//to skip new line, paragraph end, etc
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
		staircase(n);
		
		scanner.close();
		
	}

}
