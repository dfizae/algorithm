package bfs;

import java.io.BufferedReader; 

import java.io.InputStreamReader; 

import java.util.LinkedList; 

import java.util.Queue; 

import java.util.StringTokenizer; 

  

public class 벽돌 { 

    static int N, W, H; 
    static int minBricks; 

    // 상하좌우 탐색을 위한 배열 
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; 

    // 폭발할 벽돌의 정보를 담을 클래스 
    static class Brick { 
        int r, c, range; 
        public Brick(int r, int c, int range) { 
            this.r = r; 
            this.c = c; 
            this.range = range; // 벽돌에 적힌 숫자 
        } 
    } 

  

    public static void main(String[] args) throws Exception { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int T = Integer.parseInt(br.readLine().trim()); 

        for (int t = 1; t <= T; t++) { 

            StringTokenizer st = new StringTokenizer(br.readLine()); 
            N = Integer.parseInt(st.nextToken()); 
            W = Integer.parseInt(st.nextToken()); 
            H = Integer.parseInt(st.nextToken()); 
             
            int[][] map = new int[H][W]; 
            for (int r = 0; r < H; r++) { 
                st = new StringTokenizer(br.readLine()); 
                for (int c = 0; c < W; c++) { 
                    map[r][c] = Integer.parseInt(st.nextToken()); 
                } 
            } 

            minBricks = Integer.MAX_VALUE; // 최소 남은 벽돌 개수 초기화 

            // 재귀 시작 (떨어뜨린 구슬 개수, 현재 맵의 상태) 
            dropMarble(0, map); 
            System.out.println("#" + t + " " + minBricks); 
        } 
    } 

  

    // 1. 구슬 떨어뜨리기 (중복 순열, 백트래킹) 
    static void dropMarble(int count, int[][] map) { 

        // 이미 남은 벽돌이 0개라면 더 이상 탐색할 필요 없음 
        if (minBricks == 0) return; 

        // 구슬 N개를 모두 던졌을 때 
        if (count == N) { 
            int remain = countBricks(map); 
            minBricks = Math.min(minBricks, remain); 
            return; 
        } 

        // 0번 열부터 W-1번 열까지 구슬 떨어뜨려 보기 
        for (int c = 0; c < W; c++) { 
            // 맵 초기화 다음 탐색때 지장이 안가게 하기 위함
            int[][] copyMap = new int[H][W]; 
            for (int i = 0; i < H; i++) { 
                copyMap[i] = map[i].clone(); 
            } 

            // 구슬이 처음 닿는 벽돌 찾기 
            int r = 0; 
            while (r < H && copyMap[r][c] == 0) { 
                r++; 
            } 

            // 해당 열에 벽돌이 없는 경우 
            if (r == H) { 
                dropMarble(count + 1, copyMap); 
            } else { 
                // 해당 열에 벽돌이 있는 경우 
                explode(r, c, copyMap); // 2. 연쇄 폭발 
                applyGravity(copyMap);  // 3. 빈 공간 채워주기
                dropMarble(count + 1, copyMap); // 다음 구슬 던지기 
            } 
        } 
    } 

  

    // 2. 연쇄 폭발 (BFS) 
    static void explode(int startR, int startC, int[][] map) { 

        Queue<Brick> q = new LinkedList<>(); 
        // 폭발 범위가 1보다 큰 벽돌만 큐에 넣는다.
        if (map[startR][startC] > 1) { 
            q.offer(new Brick(startR, startC, map[startR][startC])); 
        } 
        map[startR][startC] = 0; // 시작 벽돌 부수기 
        while (!q.isEmpty()) { 
            Brick b = q.poll(); 
            // 벽돌에 적힌 숫자(range) - 1 만큼 상하좌우로 퍼짐 
            for (int i = 1; i < b.range; i++) { 
                for (int d = 0; d < 4; d++) { 
                    int nr = b.r + dr[d] * i; 
                    int nc = b.c + dc[d] * i; 
                     
                    // 맵 범위 내에 있고, 벽돌이 존재하는 경우 
                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) { 
                        if (map[nr][nc] > 1) { 
                            q.offer(new Brick(nr, nc, map[nr][nc])); 
                        } 
                        map[nr][nc] = 0; // 연쇄 폭발된 벽돌 부수기 
                    } 
                } 
            } 
        } 
    } 

    // 3. 빈 공간에 벽돌 내리기

    static void applyGravity(int[][] map) { 
        for (int c = 0; c < W; c++) { 
            int emptyRow = H - 1; // 가장 밑바닥부터 채워 올라감 
            for (int r = H - 1; r >= 0; r--) { 
                if (map[r][c] != 0) { // 벽돌을 발견하면 
                    int temp = map[r][c]; 
                    map[r][c] = 0; // 원래 있던 자리는 비우고 
                    map[emptyRow][c] = temp; // 채워야 할 위치로 내림 
                    emptyRow--; // 다음 채울 위치는 한 칸 위로 
                } 

            } 

        } 

    } 

    // 남은 벽돌 개수 카운트 
    static int countBricks(int[][] map) { 
        int count = 0; 
        for (int r = 0; r < H; r++) { 
            for (int c = 0; c < W; c++) { 
                if (map[r][c] > 0) count++; 
            } 
        } 
        return count; 
    } 
} 