//https://www.geeksforgeeks.org/lru-cache-implementation/
package DataStructure;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class LRUCache {

	public void generateCache(Set set, Deque queue, int ele, int cacheSize) {
		if(!set.contains(ele)) {
			if(queue.size() == cacheSize) {
				int remEle = (int) queue.removeLast();
				set.remove(remEle);
			}
		}else {
			int index=0; 
			int i = 0 ;
			Iterator itr = queue.iterator();
			while(itr.hasNext()) {
				if(itr.next().equals(ele)) {
					index = i;
					break;
				}
				i++;
			}
			queue.remove(index);
		}
		queue.push(ele);
		set.add(ele);
	}
	
	public static void main(String[] args) {
		int inputArray[] = { 1, 2, 3, 1, 4, 5 };
		Set set = new HashSet();
		Deque queue = new LinkedList<>();
		LRUCache cache = new LRUCache();
		
		for(int index=0; index<inputArray.length; index++) {
			cache.generateCache(set, queue, inputArray[index], 4);
		}
		
		Iterator itr = queue.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
