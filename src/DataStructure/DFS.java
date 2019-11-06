//https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
package DataStructure;

import java.util.LinkedList;
import java.util.ListIterator;

public class DFS {
	public static class Graph {
		private int vertex;
		private LinkedList<Integer> adj[];

		public Graph(int vertex) {
			this.vertex = vertex;
			adj = new LinkedList[vertex];
			for (int index = 0; index < vertex; index++) {
				adj[index] = new LinkedList<Integer>();
			}
		}

		public void addEdge(int u, int v) {
			adj[u].add(v);
		}

		public void DFSUtil(boolean visited[], int v) {
			visited[v] = true;
			System.out.println(v);

			ListIterator<Integer> itr = adj[v].listIterator();
			while (itr.hasNext()) {
				Integer i = itr.next();
				if (!visited[i]) {
					DFSUtil(visited, i);
				}
			}
		}

		public void DFS() {
			boolean visited[] = new boolean[vertex];

			for (int index = 0; index < vertex; index++) {
				if (!visited[index]) {
					DFSUtil(visited, index);
				}
			}

		}
		
		public void DFS(int v) {
			boolean visited[] = new boolean[vertex];

			DFSUtil(visited, v);

		}

	}

	public static void main(String[] args) {
//		Graph g = new Graph(6);
//		g.addEdge(5, 2);
//		g.addEdge(5, 0);
//		g.addEdge(4, 0);
//		g.addEdge(4, 1);
//		g.addEdge(2, 3);
//		g.addEdge(3, 1);
//		g.DFS();
//		
		
		Graph g = new Graph(4); 
		  
        g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3); 
        g.DFS(2);
	}
}
