package baekjoon;
import java.util.Scanner;

public class Bj_15469nm1 {

	static int N, M;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		visited = new boolean[N];
		arr = new int[M];
		Dfs(N, M, 0);
		System.out.println(sb);
	}
	
	static void Dfs(int N, int M, int depth) {
		if(depth == M) {
			for(int val : arr) {
				sb.append(val).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;	// (2, 2)  (1, 1) 방지			
				arr[depth] = i + 1; 
				Dfs(N, M, depth+1);
				visited[i] = false;
			}
		}
	}

}
