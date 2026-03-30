package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea2105_디저트카페 {

	static int N;
	static int[][] cafe;
	static boolean[][] visit;
	static boolean[] isEaten;
	static int maxCnt;
	
	// 사각형을 그리기 위한 대각선 방향 (우하 ↘, 좌하 ↙, 좌상 ↖, 우상 ↗)
	// 이 순서대로만 돌아야 겹치지 않고 온전한 사각형을 그릴 수 있습니다.
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			cafe = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCnt = -1; // 디저트를 먹을 수 없는 경우 -1을 출력해야 하므로 초기값을 -1로 설정
			visit = new boolean[N][N];
			isEaten = new boolean[101];  // 디저트 번호 1~100번 체크용 배열
			
			// 1. 탐색 시작
			// 사각형을 그려야 하므로 맨 아래쪽 2줄과 양 끝 열은 시작점이 될 수 없어 범위 최적화 가능
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					
					// 출발점 방문 및 디저트 먹음 처리
					visit[i][j] = true;
					isEaten[cafe[i][j]] = true;
					
					// DFS 시작 (현재 r, 현재 c, 시작 r, 시작 c, 현재 방향 인덱스, 먹은 디저트 개수)
					dfs(i, j, i, j, 0, 1);
					
					// 탐색이 끝나면 다음 시작점을 위해 출발점 상태 복구 (백트래킹)
					visit[i][j] = false;
					isEaten[cafe[i][j]] = false;
				}
			}
			
			System.out.println("#" + t + " " + maxCnt);
		}
	}

	static void dfs(int r, int c, int startR, int startC, int dir, int cnt) {
		// 현재 방향(dir)을 유지하거나, 다음 방향(dir + 1)으로 한 번 꺾는 두 가지만 탐색
		for (int d = dir; d < 4 && d <= dir + 1; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 2. 종료 조건: 사각형을 그려서 출발지로 다시 돌아왔고, 4칸 이상 이동했을 때
			if (nr == startR && nc == startC && cnt >= 4) {
				maxCnt = Math.max(maxCnt, cnt);
				return;
			}
			
			// 3. 탐색 진행: 경계 내에 있는 경우
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				// 아직 방문하지 않은 카페이고, 먹어보지 않은 디저트인 경우만 진입
				if (!visit[nr][nc] && !isEaten[cafe[nr][nc]]) {
					
					// 방문 처리 및 디저트 체크 (상태 변화)
					visit[nr][nc] = true;
					isEaten[cafe[nr][nc]] = true;
					
					// 재귀 호출 (다음 칸으로 이동)
					dfs(nr, nc, startR, startC, d, cnt + 1);
					
					// 백트래킹 (상태 원상복구)
					visit[nr][nc] = false;
					isEaten[cafe[nr][nc]] = false;
				}
			}
		}
	}
}