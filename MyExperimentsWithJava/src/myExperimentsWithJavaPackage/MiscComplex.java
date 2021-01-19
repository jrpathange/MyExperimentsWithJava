package myExperimentsWithJavaPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class MiscComplex {
	
	// google quesiton - naive solution
	// array = [1,4,7,2,6,3] find pairs whose sum is 8
	static boolean hasPairWithGivenSumNaive(int[] array, int sum) {
		boolean result = false;
		
		// two nested loops to find the pair in naive way
		for(int i=0; i < array.length; i++)
		{
			for(int j=i+1; j <array.length; j++)
			{
				if(array[i]+array[j] == sum)
					return true;
			}
		}
		
		return result;
	}
	
	static boolean hasPairWithGivenSumLinear(int[] array, int sum) {
		boolean result = false;
		List<Integer> complArray = new ArrayList<Integer>();
		for(int item : array)
		{
			// first check if complement of sum exists in list
			if(complArray.contains(item))
				return true;
			// go ahead and add now
			complArray.add(sum-item);
		}
		
		return result;
	}
	
	// to know if two arrays has matching item/element
	static boolean hasCommonElementNaive(char[] array1, char[] array2) {
		
		// assumes arrays are of characters and case sensitive
		// brute/naive method of nested for loops to determine common item presence
		for(char c1 : array1)
		{
			for(char c2 : array2)
			{
				if(c1 == c2)
					return true;
			}
		}
		
		return false;
	}
	
	// to know if two arrays has matching item/element
	static boolean hasCommonElementLinear(char[] array1, char[] array2) {
				
		// assumes arrays are of characters and case sensitive
		// O(a+b) - Linear - improved from O(a*b)
		
		// initialize List from array1 characters after converting them to String
	    String array1string = new String(array1);
		List<Character> charlist = array1string.chars()
				.mapToObj((i) -> Character.valueOf((char)i))
				.collect(Collectors.toList());		
		
		// loop through 2nd array to find first array has the common element
		for(char c2 : array2)
		{
			if(charlist.contains(c2))
				return true;
		}
		
		return false;
	}

	
	// to find the max value of consecutive array elements abs difference
	static int cost(int[] B) {
        int maxVar = Math.abs(B[1] - 1);
        int maxOne = Math.abs(B[0] - 1);

        for (int i =2; i < B.length; i++) {
            int newMaxVar = Math.max(Math.abs(B[i] - 1) + maxOne,  
                Math.abs(B[i] - B[i-1]) + maxVar);
            int newMaxOne = Math.abs(B[i-1] - 1) + maxVar;
            maxVar = newMaxVar;
            maxOne = newMaxOne;
        }

        return Math.max(maxVar, maxOne);
	}
	
	
	// sample from lovealmgren in HackerRank
	public static long find_min_actions(int[] cookies) {

	    Arrays.sort(cookies);

	    // can be done in one loop of entire array by calculating for base 0, 1 and 2
	    // refer to Yo_Zhao1 in discussion
	    int minOps0 = 0, minOps1 = 0, minOps2 =0; // min ops with base 0, 1, 2
	    for(int i = 0; i < cookies.length; i++)
	    {
	    	int delta = cookies[i] - cookies[0] + 0; // 0 base
	    	minOps0 += delta/5 + (delta%5)/2 + ((delta%5)%2)/1;
	    	delta++;	// base 1
	    	minOps1 += delta/5 + (delta%5)/2 + ((delta%5)%2)/1;
	    	delta++;	// base 2
	    	minOps2 += delta/5 + (delta%5)/2 + ((delta%5)%2)/1;
	    }
	    
	    return Math.min(minOps0,  Math.min(minOps1, minOps2));
	    
	    
	 /*   long sum = Long.MAX_VALUE;

	    for(int base = 0; base < 3; base++) {
	        int current_sum = 0;
	        for(int i = 0; i < cookies.length; i++) {
	            int delta = cookies[i] - cookies[0] + base;
	            current_sum += (int)delta / 5 + delta % 5 / 2 + delta % 5 % 2 / 1;
	        }
	        sum = Math.min(current_sum,sum);
	    }

	    return sum;*/
	}
	
    // Complete the equal function below.
    static int equal(int[] arr) {
    	int numOfMinOps = 0;
    	int size = arr.length;
    	int diff = 0;
    	int min = 0;
    	int max = 0;
    	int subtractThis = 0;
    	
    	do
    	{
    		Arrays.sort(arr);
    		min = arr[0];
    		max = arr[size-1];
    		diff = max - min;
    		
            if(diff > 0)
            {
                if(diff >= 5)
                {
                    // add 5 to all but items equal to max
                    subtractThis = 5;
                }
                else if(diff >= 2)
                {
                    // add 2 to all but items equal to max  
                    subtractThis = 2; 
                }
                else
                {
                    // add 1 to all but items equal to max 
                    subtractThis = 1;
                }
                
                // perform subtraction from one chosen one - for this logic last item 
                arr[size-1] = max - subtractThis;
                
                // increment number of operations
                numOfMinOps++;
            }

    	}while(diff != 0);
    	
    	return numOfMinOps;
    }
    
	// Complete the equal function below.
/*    static int equal(int[] arr) {
        int size = 0, numOfOperations = 0;
        
        size = arr.length;
        
        int diff = 0;
        
        do
        {
            
        	int min = Integer.MAX_VALUE;
            int max = 0;
            int addThis = 0;
            Arrays.sort(arr);
            min = arr[0];
            max = arr[size-1];
                
            diff = max - min;
            if(diff > 0)
            {
                if(diff >= 5)
                {
                    // add 5 to all but items equal to max
                    addThis = 5;
                }
                else if(diff >= 2)
                {
                    // add 2 to all but items equal to max  
                    addThis = 2; 
                }
                else
                {
                    // add 1 to all but items equal to max 
                    addThis = 1;
                }
                
                // perform adding 
                for(int i=0; i< size-1; i++)
                {
                    int item = arr[i];
                	arr[i] = item + addThis;
                }
                
                // increment number of operations
                numOfOperations++;
            }
            
        }while (diff != 0);

        return numOfOperations;
    }
*/	
    // reverse the string
    private static String reverseTheString(String myStr) {
    	
    	int strLen = myStr.length();
    	char[] charStrArray = new char[strLen];
    	char[] strArray = myStr.toCharArray();
    	for(int i=0; i<strLen; i++)
    	{
    		charStrArray[i] = strArray[strLen-i-1];
    	}
    	
    	String newStr = new String(charStrArray);
    	return newStr;
    	
    	// using reverse function of StringBuffer
    	//StringBuffer reversedStr = new StringBuffer(myStr);
    	//reversedStr.reverse();
    	//return reversedStr.toString();
    }
    
    // To merge two sorted arrays allowing duplicates
    private static ArrayList<Integer> mergeSortedArrays(ArrayList<Integer> array1,
    		ArrayList<Integer> array2) {
    	
    	ArrayList<Integer> merged = new ArrayList<Integer>();
    	int array1Size = array1.size();
    	int array2Size = array2.size();
    	if(array1Size == 0)
    		return array2;
    	if(array2Size == 0)
			return array1;
    	
    	int array1Index = 0;
    	int array2Index = 0;
    	
    	// this is O(a+b) complexity that is linear
    	do
    	{
    		
    		int item1 = Integer.MAX_VALUE; // to allow to loop through other array
    		int item2 = Integer.MAX_VALUE; // to allow to loop through other array
    		if(array1Index < array1Size)
    			item1 = array1.get(array1Index);
    		if(array2Index < array2Size)
    			item2 = array2.get(array2Index);
    		
			//allow duplicates, add from array1 till <=, else add from array2
    		if(item1 <= item2)
    		{
    			merged.add(item1);
    			array1Index++;
    		}
    		else
    		{
    			merged.add(item2);
    			array2Index++;
    		}
    		
    	}while((array1Index < array1Size) || (array2Index < array2Size));
    	
    	return merged;
    }
    
    private static int printMaxSubarray(ArrayList<Integer> array) {
    	int startIndexLastBigSum = 0;
    	int endIndexLastBigSum = 0;
    	int endIndex = 0;
    	int lastBigSum = Integer.MIN_VALUE;
    	int currentSum = 0;
    	int arraySize = array.size();
    	int maxElement = Integer.MIN_VALUE;
    	
    	if(arraySize == 0)
    		return 0;
    	else if(arraySize == 1)
    	{
    		return array.get(0);
    	}
    	
    	//int[] array1 = null;
    	//maxElement = Arrays.stream(array1).max().getAsInt();
    	
    	ArrayList<Integer> sorted = new ArrayList<Integer>(array);
    	sorted.sort(null);
    	maxElement = sorted.get(arraySize - 1);
    	
    	// complexity O(n^2)
    	for(int i=0; i<arraySize; i++)
    	{
    		currentSum = array.get(i);
    		for(int j=i+1; j<arraySize; j++)
    		{
    			currentSum += array.get(j);
    			if(currentSum > maxElement)
    			{
    				if(currentSum > lastBigSum)
    				{
    					lastBigSum = currentSum;
    					startIndexLastBigSum = i;
    					endIndexLastBigSum = j;
    				}
    			}
    			
    		}
    	}
    	
    	if(lastBigSum > maxElement)
    	{
    		// print explanation for debug purposes
	    	for(int i=startIndexLastBigSum; i<=endIndexLastBigSum; i++)
	    	{
	    		System.out.print(array.get(i) + " ");
	    	}
	    	System.out.print('\n');
	    	// return the sum
	    	return(lastBigSum);
    	}
    	else
    	{
    		return maxElement;
    	}
    }
    
    private static int firstRecurringItem(int[] array) {
    	int recItem = 0;
    	
    	// naive - two nested for loops with time complexity of O(n^2)
    	// instead let's use Map (Hash Table) to reduce the complexity to O(n)
    	// first try to find the key (one array item at a time) and 
    	// if not found add the key
    	Map<Integer, String> hashMap = new HashMap<Integer, String>();
    
    	// just loop once the array to find the first recurring item
    	for(int item : array)
    	{
    		if(hashMap.get(item) != null)
    		{
    			return item;
    		}
    		else
    		{
    			hashMap.put(item, "true");
    		}
    	}
    	
    	return recItem;
    }
    
    private static final Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		// first recurring Item in an array
		int[] array = {1,2,3,4,5,6,9,-1,-2,-3,10,5,1,2,3,4,5}; //{2,5,1,2,3,5,1,2,4};
		int recItem = firstRecurringItem(array);
		System.out.println(recItem);
		
		
		/*
		ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(
				-2,1,-3,4,-1,2,1,-5,4));
		//		2,-1,-2,1,3));
		int sum = printMaxSubarray(array);
		System.out.println(sum);
		
		
	
		int i=0;
		ArrayList<Integer> array1 = new ArrayList<Integer>(Arrays.asList(0,3,4,31,33));
		ArrayList<Integer> array2 = new ArrayList<Integer>(Arrays.asList(4,6,30,33,44,55));
		
		ArrayList<Integer> merged = new ArrayList<Integer>();
		
		merged = mergeSortedArrays(array1, array2);
		System.out.println(merged);
		
		
		
		
		String myStr = "Am I Robot!";
		String reversed = new String(reverseTheString(myStr));
		System.out.println(reversed);
		
		int[] array = {1,4,7,2,6,3};
		boolean result = hasPairWithGivenSumLinear(array, 6);
		System.out.println(result);
		
		
		char[] array1 = {'a', 's', 'w', 'x', 't'};
		char[] array2 = {'c', 'd', 'u', 'z'};
		
		//boolean hasCommon = hasCommonElementNaive(array1, array2);
		boolean hasCommon = hasCommonElementLinear(array1, array2);
		System.out.println(hasCommon);
		
		
		System.out.println("Input data");

		Scanner in = new Scanner(System.in);
	    int n = in.nextInt();
	    while(n-->0) {
	        int m = in.nextInt();
	        int cookies[] = new int[m];
	        for(int cookie_i=0; cookie_i < m; cookie_i++){
	            cookies[cookie_i] = in.nextInt();
	        }
	        System.out.println(find_min_actions(cookies));
	    }*/
		
	       //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
/*
	        int t = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        for (int tItr = 0; tItr < t; tItr++) {
	            int n = scanner.nextInt();
	            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	            int[] arr = new int[n];

	            String[] arrItems = scanner.nextLine().split(" ");
	            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	            for (int i = 0; i < n; i++) {
	                int arrItem = Integer.parseInt(arrItems[i]);
	                arr[i] = arrItem;
	            }

	            int result = equal(arr);

	            //bufferedWriter.write(String.valueOf(result));
	            //bufferedWriter.newLine();
	            System.out.println(result);
	        }

	        //bufferedWriter.close();

	        scanner.close();*/
	    }
	
	}


