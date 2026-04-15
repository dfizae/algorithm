package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Bj_2447_별찍기 {
	
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 별찍기 맵
		map = new char[N][N];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = ' '; // 빈칸으로 초기화
			}
		}
		
		pointStar(0, 0, N);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map[i]).append('\n');
		}
		
		System.out.print(sb.toString());
		
	}
	
	public static void pointStar(int r, int c, int n) {
		
		// 종료 조건이 n이 1일때까지 일듯
		if(n == 1) {
			map[r][c] = '*';
			return;
		}
		
		int size = n / 3;
		int count = 0; // 빈 곳이 들어갈 순서는 5번째 애들을 위한 카운트
		// 재귀
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				count++;
				if(count == 5) continue; // 5번째 사각형은 빈칸을 만들기 위한 작업
				pointStar(r + (i*size), c + (j*size), size);
			}
		}
		
	} 
	
}
