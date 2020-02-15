/*
 * https://www.geeksforgeeks.org/ceiling-in-a-sorted-array/
 */
package Algorithm;

public class CeilingInSortedArray {
	public static void main(String[] args) {
		int arr[] = {1, 2, 8, 10, 10, 12, 19}; ;
//		System.out.println(ceilBinarySearch(arr, 0, arr.length-1, 0));
//		System.out.println(ceilBinarySearch(arr, 0, arr.length-1, 1));
//		System.out.println(ceilBinarySearch(arr, 0, arr.length-1, 5));
//		System.out.println(ceilBinarySearch(arr, 0, arr.length-1, 20));
		System.out.println(ceilBinarySearch(arr, 0, arr.length-1, 12));
	}
	
	public static int ceilBinarySearch(int array[], int start, int end, int element) {
		if(element <= array[start]) {
			return start;
		}
		if(element > array[end]) {
			return -1;
		}
		int midIndex = (start + (end - start) / 2) ;
		if(element == array[midIndex]) {
			return midIndex;
		}
		if(element > array[midIndex]) {
			if((midIndex+1 <= end) && (array[midIndex+1] > element) && (array[midIndex] < element)) {
				return (midIndex+1);
			}else {
				return ceilBinarySearch(array, midIndex+1, end, element);
			}
		}else {
			if((midIndex-1 >= start) && (array[midIndex-1] < element) && (array[midIndex] > element)) {
				return (midIndex);
			}else {
				return ceilBinarySearch(array, start, midIndex - 1, element);
			}
		}
	}
}
