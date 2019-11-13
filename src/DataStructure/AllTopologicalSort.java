//https://www.geeksforgeeks.org/topological-sorting/
package DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class AllTopologicalSort {
	public static class Graph {
		private int vertex;
		private LinkedList<Integer>[] adj;

		Graph(int vertex) {
			this.vertex = vertex;
			adj = new LinkedList[this.vertex];
			for (int index = 0; index < this.vertex; index++) {
				adj[index] = new LinkedList<Integer>();
			}
		}

		public void addEdge(int u, int v) {
			adj[u].add(v);
		}

		public void findAllTopologicalSorts() {
			ArrayList<Integer> stack = new ArrayList(this.vertex);
			boolean[] visited = new boolean[this.vertex];
			int[] indegree = new int[this.vertex];
			for (int index = 0; index < this.vertex; index++) {
				LinkedList<Integer> adjVetex = adj[index];
				if (adjVetex.size() > 0) {
					for (int v : adjVetex) {
						indegree[v]++;
					}
				}
			}
			TopologicalSortUtil(stack, indegree, visited);
		}

		public void TopologicalSortUtil(ArrayList<Integer> stack, int[] indegree, boolean[] visited) {
			boolean flag = false;
			for (int index = 0; index < this.vertex; index++) {
				if (!visited[index] && indegree[index] == 0) {
					visited[index] = true;
					stack.add(index);
					if (adj[index].size() > 0) {
						for (int v : adj[index]) {
							indegree[v]--;
						}
					}
					TopologicalSortUtil(stack, indegree, visited);
					visited[index] = false;
					stack.remove(stack.size() - 1);
					if (adj[index].size() > 0) {
						for (int v : adj[index]) {
							indegree[v]++;
						}
					}
					flag = true;
				}
			}
			if (!flag) {
				System.out.print(Arrays.toString(stack.toArray()));
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph(6);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		graph.findAllTopologicalSorts();
	}
}
