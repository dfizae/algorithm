package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1868_지뢰찾기 {

    static int N;
    static char[][] gameField;
    static boolean[][] visited;
    static int[][] mineCount; 
    
    // 8방향 (상하좌우 및 대각선)
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            
            gameField = new char[N][N];
            mineCount = new int[N][N];
            visited = new boolean[N][N];
            
            // 입력 받기
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    gameField[i][j] = line.charAt(j);
                }
            }
            
            // 모든 칸의 주변 지뢰 개수 미리 세기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 지뢰가 아닌 빈 칸일 때만 주변 탐색
                    if (gameField[i][j] == '.') {
                        int count = 0;
                        for (int d = 0; d < 8; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                if (gameField[nr][nc] == '*') {
                                    count++;
                                }
                            }
                        }
                        mineCount[i][j] = count;
                    }
                }
            }
            
            int clickCount = 0; // 최소 클릭 횟수
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 지뢰가 아니고, 주변 지뢰 개수가 0이며, 아직 방문하지 않은 칸
                    if (gameField[i][j] == '.' && mineCount[i][j] == 0 && !visited[i][j]) {
                        clickCount++; // 클릭 횟수 1 증가
                        bfs(i, j);    // bfs를 호출하여 연쇄적으로 주변을 모두 열어준다. (방문 처리를 시키기 위함)
                    } 
                }
            }
            
            // 지뢰가 아니지만 주변 지뢰 개수가 0이 아닌 경우에는 연속으로 값이 공개가 되지 않기에 클릭을 일일이 해준다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 지뢰가 아니고, 주변 지뢰 개수가 0이며, 아직 방문하지 않은 칸
                	if(gameField[i][j] == '.' && !visited[i][j]) {
                    	clickCount++;
                    	visited[i][j] = true;
                	} 
                }
            }
            
        
            
            
            // 결과 출력
            System.out.println("#" + t + " " + clickCount);
        }
    }
    
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    // 연쇄 폭발을 구현하는 BFS 메서드
    static void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            Point curr = q.poll();
            
            for (int d = 0; d < 8; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                
                // 맵 범위 내에 있고, 아직 방문하지 않았고, 지뢰가 아닌 빈 칸이라면
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && gameField[nr][nc] == '.') {
                    visited[nr][nc] = true; // 무조건 방문(열림) 처리
                    
                    // 핵심: 방금 열린 칸도 주변 지뢰가 0개라면 그 칸도 연쇄 폭발을 일으켜야 한다.
                    if (mineCount[nr][nc] == 0) {
                        q.add(new Point(nr, nc)); 
                    }
                    // 0이 아니라면 큐에 넣지 않음 -> 연쇄 폭발이 거기서 멈추고 숫자만 보여줌
                }
            }
        }    
    }
}