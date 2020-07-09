//https://www.geeksforgeeks.org/find-all-strings-that-match-specific-pattern-in-a-dictionary/
package Algorithm;

public class stringsThatMatchSpecificPattern {
	public static boolean searchWords(String word, String pattern) {
		if (word.length() != pattern.length()) {
			return false;
		}
		char[] arr = new char[128];
		for (int index = 0; index < word.length(); index++) {
			if (arr[pattern.charAt(index)] == 0) {
				arr[pattern.charAt(index)] = word.charAt(index);
			} else if (arr[pattern.charAt(index)] != word.charAt(index)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = new String[] { "abb", "abc", "xyz", "xyy" };
		String pattern = "foo";
		for (int index = 0; index < words.length; index++) {
			System.out.println(searchWords(words[index], pattern));
		}
	}
}
