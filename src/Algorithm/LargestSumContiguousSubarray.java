//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
package Algorithm;

public class LargestSumContiguousSubarray {
	public static void main(String []args) {
		int a[] = {-2, -3, 4, -1, -2, 1, 5, -3}; 
		System.out.println(largestSumContinousArray(a));
	}
	public static int largestSumContinousArray(int array[]) {
		int currentSum = 0;
		int sum = 0;
		for(int index=0; index<array.length; index++) {
			currentSum = currentSum + array[index];
			if(currentSum < 0) {
				sum = 0;
				currentSum = 0;
			}
			if(currentSum > sum) {
				sum = currentSum;
			}
		}
		return sum;
	}
}
