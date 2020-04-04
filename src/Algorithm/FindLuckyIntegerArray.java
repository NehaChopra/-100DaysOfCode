//https://leetcode.com/problems/find-lucky-integer-in-an-array/
package Algorithm;

import java.util.HashMap;
import java.util.Map;

public class FindLuckyIntegerArray {
	
	public static void main(String[] args) {
		int arr[] = {2,2,3,4};
		System.out.println(findLuckyNumber(arr));
	}
	
	public static int findLuckyNumber(int[] arr) {
		Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		for(int index=0; index<arr.length; index++) {
			if(numbers.containsKey(arr[index])) {
				numbers.put(arr[index], numbers.get(arr[index]) + 1);
			}else {
				numbers.put(arr[index], 1);
			}
		}
		int largest = -1;
		for(int index=0; index<arr.length; index++) {
			if(numbers.get(arr[index]).equals(arr[index]) && largest < arr[index]) {
				largest = arr[index];
			}
		}
		return largest;
	}
	
}
