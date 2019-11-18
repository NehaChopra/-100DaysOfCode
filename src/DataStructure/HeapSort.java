/*
 * https://practice.geeksforgeeks.org/problems/heap-sort/1
 * Input:
2
5
4 1 3 9 7
10
10 9 8 7 6 5 4 3 2 1

Output:
1 3 4 7 9
1 2 3 4 5 6 7 8 9 10
 */
package DataStructure;

import java.util.Scanner;

public class HeapSort {
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

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int testCasesNumber = input.nextInt();
		for (int testCases = 0; testCases < testCasesNumber; testCases++) {
			int arrayLength = input.nextInt();
			int[] array = new int[arrayLength];
			for (int index = 0; index < arrayLength; index++) {
				array[index] = input.nextInt();
			}
			HeapSort obj = new HeapSort();
			obj.heapSort(array);
			for (int element : array) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}
}

