//https://www.geeksforgeeks.org/find-union-and-intersection-of-two-unsorted-arrays/
package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionIntersectionUnsorted {
	public static void heapSort(int[] inputArray) {
		for (int index = inputArray.length / 2 - 1; index >= 0; index--) {
			heapify(index, inputArray.length, inputArray);
		}

		for (int index = inputArray.length - 1; index >= 0; index--) {
			swap(0, index, inputArray);
			heapify(0, index, inputArray);
		}
	}

	public static void heapify(int index, int end, int[] inputArray) {
		int largest = index;
		int left = leftChild(index);
		int right = rightChild(index);
		if (left < end && inputArray[left] > inputArray[largest]) {
			largest = left;
		}
		if (right < end && inputArray[right] > inputArray[largest]) {
			largest = right;
		}
		if (largest != index) {
			swap(index, largest, inputArray);
			heapify(largest, end, inputArray);
		}
	}

	public static int leftChild(int index) {
		return ((2 * index) + 1);
	}

	public static int rightChild(int index) {
		return ((2 * index) + 2);
	}

	public static void swap(int iIndex, int jIndex, int[] inputArray) {
		int temp = inputArray[iIndex];
		inputArray[iIndex] = inputArray[jIndex];
		inputArray[jIndex] = temp;
	}

	public static int[] union(int[] inputArray, int[] inputArray1) {
		int fArray[] = new int[inputArray.length + inputArray1.length];
		int jindex = 0;
		for (int index = 0; index < inputArray.length; index++) {
			fArray[jindex++] = inputArray[index];
		}
		for (int index = 0; index < inputArray1.length; index++) {
			fArray[jindex++] = inputArray1[index];
		}
		heapSort(fArray);
		List<Integer> uList = new ArrayList<>();
		List<Integer> iList = new ArrayList<>();
		for (int index = 0; index < fArray.length - 1; index++) {
			if (fArray[index] != fArray[index + 1]) {
				uList.add(fArray[index]);
				if (index + 1 == fArray.length - 1) {
					uList.add(fArray[index + 1]);
				}
			} else {
				iList.add(fArray[index]);
			}
		}
		System.out.println(Arrays.deepToString(uList.toArray()));
		System.out.println(Arrays.deepToString(iList.toArray()));
		return fArray;
	}

	public static void intersection(int[] inputArray, int[] inputArray1) {

	}

	public static void main(String args[]) {
		int arr1[] = { 7, 1, 5, 2, 3, 6 };
		int arr2[] = { 3, 8, 6, 20, 7 };
		int[] uArray = union(arr1, arr2);
		for (int index = 0; index < uArray.length; index++) {
			System.out.print(uArray[index] + " ");
		}
	}
}
