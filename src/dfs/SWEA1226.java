package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1226 {

	static int N;
	static int[][] maze;
	static boolean[][] visited;
	static int result;

	// 방향 델타
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			br.readLine(); // 테스트 케이스 읽어서 버려야했다.
			
			// 미로 입력
			maze = new int[16][16];
			visited = new boolean[16][16];

			for (int i = 0; i < maze.length; i++) {
				String line = br.readLine();
				for (int j = 0; j < maze[i].length; j++) {
					maze[i][j] = line.charAt(j) - '0';
				}
			}

			result = 0;
			dfs(1, 1);
			System.out.println("#" + t + " " + result);
		}

	}

	static void dfs(int r, int c) {

		// 도착지점에 도달했을 때
		if (maze[r][c] == 3) {
			result = 1;
			return;
		}

		// 방문처리
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr > 15 || nc < 0 || nc > 15)
				continue;
			if (visited[nr][nc])
				continue;
			if (maze[nr][nc] == 1)
				continue;

			dfs(nr, nc);
		}
	}
}
