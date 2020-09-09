package Expedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public static void main(String[] args) {
		Integer arr[] = { 1, 1, 1, 2, 3, 3 };
		System.out.println(deleteProducts(Arrays.asList(arr), 2));
	}

	public static List<String> deviceNamesSystem(List<String> devicenames) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> uniqueNames = new ArrayList<>();
		if (devicenames == null || devicenames.isEmpty()) {
			return uniqueNames;
		}
		for (String deviceName : devicenames) {
			String fdeviceName = "";
			if (map.containsKey(deviceName)) {
				fdeviceName = deviceName + map.get(deviceName);
				map.put(deviceName, map.get(deviceName) + 1);
			} else {
				map.put(deviceName, 1);
				fdeviceName = deviceName;
			}
			uniqueNames.add(fdeviceName);
		}
		return uniqueNames;
	}

	/**
	 ** public static List<String> deviceNamesSystem(List<String> devicenames) {
	 * Map<String, Integer> deviceNamesMap = new HashMap<String, Integer>();
	 * List<String> uniqueNames = new ArrayList<>();
	 * 
	 * if (devicenames == null || devicenames.isEmpty()) { return uniqueNames; }
	 * 
	 * for (String name : devicenames) { if (deviceNamesMap.containsKey(name)) {
	 * uniqueNames.add(name + deviceNamesMap.get(name)); deviceNamesMap.put(name,
	 * deviceNamesMap.get(name) + 1); } else { deviceNamesMap.put(name, 1);
	 * uniqueNames.add(name); }
	 * 
	 * } return uniqueNames; }
	 */

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

	static int deleteProducts(List<Integer> ids, int m) {
		// Write your code here
		if (ids.size() <= 0 || m == 0) {
			return 0;
		}
		HashMap<Integer, Integer> hmap = new HashMap<>();
		for (int index = 0; index < ids.size(); index++) {
			if (hmap.containsKey(ids.get(index)))
				hmap.put(ids.get(index), hmap.get(ids.get(index)) + 1);
			else
				hmap.put(ids.get(index), 1);
		}
		for (Map.Entry<Integer, Integer> es : hmap.entrySet()) {
			if (es.getValue() == m) {
				hmap.remove(es.getKey());
			}
		}
		return hmap.keySet().size();
	}

//	static int deleteProducts(List<Integer> ids, int m) {
//		// Write your code here
//		if (ids.size() <= 0 || m == 0) {
//			return 0;
//		}
//		HashMap<Integer, Integer> hmap = new HashMap<>();
//		for (int index = 0; index < ids.size(); index++) {
//			if (hmap.containsKey(ids.get(index)))
//				hmap.put(ids.get(index), hmap.get(ids.get(index)) + 1);
//			else
//				hmap.put(ids.get(index), 1);
//		}
//		Integer[] array = new Integer[hmap.size()];
////		int index = 0;
////		for (Integer val : hmap.keySet()) {
////			array[index] = hmap.get(val);
////			index++;
////		}
//		array = hmap.values().toArray(new Integer[0]);
//
//		heapSort(array);
//		int finalSize = 0;
//		for (int i = 0; i < array.length && m > 0; i++) {
//			m = m - array[i];
//			if (m >= 0)
//				finalSize++;
//		}
//		return array.length - finalSize;
//	}

	public static long maxSum(List<Long> arr, long threshold) {
		// Write your code here
		if (arr.size() <= 0 || threshold == 0) {
			return 0;
		}

		long sum = arr.get(0);
		long maxSum = 0;
		int jIndex = 0;

		for (int index = 1; index < arr.size(); index++) {

			if (sum <= threshold)
				maxSum = Math.max(maxSum, sum);

			while (sum + arr.get(index) > threshold && jIndex < index) {
				sum = sum - arr.get(jIndex);
				jIndex++;
			}

			sum = sum + arr.get(index);
		}

		if (sum <= threshold) {
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}
}
