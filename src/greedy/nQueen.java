package greedy;

import java.util.Scanner;

public class nQueen {

	static int N; 
	static int ans;
	static int[] col; // col[i] = j : i번째 행의 j번째 열에 퀸이 위치함
							  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			col = new int[N];
			ans = 0;
			
			make(0);
			
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
	
	static void make(int row) {
		if (row == N) {
			ans++;
			return; 
		}
		
		for (int i = 0; i < N; i++) {
			col[row] = i; 
			
			if (isPossible(row)) {
				make(row + 1); 
			}
		}
	}
	
	static boolean isPossible(int row) {
		for (int i = 0; i < row; i++) {
			// 같은 열에 다른 퀸이 있으면 불가능
			if (col[i] == col[row]) {
				return false;
			}
			// 대각선에 다른 퀸이 있으면 불가능
			if (Math.abs(row - i) == Math.abs(col[row] - col[i])) {
				return false;
			}
		}
		return true;
	}
}