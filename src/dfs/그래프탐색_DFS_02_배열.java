package dfs;

import java.util.Scanner;

public class 그래프탐색_DFS_02_배열 {

	static int N; //2차원 정사각 배열이 주어진다.
	static int[][] map; //2차원 배열
	static boolean[][] visited; //방문처리
	static boolean ans; //탈출 여부
	
	//델타 상하좌우 순서를 쓰자
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N]; //원본을 벽으로 바꿔버릴꺼야 라고 하면 안써도 된다.
		ans = false;
		
		//문제에서 입력 받으면서 입구,출구정보를 확보해두기도 한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}//미로입력
		
		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int r, int c) {
		if (r == N - 1 && c == N -1) { // 미로 크기의 출구에 도달했을 때 종료
			ans = true;
			return;
		}
		
		visited[r][c] = true; // 벽으로 해도 좋다.
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dr[d];
			
			//1. 범위를 벗어났는지
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			//2. 벽인지 체크
			if (map[nr][nc] == 1) continue;
			//3. 이미 방문한적이 있나 체크
			if(visited[nr][nc]) continue;
			
			dfs(nr, nc);
		}
	}
	
}
