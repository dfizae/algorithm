package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class dfs_1_그래프탐색 {

	static int N,M;
	
	static int count;
	
	static StringTokenizer st;
	
	static boolean[] visit;
	static List<Integer>[] graph;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N =Integer.parseInt(st.nextToken());
		M =Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		visit = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}
		
		
		visit[1] = true;
		count = 0;
		dfs(1);
		
		System.out.print(count);
	}
	
	static void dfs(int node) {
		for(int next : graph[node]) {
			if(!visit[next]) {
				visit[next] = true;
				count++;
				dfs(next);
			}
		}
	}
	
}
