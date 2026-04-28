package bfs;

import java.io.*;
import java.util.*;

public class bfs {
    static int N, M;
    static int[][] maze;
    static boolean[][] visited;
    
    // 상, 하, 좌, 우 이동 배
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 다른 입력 방식을 연습 
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        maze = new int[N][M];
        visited = new boolean[N][M];
        
        // 미로 입력 받기
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }
        
        // BFS 탐색 시작 (배열 인덱스는 0부터 시작하므로 0, 0)
        bfs(0, 0);
        
        // 도착지에 저장된 최단 거리 출력
        System.out.println(maze[N-1][M-1]);
    }

    static void bfs(int x, int y) {
        // BFS를 위한 큐 생성 (좌표를 담기 위해 int 배열 사용)
        Queue<int[]> queue = new LinkedList<>();
        
        // 시작점을 큐에 넣어주고, 방문도 확인해주기 
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        
        // 큐가 빌 때까지 반복
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            
            // 현재 위치에서 4가지 방향으로 위치 확인
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                // 1. 미로 범위를 벗어나지 않고
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    // 2. 이동할 수 있는 칸이고, 아직 방문하지 않았다면
                    if(maze[nx][ny] != 0 && !visited[nx][ny]) {
                        // 큐에 넣어주고 방문을 확인시켜준다. 
                    	queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        
                        // 이전 칸의 거리에  1을 더해서 최단 거리를 누적해 나간다. 
                        maze[nx][ny] = maze[cx][cy] + 1;
                    }
                }
            }
        }
    }
}
