package dp;
// 피보나치를 호출할 때 음수를 호출하지는 않는다.!

import java.util.Arrays;

public class 동적계획법_01_피보나치 {
	
	static int[] count = new int[100];
	static int[] memo = new int[100];
	public static void main(String[] args) {
//		System.out.println(fibo1(45));
//		System.out.println(Arrays.toString(count));
		
//		Arrays.fill(memo, -1);
//		memo[0] = 0;
//		memo[1] = 1;
		
		System.out.println(fibo2(45));
		
		for (int t = 1; t <=10; t++) {
			//코드작성, 정답출력
		}//t
		
	}	
	
	//재귀함수를 이용한 피보나치(단점이 있어!) -> 중복호출
	static int fibo1(int N) {
		count[N]++;
		if (N < 2) {
			return N; // N이 0일때는 0을, N이 1일때는 1을 반환
		}
		return fibo1(N-1) + fibo1(N-2);
	}

	static int fibo2(int N) {
		if (N >= 2 && memo[N] == 0) {
			memo[N] = fibo2(N-1) + fibo1(N-2);
		}
		return memo[N];
	}
	
	static int fibo3(int N) {
		int[] dp = new int[N+1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <=N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[N];
	}

}
