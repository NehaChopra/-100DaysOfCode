package Expedia;
//https://www.geeksforgeeks.org/print-all-prime-numbers-less-than-or-equal-to-n/

class PrimeNumbers {
	public static void main(String[] args) {

	}

	public static void printPrime(int num) {
		for (int index = 1; index <= num; index++) {

		}
	}

	public static boolean isPrime(int num) {
		if (num == 1) {
			return false;
		}
		if (num <= 3) {
			return true;
		}
		if (num % 2 == 0 || num % 3 == 0) {
			return false;
		}

		return false;

	}
}
