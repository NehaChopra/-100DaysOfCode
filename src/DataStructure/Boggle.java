//https://www.geeksforgeeks.org/boggle-set-2-using-trie/

package DataStructure;

import DataStructure.Boggle.Trie.TrieNode;

public class Boggle {
	public static class Trie {
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
				int eleIndex = key.charAt(index) - 'A';
				if (crwal.children[eleIndex] == null) {
					crwal.children[eleIndex] = new TrieNode();
				}
				crwal = crwal.children[eleIndex];
			}
			crwal.endOfWord = true;
		}

	}

	public static void searchWords(TrieNode root, char boggle[][], boolean bvisited[][], int index, int jIndex,
			StringBuilder str) {
		if (root.endOfWord) {
			System.out.println(str);
		}
		if (isSafe(boggle, index, jIndex, bvisited)) {
			bvisited[index][jIndex] = true;
			for (int i = 0; i < 26; i++) {
				if (root != null && root.children[i] != null) {
					char chlookup = (char) ('A' + i);
					if (isSafe(boggle, index, jIndex + 1, bvisited) && boggle[index][jIndex + 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index, jIndex + 1, str.append(chlookup));
					}
					if (isSafe(boggle, index + 1, jIndex + 1, bvisited) && boggle[index + 1][jIndex + 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index + 1, jIndex + 1, str.append(chlookup));
					}
					if (isSafe(boggle, index + 1, jIndex, bvisited) && boggle[index + 1][jIndex] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index + 1, jIndex, str.append(chlookup));
					}
					if (isSafe(boggle, index + 1, jIndex - 1, bvisited) && boggle[index + 1][jIndex - 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index + 1, jIndex - 1, str.append(chlookup));
					}
					if (isSafe(boggle, index, jIndex - 1, bvisited) && boggle[index][jIndex - 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index, jIndex - 1, str.append(chlookup));
					}
					if (isSafe(boggle, index - 1, jIndex - 1, bvisited) && boggle[index - 1][jIndex - 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index - 1, jIndex - 1, str.append(chlookup));
					}
					if (isSafe(boggle, index - 1, jIndex, bvisited) && boggle[index - 1][jIndex] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index - 1, jIndex, str.append(chlookup));
					}
					if (isSafe(boggle, index - 1, jIndex + 1, bvisited) && boggle[index - 1][jIndex + 1] == chlookup) {
						searchWords(root.children[i], boggle, bvisited, index - 1, jIndex + 1, str.append(chlookup));
					}
				}
			}
			bvisited[index][jIndex] = false;
		}
	}

	public static boolean isSafe(char boggle[][], int index, int jIndex, boolean bvisited[][]) {
		if (index > -1 && index < boggle.length && jIndex > -1 && jIndex < boggle[0].length
				&& !bvisited[index][jIndex]) {
			return true;
		}
		return false;
	}

	public static void findWords(TrieNode root, char boggle[][]) {
		for (int index = 0; index < boggle.length; index++) {
			for (int jindex = 0; jindex < boggle[index].length; jindex++) {
				if (root.children[boggle[index][jindex] - 'A'] != null) {
					boolean bvisited[][] = new boolean[boggle.length][boggle[0].length];
					searchWords(root.children[boggle[index][jindex] - 'A'], boggle, bvisited, index, jindex,
							new StringBuilder("" + boggle[index][jindex]));
				}
			}
		}
	}

	public static void main(String[] args) {
		String words[] = { "GEEKS", "FOR", "QUIZ", "GEE" };
		Boggle boogle = new Boggle();
		Trie trie = new Boggle.Trie();
		trie.root = new Trie.TrieNode();
		for (int index = 0; index < words.length; index++) {
			trie.insert(words[index]);
		}
		char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' } };
		findWords(trie.root, boggle);
	}
}
