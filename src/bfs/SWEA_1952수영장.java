package bfs;

import java.util.Scanner;

public class SWEA_1952수영장 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int d = sc.nextInt();
			int m1 = sc.nextInt();
			int m3 = sc.nextInt();
			int y = sc.nextInt();
			
			int[] month = new int[13]; // 각 달의 이용계획
			for (int i = 1; i < 13; i++) {
				month[i] = sc.nextInt();
			} //입력끝
			
			int[] dp = new int[13];
			dp[1] = Math.min(m1, month[1] * d);
			dp[2] = dp[1] + Math.min(m1, month[2] * d);
			
			for (int i = 3; i < 13; i++) {
				dp[i] = Math.min(dp[i-3] + m3, Math.min(dp[i-1]+m1, dp[i-1] + month[i]*d));
				
			}
			
			
			System.out.println("#" + tc + " " + Math.min(dp[12], y));
			
			
		}//tc
	}//main
}
