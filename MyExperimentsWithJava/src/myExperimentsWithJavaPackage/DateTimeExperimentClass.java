package myExperimentsWithJavaPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateTimeExperimentClass {

	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Time in 12 hour format 08:45:15AM");
		String dateStr = scanner.nextLine();
		
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("hh:mm:ssaa");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		
		try {
			date = inputDateFormat.parse(dateStr);
		} catch(ParseException pe) {
			pe.printStackTrace();
		}
		
		System.out.println(outputDateFormat.format(date));

	}

}
