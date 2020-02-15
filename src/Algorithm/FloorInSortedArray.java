/*
 *https://www.geeksforgeeks.org/floor-in-a-sorted-array/
 */
package Algorithm;

public class FloorInSortedArray {
	public static void main(String[] args) {
		int arr[] = {1, 2, 8, 10, 10, 12, 19}; 
//		System.out.println(floorBinarySearch(arr, 0, arr.length-1, 5));
//		System.out.println(floorBinarySearch(arr, 0, arr.length-1, 20));
		System.out.println(floorBinarySearch(arr, 0, arr.length-1, 0));
	}
	
	public static int floorBinarySearch(int array[], int start, int end, int element) {
		if(element < array[start]) {
			return -1;
		}
		if(element >= array[end]) {
			return end;
		}
		int midIndex = (start + (end - start) / 2) ;
		if(element == array[midIndex]) {
			return midIndex;
		}
		if(element > array[midIndex]) {
			if((midIndex >= 0) && (array[midIndex] <= element) && (array[midIndex + 1] > element)) {
				return (midIndex);
			}else {
				return floorBinarySearch(array, midIndex+1, end, element);
			}
		}else {
			if((midIndex >= 0) && (array[midIndex-1] <= element) && (array[midIndex] > element)) {
				return (midIndex-1);
			}else {
				return floorBinarySearch(array, start, midIndex-1, element);
			}
		}
	}
}
