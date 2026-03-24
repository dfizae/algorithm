package dfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1949_등산로조성 {
	
	static int N, K;
	static int maxLength, maxHeight;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] map;
	static boolean[][] visit;
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				st = new StringTokenizer(line);
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= maxHeight) {
						maxHeight = map[i][j];
					}
				}
			}
			
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == maxHeight) {
						visit[i][j] = true;
						dfs(i, j, 1, false);
						visit[i][j] = false;
					}	
				}
			}
			System.out.println("#" + t + " ");
		}
	}

	static void dfs(int r, int c, int length, boolean isCut) {
		
		maxLength = Math.max(maxLength, length);
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N - 1 ||nc > N - 1 || visit[nr][nc]) continue;
		
			if(map[nr][nc] < map[r][c]) {
				visit[nr][nc] = true;
				dfs(nr, nc, length + 1, isCut);
				visit[nr][nc] = false;
			}
			
			else if(!isCut && map[nr][nc] - K < map[r][c]) {
				int originalHeight = map[nr][nc]; // 깎기 전 원래 높이 저장
				
				// 핵심: 현재 위치보다 딱 1만큼만 낮게 깎아야 이후 탐색에 유리
				map[nr][nc] = map[r][c] - 1;
				visit[nr][nc] = true;
				
				dfs(nr, nc, length+1, isCut);
				
				visit[nr][nc] = false;
				map[nr][nc] = originalHeight;
			}
			
		}
	}
}
