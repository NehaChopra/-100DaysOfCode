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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HeapSort {
	public static void heapSort(Integer[] inputArray) {
		for (int index = inputArray.length / 2 - 1; index >= 0; index--) {
			heapify(index, inputArray.length, inputArray);
		}

		for (int index = inputArray.length - 1; index >= 0; index--) {
			swap(0, index, inputArray);
			heapify(0, index, inputArray);
		}
	}

	public static void heapify(int index, int end, Integer[] inputArray) {
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

	public static void swap(int iIndex, int jIndex, Integer[] inputArray) {
		int temp = inputArray[iIndex];
		inputArray[iIndex] = inputArray[jIndex];
		inputArray[jIndex] = temp;
	}

	public static int findMissingPositive(int A[], int n) {
		boolean[] present = new boolean[n + 1];

		for (int i = 0; i < n; i++) {

			if (A[i] > 0 && A[i] <= n)
				present[A[i]] = true;
		}

		for (int i = 1; i <= n; i++)
			if (!present[i])
				return i;

		return n + 1;
	}

	public static int solution(String S) {
		int cres = -1;
		int res = -1;
		String str = S;
		if (str == null || str.length() == 0) {
			return res;
		}
		if (str.length() > 3) {
			for (int index = 0; index < str.length() - 2; index = index + 1) {
				for (int jindex = index + 2; jindex < str.length() - 1; jindex = jindex + 1) {
					if (str.charAt(index) == str.charAt(jindex) && str.charAt(index + 1) == str.charAt(jindex + 1))
						cres = Math.max(cres, Math.abs(jindex - index));
//					if (res < cres) {
//						res = cres;
//					}
				}
			}
		} else {
			for (int index = 0; index < str.length() - 1; index = index + 1) {
				for (int jindex = index + 1; jindex < str.length() - 1; jindex = jindex + 1) {
					if (str.charAt(index) == str.charAt(jindex))
						cres = Math.max(cres, Math.abs(jindex - index));
//					if (res < cres) {
//						res = cres;
//					}
				}
			}
		}
		return res;
	}

	public static int deleteProducts(List<Integer> ids, int m) {
		// Write your code here
		if (ids.size() < 0 || m == 0) {
			return 0;
		}
		HashMap<Integer, Integer> hmap = new HashMap<>();
		for (int index = 0; index < ids.size(); index++) {
			if (hmap.containsKey(ids.get(index)))
				hmap.put(ids.get(index), hmap.get(ids.get(index)) + 1);
			else
				hmap.put(ids.get(index), 1);
		}
		Integer[] array = new Integer[hmap.size()];
		int index = 0;
		for (Integer val : hmap.keySet()) {
			array[index] = hmap.get(val);
			index++;
		}

		heapSort(array);
		int finalSize = 0;
		for (int i = 0; i < array.length && m > 0; i++) {
			m = m - array[i];
			if (m >= 0)
				finalSize++;
		}
		return array.length - finalSize;
	}

	public static int findMaxSubarraySum(int arr[], int threshold) {
//		if (arr.size() <= 0 || threshold == 0) {
//			return 0;
//		}

		int sum = arr[0];
		int maxSum = 0;
		int jIndex = 0;

		for (int index = 1; index < arr.length - 1; index++) {

			if (sum <= threshold)
				maxSum = Math.max(maxSum, sum);

			while (sum + arr[index] >= threshold && jIndex < index) {
				sum -= arr[jIndex];
				jIndex++;
			}

			for (int mindex = 0; mindex < jIndex; mindex++) {
				sum += arr[mindex];
			}
			sum += arr[index];
		}

		if (sum <= threshold) {
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}

//	public static List<String> deviceNameSystem(List<String> devicenames) {
//		Map<String, Integer> deviceNamesMap = new HashMap();
//		List<String> uniqueNames = new ArrayList<>();
//
//		if (devicenames == null || devicenames.isEmpty()) {
//			return uniqueNames;
//		}
//
//		for (String name : devicenames)
//			if (deviceNamesMap.containsKey(name)) {
//				uniqueNames.add(name + deviceNamesMap.get(name));
//				deviceNamesMap.put(name, deviceNamesMap.get(name) + 1);
//			} else {
//				deviceNamesMap.put(name, 1);
//				uniqueNames.add(name);
//			}
//		return uniqueNames;
//	}
//
//	public static List<String> deviceNameSystem(List<String> devicenames) {
//		Map<String, Integer> deviceNamesMap = new HashMap<String, Integer>();
//		List<String> uniqueNames = new ArrayList<>();
//
//		if (devicenames == null || devicenames.isEmpty()) {
//			return uniqueNames;
//		}
//
//		for (String name : devicenames) {
//			if (deviceNamesMap.containsKey(name)) {
//				uniqueNames.add(name + deviceNamesMap.get(name));
//				deviceNamesMap.put(name, deviceNamesMap.get(name) + 1);
//			} else {
//				deviceNamesMap.put(name, 1);
//				uniqueNames.add(name);
//			}
//
//		}
//		return uniqueNames;
//	}

	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		int testCasesNumber = input.nextInt();
//		for (int testCases = 0; testCases < testCasesNumber; testCases++) {
//			int arrayLength = input.nextInt();
//			int[] array = new int[arrayLength];
//			for (int index = 0; index < arrayLength; index++) {
//				array[index] = input.nextInt();
//			}
//			HeapSort obj = new HeapSort();
//			obj.heapSort(array);
//			for (int element : array) {
//				System.out.print(element + " ");
//			}
//			System.out.println();
//		}

//		int arr[] = { 0, 10, 2, -10, -20 };
//		int arr[] = { 1, 2, 3 };
//		heapSort(arr);
//		for (int index = 0; index < arr.length; index++) {
//			if (arr[index] < 0) {
//				arr[index] = 0;
//			}
//		}
//		System.out.println(findMissingPositive(arr, arr.length));

//		System.out.println(HeapSort.solution("aaa"));
//		System.out.println(HeapSort.solution("aakmaakmakdak"));
//		System.out.println(HeapSort.solution("codelity"));
//		System.out.println(HeapSort.solution(""));
		int arr[] = new int[] { 1, 2, 4, 5, 10 };
		System.out.println(findMaxSubarraySum(arr, 10));
	}
}
