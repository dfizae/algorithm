package dp;

import java.util.Scanner;

public class 동적계획법_03_배낭문제 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 물건의 개수
		int W = sc.nextInt(); // 배낭의 무게(제한)
		
		//저장 (1차원 배열 2개로, 2차원 배열로)
		int[] weights = new int[N+1];
		int[] cost = new int[N+1];
	
		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			cost[i] = sc.nextInt();
		}
		
		int[][] dp = new int[N+1][W+1];
		
		//물건은 1개씩만 존재
		for (int i = 1; i <= N; i++) {
			for (int w = 0; w <= W; w++) {
				//1. 내가 고려할 물건(i번)의 무게가 임시무게(w)보다 작은 경우에는
				if(weights[i] > w) {
					dp[i][w] = dp[i-1][w];
				}
				//2. 내가 고려할 물건(i번)의 무게가 임시무게(w)보다 작거나 같은 경우에는
				else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i]]+cost[i]);
				}
			}
		}// 고려할 물건은 한개씩 늘리겠다.
		
	} 

}
