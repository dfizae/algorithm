package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_4_안전지대 {

	static int N, M;
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int maxHeight = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(maxHeight <= map[i][j]) {
					maxHeight = map[i][j];
				}
			}
		}
		
		int bestK = 1;
		int maxCnt = 0;
		
		for (int k = 1; k <= maxHeight; k++) {
			visit = new boolean[N][M];
			int currCnt = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(!visit[r][c] && map[r][c] > k) {
						Dfs(r, c, k);
						currCnt++; // Dfs 탐색이 끝나면 영역을 1 증가
					}
				}
			}
			if(maxCnt < currCnt){
				maxCnt = currCnt;
				bestK = k;
				
			}
		}
		System.out.println(bestK + " " + maxCnt);
	}
	
	public static void Dfs(int r, int c, int k) {
		visit[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if(!visit[nr][nc] && map[nr][nc] < k) {
					Dfs(nr, nc, k);
				}
			}
		}
	}
}
