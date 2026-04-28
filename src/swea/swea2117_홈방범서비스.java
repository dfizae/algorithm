package SWEA;

import java.util.Scanner;

public class swea2117_홈방범서비스  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt(); // 하나의 집이 지불하는 비용
            int[][] map = new int[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = sc.nextInt();
                }
            }

            int ans = 0;

            for (int K = 1; K <= N + 1; K++) {
                int cost = K * K + (K - 1) * (K - 1); // 운영 비용 계산  
                
                // 지도의 모든 칸을 서비스의 중심 좌표로 설정하고 반복 
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        int count = 0; // 해당 영역 안에 들어온 집의 개수
                        
                        // 마름모 범위 탐색
                        for (int dr = -(K - 1); dr <= (K - 1); dr++) {
                            for (int dc = -(K - 1); dc <= (K - 1); dc++) {
                            	// 중심에서 특정 칸까지 대각선으로 가로지르지 않고, 오직 상하좌우로만 걸어갈 때 총 몇 칸인지 필요한지를 계산
                                // 거리가 K-1 안에 있는 칸들이 마름모 영역에 속한다 라는 조건 
                            	if (Math.abs(dr) + Math.abs(dc) <= K - 1) {
                                    int nr = r + dr;
                                    int nc = c + dc;
                                    
                                    // 탐색하는 곳이 지도 밖으로 벗어나지 않았는지 확인
                                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                        count += map[nr][nc]; // 집이 있다면 count 증가
                                    }
                                }
                            }
                        }

                        // 찾은 집의 개수 x M = 회사의 총 수익
                        // 총 수익이 운영 비용보다 크거나 같으면 손해를 보지 않으 정답 최대 집의 수를 갱신해준다
                        if (count * M >= cost) {
                            ans = Math.max(ans, count);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + ans);
        }
        sc.close();
    }
}