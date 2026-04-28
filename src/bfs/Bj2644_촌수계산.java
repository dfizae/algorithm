package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj2644_촌수계산 {

	static int N, M, first, second, answer;
	
	static StringTokenizer st;
	
	static List<Integer>[] adj;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		first = Integer.parseInt(st.nextToken());
		second = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();		
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		
		visit = new boolean[N+1];
		answer = -1;
		dfs(first, 0);
		
		System.out.println(answer);
	}
	
	public static void dfs(int node, int depth) {
		visit[node] = true;
		if(node == second) {
			answer = depth;
			return;
		}
		
		for(int curr : adj[node]) {
			if(!visit[curr]) {
				dfs(curr, depth+1);
			}
		}
	}
}
