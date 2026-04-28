package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r, c;
    int l; // 걸린 시간 
    
    public Point(int r, int c, int l) {
        this.r = r;
        this.c = c;
        this.l = l;
    }
}

public class swea1953_탈주범검거 {
    
    static int N, M, R, C, L;
    
    static int[][] board;
    static boolean[][] visit;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    // 터널 배열  	
    static int[][] tunnel = {
        {},              // 0: 터널 없음
        {0, 1, 2, 3},    // 1: 상하좌우 
        {0, 2},          // 2: 상하 
        {1, 3},          // 3: 좌우 
        {0, 1},          // 4: 상, 우 
        {1, 2},          // 5: 우, 하 
        {2, 3},          // 6: 하, 좌 
        {0, 3}           // 7: 상, 좌
    };
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            
            board = new int[N][M];
            visit = new boolean[N][M];
            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < M; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            int ans = bfs(R, C, L);
            System.out.println("#" + t + " " + ans);
        }
    }
    
    // 시작점 r, c, 소요 시간 (소요 시간이 L이 되면 멈추게 조건을 주자)
    static int bfs(int r, int c, int l) {
        Queue<Point> q = new ArrayDeque<>();
        visit[r][c] = true;
        q.add(new Point(r, c, 1));
        
        int count = 1;
        
        while(!q.isEmpty()) {
            Point curr = q.poll();
            
            // 현재 걸린 시간이 L과 같다면 진행 불가 
            if(curr.l >= l) continue;
            
            // 무조건 4방향 탐색을 하는게 아니라, 현재 위치의 파이프가 갈 수 있는 방향만 탐색 (파이프의 방향은 종류에 따라 다르니까 )
            int currentPipe = board[curr.r][curr.c];
            
            for(int d : tunnel[currentPipe]) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                
                // 맵 범위 이탈 체크
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                // 이미 방문했거나, 다음 칸에 파이프가 없는 경우
                if(visit[nr][nc] || board[nr][nc] == 0) continue;
                
                // 다음 도달 파이프 
                int nextPipe = board[nr][nc];
                
                // 지금 파이프가 향하는 방향의 정반대 방향
                int oppositeDir = (d + 2) % 4;
                boolean canConnect = false; // 둘이 마주치는 방향인지 
                
                // 다음 파이프와 내가 온 방향이 마주치는 것인지 확인 
                for(int nd : tunnel[nextPipe]) {
                    if(nd == oppositeDir) {
                        canConnect = true;
                        break;
                    }
                }
                
                // 연결될 수 있다면 큐에 추가하고 방문 처리
                if(canConnect) {
                    visit[nr][nc] = true;
                    q.add(new Point(nr, nc, curr.l + 1));
                    count++;
                }
            }
        }
        
        return count; // 최종 도달 가능한 위치의 개수 반환
    }
}