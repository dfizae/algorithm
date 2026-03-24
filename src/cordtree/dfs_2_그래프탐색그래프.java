package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_2_그래프탐색그래프 {

	static int N, M;
	static int[][] graph;
	static boolean[][] visit;
	
	// 포인트 1: 아래, 오른쪽 두 방향만 설정
	static int[] dr = {1, 0}; 
	static int[] dc = {0, 1}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 시작점 자체가 뱀(0)일 수도 있으므로 안전하게 1일 때만 출발
		if (graph[0][0] == 1) {
			System.out.println(dfs(0, 0));
		} else {
			System.out.println(0);
		}
	}

	static int dfs(int r, int c) {
		// 목적지(우측 하단)에 도달하면 1 반환
		if (r == N - 1 && c == M - 1) {
			return 1;
		}
		
		visit[r][c] = true; // 현재 위치 방문 처리
		
		for (int d = 0; d < 2; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 포인트 2: 열(nc)의 경계는 M과 비교해야 합니다.
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				// 방문하지 않았고 뱀이 없는 곳(1)이라면 진행
				if (!visit[nr][nc] && graph[nr][nc] == 1) {
					// 포인트 3: 다음 경로에서 목적지에 도달했다면(1을 반환했다면), 현재도 1을 반환하며 탐색 즉시 종료
					if (dfs(nr, nc) == 1) {
						return 1;
					}
				}
			}
		}
		
		// 갈 수 있는 길을 다 가봤는데도 목적지에 못 갔다면 0 반환
		return 0;
	}
}