package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea1954_달팽이_숫자 {

	static int[][] snail;
	
	static int[] dr = {0, 1, 0, -1}; 
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			snail = new int[N][N];
			
			int x = 0; // 시작 행
			int y = 0; // 시작 열
			int d = 0; // 현재 이동 방향 인덱스
			
			for (int i = 1; i <= N*N; i++) {
				snail[x][y] = i;
				
				int nx = x + dr[d];
				int ny = y + dc[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || snail[nx][ny] != 0) {
					d = (d + 1) % 4;
					
					nx = x + dr[d];
					ny = y + dc[d];
				}
				
				x = nx;
				y = ny;
			}
			System.out.println("#" + t + " ");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
