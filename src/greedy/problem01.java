package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem01 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			long N = Long.parseLong(br.readLine().trim());
			
			int cnt = 0;
			long curr = N;
			while(curr > 2) {
				long root = (long)Math.sqrt(curr);
				if(root * root == curr) {
					curr = root;
					cnt++;
				} else {
					long nextRoot = root + 1; // 루트 제곱이 curr하고 안맞으면 +1 시키기
					long nextSquare = nextRoot * nextRoot; // nextRoot 제곱은 현재 curr보다 큰 다음 완전제곱근 수
					long diff = nextSquare - curr; //  그 완전제곱근 수를 현재 수랑 빼주면 그것이 카운트다. 
					
					cnt += diff;
					curr = nextSquare;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}

}
