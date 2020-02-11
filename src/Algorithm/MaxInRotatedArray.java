/*
 * https://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
 */
package Algorithm;

public class MaxInRotatedArray {
	public static void main(String[] args) {
//		int arr[] = { 1, 3, 50, 10, 9, 7, 6 };
//		int arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
//		int arr[] = {10, 20, 30, 40, 50};
		int arr[] = {120, 100, 80, 20, 0};
		System.out.println(binarySearchFindMax(arr, 0, arr.length - 1));
	}

	public static int binarySearchFindMax(int[] array, int start, int end) {
		if(start == end) {
			return array[start];
		}
		if((start+1 == end)) {
			if((array[start] > array[end])) {
				return array[start];
			}
			if((array[start] < array[end])) {
				return array[end];
			}
		}
		int mid = (start + (end - start) / 2);
		if(array[mid] > array[mid + 1] && array[mid] > array[mid - 1]) {
			return array[mid];
		}
		if((array[mid] < array[mid + 1]) && (array[mid] > array[mid - 1])) {
			return binarySearchFindMax(array, mid + 1, end);
		}else {
			return binarySearchFindMax(array, start, mid - 1);
		}
	}
}
