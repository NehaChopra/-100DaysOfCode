/*
 * https://www.geeksforgeeks.org/binary-search/
 */
package DataStructure;

import java.util.Scanner;

public class BinarySearch {
	public static void main(String[] args) {
//		int arr[] = { 2, 3, 4, 10, 40 };
//		System.out.println(binarySearch(arr, 0, (arr.length-1), 10));
        Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		for(int test = 0; test<testCases; test++){
			int arrayLength = input.nextInt();
			int array[] = new int[arrayLength];
			int element = input.nextInt();
			for(int index=0; index<arrayLength; index++){
				array[index] = input.nextInt();
			}
			System.out.println(binarySearch(array,0,(array.length-1),element));
		}
	}

	public static int binarySearch(int[] array, int start, int end, int ele) {
		if (start <= end) {
			int midIndex = start + (end - start) / 2;
			if (array[midIndex] == ele) {
				return midIndex;
			}
			if (array[midIndex] > ele) {
				return binarySearch(array, start, (midIndex - 1), ele);
			}
			if (array[midIndex] < ele) {
				return binarySearch(array, (midIndex + 1), end, ele);
			}
		}
		return -1;
	}
}
