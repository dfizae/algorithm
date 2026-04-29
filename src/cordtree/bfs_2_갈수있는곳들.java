package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_2_갈수있는곳들 {

	static int N, K, count;
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static StringTokenizer st;
	
	public static class Node{
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = 0;
		for (int r = 0; r < K; r++) {
			st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			
			visit = new boolean[N+1][N+1];
			if (map[startR][startC] == 0) {
				bfs(startR, startC);
			}
			
		}
		
		System.out.println(count);
	}
	
	public static void bfs(int r, int c) {
		visit[r][c] = true;
		map[r][c] = 1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));
		count++;
		
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if (nr>=1 && nc>=1 && nr<=N && nc<=N) {
					if(!visit[nr][nc] && map[nr][nc] == 0) {
						q.add(new Node(nr, nc));
						map[nr][nc] = 1;
						count++;
					}
				}
			}
			
		}
		
	}
}
