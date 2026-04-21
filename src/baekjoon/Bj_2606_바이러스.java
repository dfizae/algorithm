package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj_2606_바이러스 {
	static int N,V,infected;
	
	static StringTokenizer st;
	
	static List<Integer>[] adj;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		V = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1]; 
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj[start].add(end);
			adj[end].add(start);	
		}
		
		visit = new boolean[N+1];
		infected = 0;
		Dfs(1);
		System.out.println(infected);
	}
	
	public static void Dfs(int node) {
		visit[node] = true;
		
		for(int curr : adj[node]) {
			if(!visit[curr]) {
				infected++;
				Dfs(curr);
			}
		}
	}
	
}
