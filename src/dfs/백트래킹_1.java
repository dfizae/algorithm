package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백트래킹_1 {

	static int[] gyuYoung = new int[9];  
	static int[] currentDeck = new int[9];   
	static boolean[] isVisited = new boolean[9]; 
	static List<Integer> inYoung = new ArrayList<>(); 
	
	static int winCnt, loseCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			inYoung.clear(); 
			winCnt = 0;
			loseCnt = 0;
			isVisited = new boolean[9];
			
			boolean[] isUsed = new boolean[19];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 9; i++) {
				gyuYoung[i] = Integer.parseInt(st.nextToken());
				isUsed[gyuYoung[i]] = true; 
			}
			
			// 규영이가 안 가져간 남은 카드 9장을 인영이 덱에 추가
			for(int i = 1; i <= 18; i++) {
				if(!isUsed[i]) {
					inYoung.add(i);
				}
			}
			
			// 백트래킹 시작
			dfs(0);

			System.out.println("#" + t + " " + winCnt + " " + loseCnt);
		}
	}

	static void dfs(int depth) {
		// 종료 조건 -> 인영이의 카드 9장 순서가 모두 정해졌을 때
		if(depth == 9) {
			int sumG = 0; // 이번 판 규영이 점수
			int sumI = 0; // 이번 판 인영이 점수
			
			// 9라운드에 걸쳐 두 사람의 카드를 비교하여 점수 계산
			for(int i = 0; i < 9; i++) {
				if(gyuYoung[i] > currentDeck[i]) {
					sumG += (gyuYoung[i] + currentDeck[i]);
				} else if (gyuYoung[i] < currentDeck[i]) {
					sumI += (gyuYoung[i] + currentDeck[i]);
				}
			}
			
			// 총점에 따른 규영이의 승/패 카운트 누적
			if(sumG > sumI) {
				winCnt++;
			} else if (sumG < sumI) {
				loseCnt++;
			}
			return; // 판 종료
		} 

		for(int i = 0; i < 9; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				
				currentDeck[depth] = inYoung.get(i); 
			
				dfs(depth + 1);
				
				// 백트래킹
				isVisited[i] = false; 
			}
		}
	}
}