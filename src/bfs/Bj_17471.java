package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17471 {

	static int N, minDiff;
	static List<Integer>[] adj;
	
	static StringTokenizer st;
	
	static int[] peopleNum; // 각 노드의 인구 수를 담아내는 배열 
	static boolean[] isSelected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		minDiff = Integer.MAX_VALUE; // 각 선거 구의 최소 합을 구해야 함. 
		
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
		
		isSelected = new boolean[N + 1];
		dfs(1); // 선거구 나누기 (1번 노드 부터) 
		if(minDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minDiff);
		}
		
	}
	
	// 구역 나누기, 연결성 검사, 인구수 차이 계산을 모두 수행하는 단일 메서드
		public static void dfs(int node) {
			// 모든 구역의 선거구 배정이 끝났을 때 (종료) 
			if (node == N + 1) {
				int aStart = -1, bStart = -1;
				int sumA = 0, sumB = 0;
				
				// A구역과 B구역의 시작점 찾기 및 각 구역의 총 인구수 미리 계산
				for (int i = 1; i <= N; i++) {
					if (isSelected[i]) {
						aStart = i;
						sumA += peopleNum[i];
					} else {
						bStart = i;
						sumB += peopleNum[i];
					}
				}
				
				// 어느 한쪽 선거구에 구역이 하나도 없다면 빈값 반환
				if (aStart == -1 || bStart == -1) return;
				
				// BFS를 이용해 연결되어 있는지 검사 진행
				boolean[] visited = new boolean[N + 1];
				int connectedCount = 0; // 연결된 구역의 총 개수 카운트
				Queue <Integer> queue = new LinkedList<>();
				
				// A 선거구 연결 
				queue.add(aStart);
				visited[aStart] = true;
				connectedCount++;
				
				while (!queue.isEmpty()) {
					int curr = queue.poll();
					for (int next : adj[curr]) {
						if (!visited[next] && isSelected[next]) { // 같은 A 선거구(true)만 탐색
							visited[next] = true;
							queue.add(next);
							connectedCount++;
						}
					}
				}
				
				// B 선거구 연결 확인
				queue.add(bStart);
				visited[bStart] = true;
				connectedCount++;
				
				while (!queue.isEmpty()) {
					int curr = queue.poll();
					for (int next : adj[curr]) {
						if (!visited[next] && !isSelected[next]) { // 같은 B 선거구(false)만 탐색
							visited[next] = true;
							queue.add(next);
							connectedCount++;
						}
					}
				}
				
				// 정상적으로 모두 연결되었다면 (방문한 노드 수가 총 노드 수 N과 같다면) 최솟값 갱신
				if (connectedCount == N) {
					minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
				}
				
				return;
			}
			
			// 현재 구역을 A 선거구(true)에 배정하고 다음 구역으로
			isSelected[node] = true;
			dfs(node + 1);
			
			// 현재 구역을 B 선거구(false)에 배정하고 다음 구역으로
			isSelected[node] = false;
			dfs(node + 1);
		}


}


	



