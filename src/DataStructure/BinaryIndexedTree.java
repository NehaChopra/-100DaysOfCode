//https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
/*
 * An alternative solution is Binary Indexed Tree, which also achieves O(Logn) 
 * time complexity for both operations. Compared with Segment Tree, Binary Indexed 
 * Tree requires less space and is easier to implement..
 * 
 * The size of the Binary Indexed Tree is equal to the size of the input array,
 * 
 */
package DataStructure;

import java.util.Arrays;

public class BinaryIndexedTree {
	private static int array[];

	public BinaryIndexedTree(int inputArrayLen) {
		this.array = new int[inputArrayLen + 1];
	}

	public void construct(int index, int inputArrayLen, int val) {
		index = index + 1;
		while(index <= inputArrayLen) {
			array[index] =  array[index] + val;
			index = index + ((index) & (-index));
		}
	}

	public void constructBinaryTree(int inputArray[]) {
		for (int index = 0; index < inputArray.length; index++) {
			construct(index, inputArray.length, inputArray[index]);
		}
	}

	public int evaluateSum(int index, int end) {
		int sum = 0;
		end = end + 1;
		while(end > index) {
			sum = sum + array[end];
			end = end - ((end) & (-end));
		}
		return sum;
	}

	public static void main(String[] args) {
		int inpuArray[] = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };
		BinaryIndexedTree bitree = new BinaryIndexedTree(inpuArray.length);
		bitree.constructBinaryTree(inpuArray);
		System.out.println(Arrays.toString(bitree.array));
		System.out.println(bitree.evaluateSum(0, 5));
	}
}
