//https://www.geeksforgeeks.org/word-formation-using-concatenation-of-two-dictionary-words/
/*
 * 
1
5
news abcd tree geeks paper
newspaper

 */
package DataStructure;

import java.util.Scanner;

public class WordFormation {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		for(int index=0; index<testCases; index++){
			 TrieWF obj = new TrieWF();
			 TrieNodeWF root = new TrieNodeWF();
			 obj.root = root;
			int arrayLength = input.nextInt();
			for(int jIndex=0; jIndex<arrayLength; jIndex++){
				 obj.insert(input.next());
			}
			System.out.println(obj.search(input.next(),0));
		}
	}
}
class TrieNodeWF{
	TrieNodeWF children[] = new TrieNodeWF[26];
	boolean endOfWord;
	TrieNodeWF(){
		for(int index=0; index<children.length; index++){
			this.children[index] = null;
		}
		this.endOfWord = false;
	}
}

class TrieWF{
	TrieNodeWF root;
	public void insert(String key){
		TrieNodeWF cwral = root;
		for(int index=0; index<key.length();index++){
			int elementIndex = key.charAt(index) - 'a';
			if(cwral.children[elementIndex] == null){
				cwral.children[elementIndex] = new TrieNodeWF();
			}
			cwral = cwral.children[elementIndex];
		}
		cwral.endOfWord = true;
	}
	public boolean search(String key, int jIndex){
		if(jIndex >= key.length()) {
			return true;
		}
		TrieNodeWF cwral = root;
		for(int index=jIndex; index<key.length();index++){
			int elementIndex = key.charAt(index) - 'a';
			if(cwral.children[elementIndex] == null){
				return false;
			}
			cwral = cwral.children[elementIndex];
			if(cwral!=null && cwral.endOfWord) {
				return search(key, index+1);
			}
		}
		return (cwral!=null && cwral.endOfWord);
	}
}