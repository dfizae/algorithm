package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Swea_7102 {
	
	static int T,N,M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t<=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			// 더한 값들 모아놓은 것
			int[] count = new int[N + M + 1];
			
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=M; j++) {
					int sum = i+j;
					count[sum]++;
				}
			}
			
			int maxFrequency = 0;
			for(int f = 0; f<count.length; f++) {
				if(maxFrequency<count[f]) {
					maxFrequency = count[f];
				}
			}
			
			System.out.println("#" + t + " ");
			
			for(int k = 0; k<count.length; k++) {
				if(count[k] == maxFrequency) {
					System.out.print(k + " ");
				}
			}
			System.out.println();
		}
	}

}
