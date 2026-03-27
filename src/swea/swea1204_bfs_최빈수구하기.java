package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1204_bfs_최빈수구하기 {
	
	static int[] score;
	static int[] number;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 전체 테스트 케이스 수
		int maxProp = 0;
		
		for (int t = 1; t <= T; t++) {
			int test_case = Integer.parseInt(br.readLine()); // 부분 테스트 케이스 번호 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			score = new int[1000];
			number = new int[101];
			for (int i = 0; i < 1000; i++) {	
				score[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < score.length; i++) {
				int currScore = score[i];
				number[currScore] += 1;
			}
			
			int maxFrequency = 0;
			int result = 0; // 제일 큰 최빈수
			
			for (int i = 0; i < number.length; i++) {
				if(number[i] >= maxFrequency) {
					maxFrequency = number[i];
					result = i;
				}
			}
			
			System.out.println("#" + test_case + " " + result);
		}

	}

}
