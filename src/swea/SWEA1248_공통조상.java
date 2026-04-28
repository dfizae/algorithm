package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1248_공통조상 {

	static int[] parent;
	static List<Integer>[] child;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int node1= Integer.parseInt(st.nextToken());
			int node2= Integer.parseInt(st.nextToken());
			
			parent = new int[V+1];
			child = new ArrayList[V+1];
		
			for(int i = 1; i<V; i++) {
				child[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<E; i++) {
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				parent[c] = p; // 자식의 부모를 기록
				child[p].add(c); // 부모의 자식을 기록
			}
			
			int lca = findLCA(node1, node2, V);
			int subTreeSize = getSubTreeSize(lca);
			
			System.out.println("#" + t + " " + lca + " " + subTreeSize);
		}
	}

		static int findLCA(int n1, int n2, int V) {
			boolean[] visited = new boolean[V + 1];
			
			int curr = n1;
			while(curr != 0) { 
				visited[curr] = true;
				curr = parent[curr];
			}
		
			curr = n2;
			while(curr != 0) {
				if(visited[curr]) {
					return curr;
				}
				curr = parent[curr];
			}
			
			return 1;
		}

		static int getSubTreeSize(int node) {
			int size = 1; 
			

			for(int c : child[node]) {
				size += getSubTreeSize(c);
			}
			
			return size;
		}
}
