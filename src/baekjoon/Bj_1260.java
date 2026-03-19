package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Bj_1260 {
	
	static int N, M, V;
	
	static List<Integer>[] adjList;
	static boolean[] visit;
	
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		
		
		// 인접리스트 초기화
		adjList = new ArrayList[N + 1]; 
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 인접리스트에 노드 간선 관계 집어넣기 - 양방향
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
		
			// 양방향 넣어주기
			adjList[start].add(end);
			adjList[end].add(start);
		}

		// 방문 처리 초기화
		visit = new boolean[N+1];
		dfs(V);
		visit = new boolean[N+1];
		bfs(V);
		
	}
	
	static void dfs(int node) {
		// 노드가 끝까지 탐색되었을 경우 종료
		if(node == N) return;
		
		Stack<Integer> stack = new Stack<>();
		stack.push(node);
		
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			
			visit[curr] = true;
			System.out.print(curr + " ");
		
			for (int i = 0; i < adjList[curr].size(); i++) {
				
			}
		}	
	}

	static void bfs(int startNode) {
		Queue<Integer> q = new LinkedList<>();
		q.add(startNode);
		visit[startNode] = true;
		
		while(!q.isEmpty()) {
			 int curr = q.poll();
			 System.out.print(curr + " ");
			 
			 for(int i = 0; i<adjList[curr].size(); i++) {
				 int near = adjList[curr].get(i);
				 if(!visit[near]) {
					 q.add(near);
					 visit[near] = true;
				 }
			 }
		}
	}
	
}
