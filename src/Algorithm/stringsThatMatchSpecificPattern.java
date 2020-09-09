//https://www.geeksforgeeks.org/find-all-strings-that-match-specific-pattern-in-a-dictionary/
package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//public class stringsThatMatchSpecificPattern {

//	public static boolean searchWords(String word, String pattern) {
//		if (word.length() != pattern.length()) {
//			return false;
//		}
//		char[] arr = new char[128];
//		for (int index = 0; index < word.length(); index++) {
//			if (arr[pattern.charAt(index)] == 0) {
//				arr[pattern.charAt(index)] = word.charAt(index);
//			} else if (arr[pattern.charAt(index)] != word.charAt(index)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public static void main(String[] args) {
//		String[] words = new String[] { "abb", "abc", "xyz", "xyy" };
//		String pattern = "foo";
//		for (int index = 0; index < words.length; index++) {
//			System.out.println(searchWords(words[index], pattern));
//		}
//	}
//}

//class A1 {
//	public int i;
//	protected int j;
//}
//
//class B2 extends A1 {
//	int j;
//
//	void display() {
//		super.j = 3;
//		super.i = 10;
//		System.out.println(i + " " + j);
//	}
//}
//
//public class stringsThatMatchSpecificPattern {
//
//	public static void main(String args[]) {
//
//		B2 obj = new B2();
//		obj.i = 1;
//		obj.j = 2;
//		obj.display();
//	}
//}

//class A {
//	final public int GetResult(int a, int b) {
//		return 0;
//	}
//}
//
//class B extends A {
//	public int GetResult(int a, int b) {
//		return 1;
//	}
//}
//
//public class stringsThatMatchSpecificPattern {
//	public static void main(String args[]) {
//		B b = new B();
//		System.out.println("x = " + b.GetResult(0, 1));
//	}
//}

//class Men {
//	public int number;
//}
//
//public class stringsThatMatchSpecificPattern {
//	public void doIt(int i, Men p) {
//		i = 5;
//		p.number = 8;
//	}
//
//	public static void main(String args[]) {
//		int x = 0;
//		Men p = new Men();
//		new stringsThatMatchSpecificPattern().doIt(x, p);
//		System.out.println(x + " " + p.number);
//	}
//}

//public class stringsThatMatchSpecificPattern {
//
//	// Function to convert decimal number n
//	// to its binary representation
//	// stored as an array arr[]
//	static void decBinary(int arr[], int n) {
//		int k = (int) (Math.log(n) / Math.log(2));
//
//		while (n > 0) {
//			arr[k--] = n % 2;
//			n /= 2;
//		}
//	}
//
//	// Funtion to convert the number
//	// represented as a binary array
//	// arr[] into its decimal equivalent
//	static int binaryDec(int arr[], int n) {
//		int ans = 0;
//		for (int i = 0; i < n; i++)
//			ans += arr[i] << (n - i - 1);
//		return ans;
//	}
//
//	// Function to concatenate the binary
//	// numbers and return the decimal result
//	static int concat(int m, int n) {
//
//		// Number of bits in both the numbers
//		int k = (int) (Math.log(m) / Math.log(2)) + 1;
//		int l = (int) (Math.log(n) / Math.log(2)) + 1;
//
//		// Convert the bits in both the integers
//		// to the arrays a[] and b[]
//		int a[] = new int[k];
//		int b[] = new int[l];
//
//		// c[] will be the binary array
//		// for the result
//		int c[] = new int[k + l];
//		decBinary(a, m);
//		decBinary(b, n);
//
//		// Update the c[] array
//		int in = 0;
//		for (int i = 0; i < k; i++)
//			c[in++] = a[i];
//		for (int i = 0; i < l; i++)
//			c[in++] = b[i];
//
//		// Return the decimal equivalent
//		// of the result
//		return (binaryDec(c, k + l));
//	}
//
//	// Driver code
//	public static void main(String[] args) {
//		int m = 4, n = 5;
//
//		System.out.println(binaryDec(new int[] { 1, 1, 0, 1, 1 }, 5));
////		System.out.println(concat(m, n));
//	}
//}

public class stringsThatMatchSpecificPattern {

	public static Long[] decimalToBinary(Long arr[], Long num) {
		int index = (int) (Math.log(num) / Math.log(2));

		while (num > 0) {
			arr[index--] = num % 2;
			num = num / 2;
		}
		return arr;
	}

	public static Long binaryToDecimal(Long arr[], int len) {
		Long decNum = 0L;
		for (int i = 0; i < len; i++)
			decNum += arr[i] << (len - i - 1);
		return decNum;
	}

	public static Long binaryToDecimal(Long n) {
		Long num = n;
		Long fDecVal = 0L;
		int baseVal = 1;

		Long temp = num;
		while (temp > 0) {
			Long last_digit = temp % 10;
			temp = temp / 10;
			fDecVal = fDecVal + last_digit * baseVal;
			baseVal = baseVal * 2;
		}

		return fDecVal;
	}

	public static void addBinaryToList(Long num, List<Long> finalNumEle) {
		int estArrSize = (int) (Math.log(num) / Math.log(2)) + 1;
		Long arr[] = new Long[estArrSize];
		arr = decimalToBinary(arr, num);
		finalNumEle.addAll(Arrays.asList(arr));
	}

	public static long FindBigNum(long n) {
		if (n <= 0) {
			return 0;
		}
		List<Long> finalNumEle = new ArrayList<Long>();
		for (Long index = 1L; index <= n; index++) {
			addBinaryToList(index, finalNumEle);
		}
		if (finalNumEle.size() <= 0) {
			return 0;
		}
		String fString = finalNumEle.stream().map(Object::toString).collect(Collectors.joining(""));
		Long decimalNum = binaryToDecimal(finalNumEle.toArray(new Long[0]), finalNumEle.size());
//		return binaryToDecimal(Long.valueOf(fString));
		return decimalNum;
	}

	// Driver code
	public static void main(String[] args) {
		System.out.println(FindBigNum(3));
	}
}