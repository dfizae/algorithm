package bfs;

import java.util.Scanner;

public class SWEA_5215_햄버거다이어트 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int L = sc.nextInt();
			int[] score = new int[N+1];
			int[] cal = new int[N+1];
			for (int i = 1; i <= N; i++) {
				score[i] = sc.nextInt();
				cal[i] = sc.nextInt();
			}
			
			//N개의 재료이고 제한은 L만큼의 무게(제한)
			int[][] dp = new int[N+1][L+1];
			for (int i = 1; i<=N; i++) {
				//j 임시칼로리(무게)
				for (int j = 0; j <= L; j++) {
					if (j < cal[i]) //그재료 못쓴다잉(고려할수 없어)
						dp[i][j] = dp[i-1][j];
					else
						dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cal[i]+score[i]]);
				} //무게별 최고의 맛을 가린다
			}//N개의 재료를 고려한다.
			
			System.out.println("#" + tc + " " + dp[N][L]);
		}

	}

}
