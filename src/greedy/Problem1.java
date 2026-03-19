package greedy;

import java.util.Scanner;

public class Problem1 {

	static int T, N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			T = sc.nextInt();
			sc.nextLine();
			N = sc.nextInt();
			M = sc.nextInt();
			sc.nextLine();
			
			long result = pow(N, M);
			System.out.println("#" + t + " " + result);
		}
	}

	static long pow(int N, int M) {
		// 종료조건을 생각할 때 맨 마지막 경우가 무엇인지 생각하자
		if(M == 0) return 1;
		
		//1. 홀수일 때 
		if(M % 2 == 1) {
			return pow(N, (M-1)/2) * pow(N, (M-1)/2) * N;
		} 
		//2. 짝수일 때
		else {
			return pow(N, M/2) * pow(N, M/2);
		}
	}
}
