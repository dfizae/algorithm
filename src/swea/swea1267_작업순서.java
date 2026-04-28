package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1267_작업순서 {
	
	static int V, E;
	
	static StringTokenizer st;
	static StringBuilder sb;
	
	static List<Integer>[] adj;
	
	static int[] inDegree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t<= 10; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V+1];
			inDegree = new int[V+1];
			for(int i = 1; i<=V; i++) {
				adj[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<E; i++) {
				int from = Integer.parseInt(st.nextToken()); 
	            int to = Integer.parseInt(st.nextToken());   
	            
	            adj[from].add(to);
	            inDegree[to]++;
			}
			
			Queue<Integer> queue = new LinkedList<>();
			for(int i = 1; i<=V; i++) {
				if(inDegree[i] == 0) {
					queue.add(i);
				}
			}
			
			sb.append("#").append(t).append(" ");
			    
			while(!queue.isEmpty()) {
				int curr = queue.poll();
				sb.append(curr).append(" ");
				for(int next : adj[curr]) {
					inDegree[next]--;
					
					if(inDegree[next] == 0) {
						queue.add(next);
					}
				}
			}
			sb.append("\n");
			
		}
		
		System.out.print(sb);
	}
}
