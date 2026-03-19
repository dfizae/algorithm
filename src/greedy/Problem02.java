package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem02 {

	static int[] currency = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			int curr = N;
			int[] result = new int[currency.length];
			for(int i = 0; i<currency.length; i++) {
				result[i] = curr / currency[i];
				curr = curr % currency[i];
			}
			
		StringBuilder sb = new StringBuilder();
		sb.append("#").append(t).append("\n");
		
		for(int num : result) {
			sb.append(num).append(" ");
		}
		
		System.out.println(sb.toString());
		}
	}

}
