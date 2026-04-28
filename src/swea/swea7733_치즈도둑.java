package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea7733_치즈도둑 {

	static int N;
	
	static int[][] cheeze;
	static boolean[][] visit;
	
	static StringTokenizer st;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static class Point{
		int r, c, day;
		public Point(int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			cheeze = new int[N][N];
			
			for(int r = 0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c<N; c++) {
					cheeze[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxChunk = 0;
			
			for(int x = 1; x<=100; x++) {
				visit = new boolean[N][N];
				int currentChunkCnt = 0;
				for(int r = 0; r<N; r++) {
					for(int c = 0; c<N; c++) {
						if(cheeze[r][c] > x && !visit[r][c]) {
							currentChunkCnt++;
							bfs(r, c, x);
						}
					}
				}
				
				maxChunk = Math.max(maxChunk, currentChunkCnt);
			}
			System.out.println("#" + t + " " + maxChunk);
		}
		
		
	}
	
	
	public static void bfs(int r, int c, int day) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(r, c, day));
		visit[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			for(int d = 0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr>=0 && nc >= 0 && nr < N && nc < N) {
					if(!visit[nr][nc] && cheeze[nr][nc] > day) {
						visit[nr][nc] = true;
						queue.add(new Point(nr, nc, day+1));
					}
						
				}
			}
			
		}
		
	}
	
}
