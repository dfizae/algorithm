package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_5521 {

	static int N, M, count;
	
	static List<Integer>[] adj;
	
	static boolean[] visit;
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		
			adj = new ArrayList[N+1];
			for(int n = 1; n<=N; n++) {
				adj[n] = new ArrayList<>();
			}
			
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			
				adj[a].add(b);
				adj[b].add(a);
			}
			
			visit = new boolean[N+1];
			// depth가 2인 경우까지가 초대장을 받는 거임
			count = 0;
			bfs(1, 0); // 시작 노드 1, depth 
			
			System.out.println("#" + t + " " + count);
		}
	}
	
	public static void bfs(int node, int depth) {
		visit[node] = true;
		Queue<int []> q = new LinkedList<>();
		q.add(new int[]{node, depth});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int currNode = curr[0];		
			int currDepth = curr[1];
		
			if(currDepth == 2) continue;
			
			for(int next : adj[currNode]) {
				if(!visit[next]) {
					visit[next] = true;
					count++;
					q.add(new int[] {next, currDepth+1});
				}
			}
		}
	}
}
