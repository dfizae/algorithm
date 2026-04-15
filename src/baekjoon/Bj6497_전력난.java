package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge>{
	int to; //도착 노드
	int weight; // 거리(가중치)
	
	public Edge(int to, int weight) {
		this.to= to;
		this.weight = weight;
	}
	
	@Override
	// 가중치 내림차순
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}
}




public class Bj6497_전력난 {
	
	static StringTokenizer st;
	
	static int m, n, maxPrice;
	
	static List<Edge>[] adj;
	
	static boolean[] visit;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(br.readLine()); //집 개수 (노드 개수)
		n = Integer.parseInt(br.readLine()); //길 개수 (간선 개수)
		
		// 인접리스트 초기화
		adj = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			adj[i] = new ArrayList<>();
		}
		
		
		// 집과 집 사이의 관계들을 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			maxPrice+=z;
			
			// 양방향 연결
			adj[x].add(new Edge(y, z));
			adj[y].add(new Edge(x, z));
			
			// 끈 값(답)
			int answer = 0;
			visit = new boolean[m];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
		}
	}
	
	
	
}


