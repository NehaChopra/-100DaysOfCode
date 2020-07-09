/*
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
 */
package Algorithm;

import java.util.HashMap;
import java.util.Map;

public class SubarrayWithSum {
	public static void main(String[] args) {
		int arr[] = { 1, 4, 20, 3, 10, 5 };
		int sum = 33;
//		int arr[] = {10, 2, -2, -20, 10};
//		int sum = -10;
//		int arr[] ={-10, 0, 2, -2, -20, 10};
//		int sum = 20;
		subarrayWithGivenSum(arr, sum);
		int nums[] = { -1, 3, 4, -2, 5, -7 };
		maxSubArray(nums);
	}

	public static void subarrayWithGivenSum(int arr[], int sum) {
		int currentSum = 0;
		int start = 0;
		int end = -1;
		Map<Integer, Integer> map = new HashMap<>();
		for (int index = 0; index < arr.length; index++) {
			currentSum = currentSum + arr[index];
			if (currentSum - sum == 0) {
				start = 0;
				end = index;
				break;
			}
			if (map.containsKey(currentSum - sum)) {
				start = map.get(currentSum - sum) + 1;
				end = index;
				break;
			}
			map.put(currentSum, index);
		}
		if (end != -1) {
			System.out.println(start + "........" + end);
		}
	}

	public static int maxSubArray(int[] nums) {
		int result = nums[0];
		int sum = nums[0];

		for (int i = 1; i < nums.length; i++) {
			sum = Math.max(nums[i], sum + nums[i]);
			result = Math.max(result, sum);
		}

		return result;
	}

}
