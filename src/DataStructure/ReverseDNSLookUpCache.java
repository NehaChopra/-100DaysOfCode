package DataStructure;

class TrieNode1{
	TrieNode1[] children = new TrieNode1[11];
	boolean endOfWord;
	String URL;
	
	TrieNode1() {
		for(int index=0; index<11; index++) {
			this.children[index] = null;
		}
		this.endOfWord = false;
		this.URL = null;
	}
}

class Trie1{
	TrieNode1 root;
	
	public static int getIndex(char value) {
		return ((value == '.') ? 10 : (value - '0'));
	}
	
	public void insert(String key, String URL) {
		TrieNode1 pcrawl = root;
		for(int index=0 ;index<key.length(); index++) {
			int elementIndex = getIndex(key.charAt(index));
			if(pcrawl.children[elementIndex] == null) {
				pcrawl.children[elementIndex] = new TrieNode1();
			}
			pcrawl = pcrawl.children[elementIndex];
		}
		pcrawl.endOfWord = true;
		pcrawl.URL = URL;
	}
	
	public boolean search(String key) {
		TrieNode1 pcrawl = root;
		for(int index=0 ;index<key.length(); index++) {
			int elementIndex = getIndex(key.charAt(index));
			if(pcrawl.children[elementIndex] == null) {
				return false;
			}
			pcrawl = pcrawl.children[elementIndex];
		}
		if(pcrawl!=null && pcrawl.endOfWord) {
			System.out.println(pcrawl.URL);
			return true;
		}
		return false;
	}
}
class ReverseDNSLookUpCache {

	public static void main(String[] args) {
		TrieNode1 root = new TrieNode1();
		Trie1 obj = new Trie1();
		obj.root = root;
		
		obj.insert("107.108.11.123", "www.samsung.com");
		obj.insert("107.109.123.255", "www.samsung.net");
		obj.insert("74.125.200.106", "www.google.in");
		
		obj.search("74.125.200.106");
		System.out.println("================www.google.in========================");
		obj.search("107.109.123.255");
		System.out.println("=================www.samsung.net=======================");
		obj.search("107.108.11.123");
		System.out.println("=================www.samsung.com=======================");
	}

}

