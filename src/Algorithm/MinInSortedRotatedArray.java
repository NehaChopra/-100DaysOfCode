/*
 * https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
 */
package Algorithm;

public class MinInSortedRotatedArray {
	public static void main(String[] args) {
//		 int arr[] =  {5, 6, 1, 2, 3, 4}; 
//		 int arr[] =  {1, 2, 3, 4};
//		int arr[] =  {1};
//		int arr[] =  {1,2};
//		int arr[] =  {2, 1};
//		int arr[] =  {5, 6, 7, 1, 2, 3, 4}; 
//		int arr[] =  {1, 2, 3, 4, 5, 6, 7}; 
//		int arr[] =  {2, 3, 4, 5, 6, 7, 8, 1};
		int arr[] = { 3, 4, 5, 1, 2 };
		System.out.println(minBinarySearch(arr, 0, arr.length - 1));
	}

	public static int minBinarySearch(int[] array, int start, int end) {
		if(start > end) {
			return array[start];
		}
		if (start <= end) {
			int midIndex = (start + (end - start) / 2);
			if (start == end) {
				return array[start];
			}
			if (midIndex > start && midIndex < end && array[midIndex] < array[midIndex + 1]
					&& array[midIndex] < array[midIndex-1]) {
				return array[midIndex];
			}
			if (array[midIndex] < array[end])
				return minBinarySearch(array, start, midIndex - 1);

			return minBinarySearch(array, midIndex + 1, end);
		}
		return -1;
	}
}
