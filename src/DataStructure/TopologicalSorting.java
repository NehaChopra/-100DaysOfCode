//https://www.geeksforgeeks.org/topological-sorting/
package DataStructure;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class TopologicalSorting {
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

		public void topologicalSortUtil(Stack stack, boolean visited[], int v) {
			visited[v] = true;
			ListIterator<Integer> itr = adj[v].listIterator();
			while (itr.hasNext()) {
				Integer i = itr.next();
				if (!visited[i]) {
					topologicalSortUtil(stack, visited, i);
				}
			}
			stack.push(new Integer(v));
		}

		public void topologicalSort() {
			Stack stack = new Stack();
			boolean visited[] = new boolean[vertex];

			for (int index = 0; index < vertex; index++) {
				if (!visited[index]) {
					topologicalSortUtil(stack, visited, index);
				}
			}

			while (stack.size() > 0) {
				System.out.println(stack.pop());
			}

		}

	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.topologicalSort();
	}

}
