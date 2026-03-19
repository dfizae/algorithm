package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point{
	int r, c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class swea1226 {

	static int[][] maze = new int[16][16];
	static boolean[][] visit;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	

	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 0 �̵��� �� ����, 1��, 2 ������, 3 ������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			
			int startR = 0;
			int startC = 0;
			
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = line.charAt(j) - '0';
					if(maze[i][j] == 2) {
						startR = i;
						startC = j;
					}
				}
			}
			
			visit = new boolean[16][16];
			int answer = bfs(startR, startC);
			System.out.println("#" + t + " " + answer);
		}
		
		
	}

	static int bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		visit[r][c] = true;
		int answer = 0;
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			int currR = curr.r;
			int currC = curr.c;
			
			for (int d = 0; d < 4; d++) {
				int nr = currR + dr[d];
				int nc = currC + dc[d];
				if (nr >= 0 && nc >= 0 && nr <= 15 && nc <= 15 && !visit[nr][nc]) {
					visit[nr][nc] = true;
					if(maze[nr][nc] == 0) {
						q.add(new Point(nr, nc));
					} else if(maze[nr][nc] == 3) {
						answer = 1;
						break;
					}
				}
			}
		}
	return answer;	
	}
	
}
