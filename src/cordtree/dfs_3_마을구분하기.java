package cordtree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class dfs_3_마을구분하기 {

	static int N;
	
	static int[][] town;
	static boolean[][] visit;
	
	static int peopleCount;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		town = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				town[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Integer> townList = new ArrayList<>();
	
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(town[r][c] == 1 && !visit[r][c]) {
					peopleCount = 1;
					visit[r][c] = true;
					dfs(r, c);
					townList.add(peopleCount);
				}
			}
		}
		
		Collections.sort(townList);
		
		System.out.println(townList.size());
		
		for(int count : townList) {
			System.out.println(count);
		}
		
	}
	
	
	static void dfs(int r, int c) {
		
		int currR = r;
		int currC = c;
	
		
		
		for (int d = 0; d < 4; d++) {
			int nr = currR + dr[d];
			int nc = currC + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visit[nr][nc]) {
				continue;
			}
			
			if (town[nr][nc] == 1) {
				visit[nr][nc] = true;
				peopleCount++;
				dfs(nr, nc);
			}
		}
	}
	

}
