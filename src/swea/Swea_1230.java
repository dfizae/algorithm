package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Swea_1230 {
	
	static List<Integer> pw;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			pw = new LinkedList<>();
			
			// 1. 암호문 뭉치 한 줄 읽기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				pw.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			// 2. 명령어 뭉치 한 줄 읽기
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < M; i++) {
				String cmd = st.nextToken();
				if(cmd.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0; j < y; j++) {
						int s = Integer.parseInt(st.nextToken());
						pw.add(x + j, s);
					}
				} else if(cmd.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0; j < y; j++) {
						pw.remove(x);
					}
				} else if(cmd.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					for(int j = 0; j < y; j++) {
						int s = Integer.parseInt(st.nextToken());
						pw.add(s);
					}
				}
			}
			
			System.out.print("#" + t + " ");
			// 3. 처음 10개만 출력하기
			for(int i = 0; i < 10; i++) {
				System.out.print(pw.get(i) + " ");
			}
			System.out.println();
		}
	}
}