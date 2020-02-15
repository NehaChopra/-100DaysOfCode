/*
 * https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/
 */
package DataStructure;

public class OccurrenceInSortedArray {
	public static void main(String[] args) {
//		int arr[] = { 1, 2, 2, 3, 3, 3, 3 };
//		int arr[] = {1, 1, 2, 2, 2, 2, 3};
		int arr[] = {62, 140, 152, 178, 411, 456, 470};
		System.out.println(occurences(arr));
	}

	public static int occurences(int[] array) {
		int start = findFirstUsingbinarySearch(array, 0, array.length - 1, 114);
		if (start == -1) {
			return -1;
		}
		int end = findLastUsingbinarySearch(array, start, array.length - 1, 114);
		if (end == -1) {
			return -1;
		}
		return (end - start) + 1;
	}

	public static int findFirstUsingbinarySearch(int[] array, int start, int end, int ele) {
		if (start <= end) {
			int midIndex = start + (end - start) / 2;
			if (midIndex == 0 || ((array[midIndex] == ele) && (array[midIndex - 1] < ele))) {
				return midIndex;
			}
			if (array[midIndex] < ele) {
				return findFirstUsingbinarySearch(array, (midIndex + 1), end, ele);
			} else {
				return findFirstUsingbinarySearch(array, start, (midIndex - 1), ele);
			}
		}
		return -1;
	}

	public static int findLastUsingbinarySearch(int[] array, int start, int end, int ele) {
		if (start <= end) {
			int midIndex = start + (end - start) / 2;
			if ((midIndex == array.length - 1) || ((array[midIndex] == ele) && (array[midIndex + 1] > ele))) {
				return midIndex;
			}
			if (array[midIndex] > ele) {
				return findLastUsingbinarySearch(array, start, (midIndex - 1), ele);
			} else {
				return findLastUsingbinarySearch(array, (midIndex + 1), end, ele);
			}
		}
		return -1;
	}
}
