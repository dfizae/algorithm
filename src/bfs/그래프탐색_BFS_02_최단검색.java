package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 그래프탐색_BFS_02_최단검색 {
	
	static int N; // NxN 크기의 2차원 배열
	static int[][] map; // 2차원 배열 (미로)
	static boolean[][] visit; // 방문 처리
	
	// 상하좌우 4방향 탐색
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Pos {
		int r, c, dist;
		
		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visit = new boolean[N][N]; // 기본값 false로 초기화됨
		
		/* * 큐(Queue)에 넣는 Pos 객체 내부에서 거리를 계산하므로
		 * 불필요했던 dist 배열 초기화 코드는 삭제했습니다.
		 */
		
		// 입력 (예: 0이 이동 가능, 1이 벽)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 미로 입력 완료!
		
		// (0, 0)에서 출발
		int ans = bfs(0, 0);
		
		System.out.println(ans);
	}

	static int bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		
		// 시작점 큐에 삽입 (시작점의 거리를 0 또는 1로 설정. 여기선 0으로 시작)
		q.add(new Pos(r, c, 0));
		visit[r][c] = true;
		
		while(!q.isEmpty()) {
			// int[]가 아닌 Pos 객체로 꺼내야 합니다.
			Pos curr = q.poll();
			
			// 도착 지점 확인
			if (curr.r == N-1 && curr.c == N-1) {
				return curr.dist; // 현재 객체가 가지고 있는 누적 거리를 반환
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
			
				// 1. 범위 체크
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				// 2. 벽인지 체크 (보통 1을 벽으로 가정)
				if(map[nr][nc] == 1) continue;
				// 3. 이미 방문했는지 체크
				if(visit[nr][nc]) continue;
				
				// 쳐내질 건 쳐내졌어! -> 방문 처리 후 큐에 삽입
				visit[nr][nc] = true;
				// 이전 위치의 거리(curr.dist)에 +1을 해서 큐에 넣음
				q.add(new Pos(nr, nc, curr.dist + 1));
			}
		}
		
		// 도착할 수 없는 경우 -1 반환
		return -1;
	}
}