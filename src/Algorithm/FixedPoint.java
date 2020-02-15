/*
 * https://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
 */
package Algorithm;

public class FixedPoint {
	public static void main(String[] args) {
		int arr[] = {-10, -5, 3, 4, 7, 9};
		System.out.println(binarySearch(arr, 0, (arr.length-1), -1));
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
