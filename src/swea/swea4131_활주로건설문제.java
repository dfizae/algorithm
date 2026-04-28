package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4131_활주로건설문제 {
    static int N, X;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<N; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 행과 열을 각자 검사 
            for (int i = 0; i < N; i++) {
                if (canBuild(map[i])) answer++; // 행 검사
                int[] col = new int[N]; // 열은 1차원 배열 인덱스가 고정 그래서 col 1차원 배열로 설정 
                for (int j = 0; j < N; j++) {
                    col[j] = map[j][i];
                }
                if (canBuild(col)) answer++; // 열 검사
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    // 해당 줄에 활주로를 건설할 수 있는지 메서드 
    static boolean canBuild(int[] line) {
        boolean[] hasSlope = new boolean[N]; // 경사로 설치 여부를 담아내는 배열 
        
        for (int i = 0; i < N - 1; i++) { // 높이가 무조건 1이니까 N-1 
            // 1. 활주로 높이가 같으면 평지이므로 계속 진행
            if (line[i] == line[i + 1]) continue;

            // 2. 높이 차이가 1보다 크면 경사로 설치가 안된다. 
            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            // 3. 내리막길인 경우 : 현재 칸 높이 > 다음 칸 높이 
            if (line[i] - 1 == line[i + 1]) {
                for (int j = i + 1; j <= i + X; j++) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 있다면 불가
                    if (j >= N || line[i + 1] != line[j] || hasSlope[j]) return false;
                    hasSlope[j] = true; // 경사로 설치 
                }
            } 
            
            // 4. 오르막길인 경우 : 현재 칸 높이 < 다음 칸 높이
            else if(line[i] + 1 == line[i + 1]) {
            	for(int j = i; j> i - X; j--) {
            		// 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 있다면 불가
                    if (j < 0 || line[i] != line[j] || hasSlope[j]) return false;
                    hasSlope[j] = true; // 경사로 설치
            	}
            }
            
        }
        return true; // 무사히 끝까지 도달했다면 활주로 건설 가능
    }
}