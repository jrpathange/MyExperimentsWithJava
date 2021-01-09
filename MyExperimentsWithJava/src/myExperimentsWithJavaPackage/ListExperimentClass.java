package myExperimentsWithJavaPackage;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class ListExperimentClass {
	
	private static int countTallCandles(List<Integer> candles) {
		int countOfTallCandles = 0, candleMaxHeight=0, firstIndexOfTall = 0;
		int size=0;
		
		size = candles.size();
		Collections.sort(candles);
		candleMaxHeight = candles.get(size-1);
		firstIndexOfTall = candles.indexOf(candleMaxHeight);
		countOfTallCandles = size - firstIndexOfTall;
		
		return countOfTallCandles;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// declare buffer reader to get list size and list items user input
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		//int candlesCount = Integer.parseInt(bufferedReader.readLine().trim());
		System.out.println("Input array of integers seperated by space:");
		List<Integer> candles = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt)
				.collect(toList());
		
		int countOfTallCandles = countTallCandles(candles);
		System.out.println(countOfTallCandles);

	}

}
