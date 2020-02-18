/*
 * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
 */
package Algorithm;

public class PeakInArray {
	public static void main(String[] args) {
//		int arr [] = {5, 10, 20, 15};
//		int arr [] = {10, 20, 15, 2, 23, 90, 67};
//		int arr [] = {10, 20, 30, 40, 50};
		int arr [] = {100, 80, 60, 50, 20};
		System.out.println(peakBinarySearch(arr, 0 , arr.length -1));
	}

	public static int peakBinarySearch(int[] array, int start, int end) {
		if(start <= end) {
			int midIndex = start + (end - start) / 2;
			if(midIndex==0 && (array[midIndex] > array[midIndex+1])) {
				return array[midIndex];
			}
			if((midIndex==array.length-1) && (array[midIndex] > array[midIndex-1])) {
				return array[midIndex];
			}
			if((array[midIndex-1] < array[midIndex]) && (array[midIndex] > array[midIndex+1])) {
				return array[midIndex];
			}
			if(array[midIndex] < array[midIndex+1]) {
				return peakBinarySearch(array, midIndex+1, end);
			}else {
				return peakBinarySearch(array, start, midIndex-1);
			}
		}
		return -1;
	}
}
