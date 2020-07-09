//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
package Algorithm;

import java.util.List;

public class LargestSumContiguousSubarray {
	public static void main(String[] args) {
//		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
//		int a[] = { -2 };
//		int a[] = { 1 };
//		int a[] = {};
		int a[] = { 4, -1, -2, 1, 3 };
//		List<Integer> a = Arrays.asList(a);
		System.out.println(largestSumContinousArray(a));
//		System.out.println(largestSumContinousArray(a));
	}

	public static int largestSumContinousArray(int array[]) {
		int currentSum = 0;
		int sum = 0;
		if (array.length > 0) {
			for (int index = 0; index < array.length; index++) {
				currentSum = currentSum + array[index];
				if (currentSum < 0) {
					sum = 0;
					currentSum = 0;
				}
				if (currentSum > sum) {
					sum = currentSum;
				}
			}
		}
		return sum;
	}

	public static long largestSumContinousArray(List<Integer> array) {
		long currentSum = 0;
		long sum = 0;
		if (array.size() > 0) {
			for (int index = 0; index < array.size(); index++) {
				currentSum = currentSum + array.get(index);
				if (currentSum < 0) {
					sum = 0;
					currentSum = 0;
				}
				if (currentSum > sum) {
					sum = currentSum;
				}
			}
		}
		return sum;
	}

}
