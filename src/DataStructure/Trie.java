//https://www.geeksforgeeks.org/trie-insert-and-search/
/*
 * the a there answer any by bye their
the
 */
package DataStructure;

public class Trie {
	private TrieNode root = null;

	public static class TrieNode {
		private TrieNode[] children = new TrieNode[26];
		private boolean endOfWord = false;

		TrieNode() {
			for (int index = 0; index < 26; index++) {
				children[index] = null;
				endOfWord = false;
			}
		}
	}

	public void insert(String key) {
		TrieNode crwal = root;
		for (int index = 0; index < key.length(); index++) {
			int eleIndex = key.charAt(index) - 'a';
			if (crwal.children[eleIndex] == null) {
				crwal.children[eleIndex] = new TrieNode();
			}
			crwal = crwal.children[eleIndex];
		}
		crwal.endOfWord = true;
	}

	public boolean search(String key) {
		TrieNode crwal = root;
		for (int index = 0; index < key.length(); index++) {
			int eleIndex = key.charAt(index) - 'a';
			if (crwal.children[eleIndex] == null) {
				return false;
			}
			crwal = crwal.children[eleIndex];
		}
		return (crwal != null && crwal.endOfWord);
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.root = new Trie.TrieNode();
		String inputString = "the a there answer any by bye their";
		String inputArray[] = inputString.split(" ");
		for (int index = 0; index < inputArray.length; index++) {
			trie.insert(inputArray[index]);
		}

		System.out.println(trie.search("the"));
		System.out.println(trie.search("ans"));
		System.out.println(trie.search("answer"));

	}
}
