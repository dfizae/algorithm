package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Bj_2667_단지번호붙이기 {

	static int N, currCnt;
	
	static int[][] apt;
	static boolean[][] visit;
	
	static List<Integer> aptNum; // 단지 당 아파트 개수 담아내는 배열
	
	// 방향 벡터
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
		apt = new int[N][N];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < N; c++) {
				apt[r][c] = line.charAt(c) - '0';
			}
		}
		
		
		aptNum = new ArrayList<>();
		visit = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {	
				// 방문처리를 해줘야하는 이유가 1인 애들이 얼마나 많고, 각 섹터 당 탐색이 끝났는 지 확인하기 위해서
				if(apt[r][c] == 1 && !visit[r][c]) { 
					currCnt = 1; // 현재 단지 개수 / 조건 충족 하면 1을 더해주는게 맞음.
					Dfs(r, c);
					aptNum.add(currCnt);
				}
			}
		}
		
		Collections.sort(aptNum);
		System.out.println(aptNum.size());
		for (int i = 0; i < aptNum.size(); i++) {
			System.out.println(aptNum.get(i));
		}
		
	}
	
	public static void Dfs(int r, int c) {
		visit[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				if(!visit[nr][nc] && apt[nr][nc] == 1) {
					Dfs(nr, nc);
					currCnt++;
				}
			}
		}
	}

}
