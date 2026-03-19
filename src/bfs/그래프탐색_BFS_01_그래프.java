package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 그래프탐색_BFS_01_그래프 {

	static int V, E; //정점, 간선
	static List<Integer>[] adjList; //인접리스트
	static boolean[] visited; //방문쳌
	static Queue<Integer> q; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();
		
		adjList = new ArrayList[V+1]; //1번부터시작 
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		} // 초기화 작업이 필요하다. 그래야 null을 방지한다.
		
		visited = new boolean[V+1];
		
		for (int i = 0; i < E; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			
			//지금의 예제는 무향!
			adjList[st].add(ed);
			adjList[ed].add(st);
		} // 입력끝
	
		bfs(1);
		
	}
	
	// v: 시작 정점
	static void bfs(int v) {
		q = new ArrayDeque<>();
		
		q.add(v); //시작정점을 넣자!
		visited[v] = true; //시작정점 방문체크
		int dist = 1; //시작점의 레벨 혹은 길이
		
		//큐가 공백이 될때까지
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int s = 0; s < size; s++) {
				
				
				
				
			}
			
			int curr = q.poll(); //remove도 상관은 없다.
			System.out.println(curr); //작업을 했다!
			
			//curr 인접하면서, 방문하지 않은 정점들을 Q에 넣자!
//			for (int i = 0; i < adjList[curr].size(); i++) {
//				int w = adjList[curr].get(i); //w: curr 인접한 정점
//				if (!visited[w]) {
//					q.add(w);
//					visited[w] = true;
//				}
//			}
			
			for (int w : adjList[curr]) {
				if (!visited[w]) {
					q.add(w);
					visited[w] = true;
				}
			}
			
		}
		
	}
	
}
