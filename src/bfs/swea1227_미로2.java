package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point2{
	
	int r, c;
	
	public Point2(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class swea1227_미로2 {

	static int [][] maze = new int [100][100];
	static boolean [][] visit;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					maze[i][j] = line.charAt(j) - '0';
				}
			}
			
			int startR = 0;
			int startC = 0;
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					if(maze[i][j] == 2) {
						startR = i;
						startC = j;
					}
				}
			}
			
			visit = new boolean[100][100];
			int answer = bfs(startR, startC);
			System.out.println("#" + t + " " + answer);
			
			
		}
	}

	static int bfs(int r, int c) {
		Queue<Point2> q = new LinkedList<>();
		q.add(new Point2(r, c));
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			
			Point2 curr = q.poll();
			int currR = curr.r;
			int currC = curr.c;
			
			for (int d = 0; d < 4; d++) {
				int nr = currR + dr[d];
				int nc = currC + dc[d];
				
				if(nr >= 0 && nc >= 0 && nr <= 99 && nc <= 99 && !visit[nr][nc]) {
					
					if(maze[nr][nc] == 3) {
						return 1;
					}
					
					if(maze[nr][nc] == 0) {
						visit[nr][nc] = true;
						q.add(new Point2(nr, nc));
					}
				}
			}
		}
		
		return 0;
	}
	
	
	
}




