package SWEA;

import java.io.*;
import java.util.*;

public class Swea_1949_등산로 {
    static int N, K, maxLength;
    static int[][] map;
    static boolean[][] visited;
    // 상, 하, 좌, 우 이동 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            maxLength = 0;
            int maxHeight = 0;

            // 1. 지도 입력 및 최고 봉우리 높이 찾기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            // 2. 가장 높은 모든 봉우리에서 각각 DFS 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false); // 좌표, 현재 길이, 공사 여부, 지나간(잘린) 자리인지 
                        visited[i][j] = false; // 다른 출발점을 위해 초기화
                    }
                }
            }

            // 정답 출력
            System.out.println("#" + t + " " + maxLength);
        }
    }

    static void dfs(int x, int y, int length, boolean isCut) {
        // 최대 등산로 길이를 바꿔줘야 한다. 
        maxLength = Math.max(maxLength, length);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 맵을 벗어나거나 이미 방문한 곳은 패스
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            // 다음 위치가 현재 위치보다 낮을 때
            if (map[nx][ny] < map[x][y]) {
                visited[nx][ny] = true;
                dfs(nx, ny, length + 1, isCut);
                visited[nx][ny] = false;
            } 
            // 다음 위치가 높거나 같지만, 아직 깎을 수 있는 경우 
            else if (!isCut && map[nx][ny] - K < map[x][y]) {
                int originalHeight = map[nx][ny]; // 깎기 전 원래 높이 저장
                
                // 1만 깎아주면 좀 더 길게 갈 수 있다.
                map[nx][ny] = map[x][y] - 1; 
                visited[nx][ny] = true;
                
                dfs(nx, ny, length + 1, true); // 공사 완료(true) 상태로 넘김
                
                visited[nx][ny] = false;
                map[nx][ny] = originalHeight; // 원래 높이로 복구 
            }
        }
    }
}