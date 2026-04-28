package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Swea_1225 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int t = 1; t<=10; t++) {
			
			int N = sc.nextInt();
			sc.nextLine();
			// 숫자 사이클 : 5 감소 후 다시 1감소
			
			// 큐에 입력 숫자 입력  
			Queue<Integer> q = new LinkedList<>();
			for(int i = 1; i<=8; i++) {
				q.add(sc.nextInt());
			}
			
			// 뒤에 숫자를 앞으로 이동 하면서 1~5 빼기 (사이클 형성) 
			while(true) {
				boolean ok = true;
				for(int i = 1; i<=5; i++) {
					int first = q.poll() - i;
					if(first <= 0) {
						first = 0;
						ok = false;
					}
					q.offer(first);
					if(!ok) {
						break;
					}
				}
				if(!ok) {
					break;
				}
			}
			
			System.out.print("#" + N + " ");
			for(int i = 1; i<=8; i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
		
	}

}
