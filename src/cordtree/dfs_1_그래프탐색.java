package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class dfs_1_그래프탐색 {

	static int N, M;
	
	static int count;
	
	static List<Integer>[] graph;
	static boolean[] visit;
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		visit = new boolean[N+1];
		for (int i = 0; i < N; i++) {
			graph[i+1] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}
		
		// 시작 노드, 방문한 카운트, 시작 노드는 1로 고정
		visit[1] = true;
		count = 0; // 결과값이 될 방문 카운트
		int result = dfs(N, M, 1, count);
		System.out.println();
	}
	
	static int dfs(int N, int M, int start, int c) {
		int count = c;
		
		// 종료조건
		// 끝 노드에 도착하면 count를 세주기
		if(start == N) {
			return count;
		}
		
		for (int i = 0; i < M; i++) {
			
			
			
		}
	
		return count;
	}
	
}
