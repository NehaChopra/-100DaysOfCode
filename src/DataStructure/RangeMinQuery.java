//https://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
package DataStructure;

import java.util.Arrays;

public class RangeMinQuery {
	public static int[] array;
	public static int arrayL;

	RangeMinQuery(int arrayLength) {
		this.arrayL = 2 * (int) (Math.pow(2, ((int) Math.ceil(Math.log(arrayLength) / Math.log(2)))) - 1);
		array = new int[arrayL];
	}

	public int getMid(int start, int end) {
		return (start + (end - start) / 2);
	}

	public int constructSegmentArray(int inputArray[], int segStart, int segEnd, int segIndex) {
		if (segStart == segEnd) {
			array[segIndex] = inputArray[segStart];
			return inputArray[segStart];
		}
		int mid = getMid(segStart, segEnd);
		array[segIndex] = Math.min(constructSegmentArray(inputArray, segStart, mid, (2 * segIndex) + 1), 
				constructSegmentArray(inputArray, mid + 1, segEnd, (2 * segIndex) + 2));
		return array[segIndex];
	}

	public int findMin(int segStart, int segEnd, int quStart, int quEnd, int segIndex) {
		if (quStart <= segStart && quEnd >= segEnd) {
			return array[segIndex];
		}
		if (segEnd < quStart || segStart > quEnd) {
			return Integer.MAX_VALUE;
		}
		int mid = getMid(segStart, segEnd);
		return Math.min(findMin(segStart, mid, quStart, quEnd, (2 * segIndex) + 1)
				, findMin(mid + 1, segEnd, quStart, quEnd, (2 * segIndex) + 2));
	}

	public static void main(String[] args) {
		int inputArray[] = { 1, 3, 2, 7, 9, 11 };
		RangeMinQuery st = new RangeMinQuery(inputArray.length);
		st.constructSegmentArray(inputArray, 0, inputArray.length - 1, 0);
		System.out.println(Arrays.toString(st.array));
		System.out.println(st.findMin(0, inputArray.length - 1, 1, 5, 0));
	}
}
