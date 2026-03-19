package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7733_치즈도둑 {

	static int N;
	
	// 치즈, 방문탐색
	static int[][] cheese;
	static boolean[][] visited;
	
	static class Point{
		int r, c;
		int day;
		
		public Point(int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	
	// 방향 델타
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxChunks = 0;
			
			// 100일 동안의 덩어리 기록
			for (int d = 0; d <= 100; d++) {
				visited = new boolean[N][N];
				int currentDayChunks = 0;
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if(cheese[r][c] > d && !visited[r][c]) {
							currentDayChunks++;
							Bfs(r, c, d);
						}
					}
				}
				
				maxChunks = Math.max(maxChunks, currentDayChunks);
				
			}
			System.out.println("#" + t + " " + maxChunks);
			
			
		}
	}
	
	static void Bfs(int r, int c, int day) {
		Queue<Point> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new Point(r, c, day));
		
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			int currR = p.r;
			int currC = p.c;
			
			// 100일까지 탐색
			for (int d = 0; d < 4; d++) {
				int nr = currR + dr[d];
				int nc = currC + dc[d];
				
				if(nr >= 0 && nc >= 0 && nr <= N-1 && nc <= N-1) {
					if(!visited[nr][nc] && cheese[nr][nc] > day) {
						visited[nr][nc] = true;
						q.add(new Point(nr, nc, day));
					}
				}
				
			}
		}
		
	}

}
