package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj17471 {
	
	static int N, answer;
	static List<Integer>[] adj;
	
	static StringTokenizer st;
	
	static int[] peopleNum;
	static boolean[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		
		// 인접 리스트 초기화
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// 각 노드 당 인구 수 넣어주기
		st = new StringTokenizer(br.readLine());
		peopleNum = new int[N+1]; // 각 노드 당 인구 수
		for (int i = 1; i <= N; i++) {
			peopleNum[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접 리스트에 각 노드 별 연결 여부 확인
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
				
			for (int l = 0; l < len; l++) {
				int num = Integer.parseInt(st.nextToken());
				adj[i].add(num);
			}
		}
		
		answer = -1;
		visit = new boolean[N+1];
		
	}
	
	public static int Dfs(int node) {
		visit[node] = true;
		answer += peopleNum[node];
		
		if(adj[node].size() == 1 ) { // 막다른 노드면 break;
			return answer;
		}
		
			for (int j = 0; j < adj[node].size(); j++) {
				int curr = adj[node].get(j);
				if(!visit[curr]) {
					Dfs(curr);
				}
			}
			
		return answer;
		
	}

}
