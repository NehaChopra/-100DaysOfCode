//http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in-java/
package trie;

class TrieNodePM {
	TrieNodePM children[] = new TrieNodePM[26];
	boolean endOfWord;

	TrieNodePM() {
		for (int index = 0; index < children.length; index++) {
			this.children[index] = null;
		}
		this.endOfWord = false;
	}
}

class TriePM {
	TrieNodePM root;

	public void insert(String key) {
		TrieNodePM cwral = root;
		for (int index = 0; index < key.length(); index++) {
			int elementIndex = key.charAt(index) - 'a';
			if (cwral.children[elementIndex] == null) {
				cwral.children[elementIndex] = new TrieNodePM();
			}
			cwral = cwral.children[elementIndex];
		}
		cwral.endOfWord = true;
	}

	public String search(String key) {
		StringBuffer finalString = new StringBuffer("");
		TrieNodePM cwral = root;
		for (int index = 0; index < key.length(); index++) {
			int elementIndex = key.charAt(index) - 'a';
			cwral = cwral.children[elementIndex];
			if (cwral == null) {
				finalString = new StringBuffer(key.substring(0, index));
				break;
			}
		}
		if (cwral != null && cwral.endOfWord) {
			return key;
		}
		return finalString.toString();
	}
}

class LongestPrefixMatching {
	public static void main(String[] args) {
		TrieNodePM root = new TrieNodePM();
		TriePM obj = new TriePM();
		obj.root = root;
		obj.insert("are");
		obj.insert("area");
		obj.insert("base");
		obj.insert("cat");
		obj.insert("cater");
		obj.insert("children");
		obj.insert("basement");

		System.out.println("caterer : " + obj.search("caterer"));
		System.out.println("basement : " + obj.search("basement"));
		System.out.println("basexyz : " + obj.search("basexyz"));
		System.out.println("are : " + obj.search("are"));
		System.out.println("area : " + obj.search("area"));
		System.out.println("arexyz : " + obj.search("arexyz"));
		System.out.println("arem : " + obj.search("arem"));
	}
}
