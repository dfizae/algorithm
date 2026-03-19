package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178미로탐색 {

	static int N, M;
	static int[][] maze;
	static boolean[][] visited;
	
	static int[] dr = {0, 0, -1, 1}; 
	static int[] dc = {-1, 1, 0, 0}; 

	
	static class Point{
		int x, y, dist;
		
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				maze[r][c] = line.charAt(c) - '0'; 
			}
		}
		
		
		Bfs(0, 0);
		
	}

	static void Bfs(int startX, int startY) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX, startY, 1));
		
		// 출발점 방문 처리
		visited[startX][startY] = true;
		
		int[] dr = {0, 0, -1, 1};
		int[] dc = {-1, 1, 0, 0};
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int currR = current.x;
			int currC = current.y;
			
			if(currR == N-1 && currC == M - 1) {
				System.out.println(current.dist);
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = currR + dr[d];
				int nc = currC + dc[d];
				if(nr >= 0 && nr<=N-1 && nc>=0 && nc<=M-1) {
					if(!visited[nr][nc] && maze[nr][nc] == 1) {
						q.add(new Point (nr, nc, current.dist+1));
						visited[nr][nc] = true;
					}
				}
			}
		}
		
	}
	
}
