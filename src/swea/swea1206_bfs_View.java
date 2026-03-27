package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1206_bfs_View {

	static int N;
	static int[] building;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			building = new int[N];
			for (int i = 0; i < N; i++) {
				building[i] = Integer.parseInt(st.nextToken());
			}
			
			int totalView = 0;
			
			for (int i = 2; i < N-2; i++) {
				int leftMax = Math.max(building[i-2], building[i-1]);
				int rightMax = Math.max(building[i+1], building[i+2]);
				int maxNeighbor = Math.max(leftMax, rightMax);
				
				if(building[i] > maxNeighbor) {
					totalView += (building[i] - maxNeighbor);
				}
			}
			
			
			System.out.println("#" + t + " " + totalView);
		}
		
		
		
	}

}
