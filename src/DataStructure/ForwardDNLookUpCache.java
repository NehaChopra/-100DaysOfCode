//https://www.geeksforgeeks.org/implement-forward-dns-look-cache/
package DataStructure;

class TrieNode2{
	TrieNode2[] children = new TrieNode2[28];
	boolean endOfWord;
	String IP;
	
	TrieNode2() {
		for(int index=0; index<28; index++) {
			this.children[index] = null;
		}
		this.endOfWord = false;
		this.IP = null;
	}
}

class Trie2{
	TrieNode2 root;
	
	public static int getIndex(char value) {
		return ((value=='.') ? 27 : (value-'a'));
	}
	
	public void insert(String key, String IP) {
		TrieNode2 pcrawl = root;
		for(int index=0 ;index<key.length(); index++) {
			int elementIndex = getIndex(key.charAt(index));
			if(pcrawl.children[elementIndex] == null) {
				pcrawl.children[elementIndex] = new TrieNode2();
			}
			pcrawl = pcrawl.children[elementIndex];
		}
		pcrawl.endOfWord = true;
		pcrawl.IP = IP;
	}
	
	public boolean search(String key) {
		TrieNode2 pcrawl = root;
		for(int index=0 ;index<key.length(); index++) {
			int elementIndex = getIndex(key.charAt(index));
			if(pcrawl.children[elementIndex] == null) {
				return false;
			}
			pcrawl = pcrawl.children[elementIndex];
		}
		if(pcrawl!=null && pcrawl.endOfWord) {
			System.out.println(pcrawl.IP);
			return true;
		}
		return false;
	}
}
class ForwardDNLookUpCache {

	public static void main(String[] args) {
		TrieNode2 root = new TrieNode2();
		Trie2 obj = new Trie2();
		obj.root = root;
		
		obj.insert("www.samsung.com", "107.108.11.123");
		obj.insert("www.samsung.net", "107.109.123.255");
		obj.insert("www.google.in", "74.125.200.106");
		
		obj.search("www.google.in");
		System.out.println("================www.google.in========================");
		obj.search("www.samsung.net");
		System.out.println("=================www.samsung.net=======================");
		obj.search("www.samsung.com");
		System.out.println("=================www.samsung.com=======================");
	}

}

