package dfs;

import java.io.*;
import java.util.*;

public class swea1798범준이의여행계획 {
    
    // 관광지, 호텔, 공항의 정보를 담는 클래스
    static class Point {
        int type; // 0: 공항(A), 1: 관광지(P), 2: 호텔(H)
        int playTime; // 노는 시간
        int satis; // 만족도
        int id; // 노드 번호
        
        public Point(int type, int id, int playTime, int satis) {
            this.type = type;
            this.id = id;
            this.playTime = playTime;
            this.satis = satis;
        }
    }

    static int N, M; // N: 지점 개수, M: 여행 일수
    static int[][] dist; // 이동 시간 배열
    static Point[] points; // 각 지점 정보
    static List<Integer> hotels; // 호텔들의 노드 번호 리스트
    static int airport; // 공항의 노드 번호
    
    static int maxSatis; // 최대 만족도
    static List<Integer> bestPath; // 최적의 경로
    static boolean[] visited; // 관광지 방문 여부

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N + 1][N + 1];
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = i + 1; j <= N; j++) {
                    dist[i][j] = dist[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            points = new Point[N + 1];
            hotels = new ArrayList<>();
            maxSatis = 0;
            bestPath = new ArrayList<>();
            visited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                String typeStr = st.nextToken();
                int type = typeStr.equals("A") ? 0 : typeStr.equals("P") ? 1 : 2;
                
                if (type == 0) {
                    airport = i;
                    points[i] = new Point(type, i, 0, 0);
                } else if (type == 2) {
                    hotels.add(i);
                    points[i] = new Point(type, i, 0, 0);
                } else {
                    int playTime = Integer.parseInt(st.nextToken());
                    int satis = Integer.parseInt(st.nextToken());
                    points[i] = new Point(type, i, playTime, satis);
                }
            }

            // 탐색 시작: 1일차, 공항에서 출발, 사용시간 0, 누적 만족도 0, 빈 경로
            dfs(1, airport, 0, 0, new ArrayList<>());

            // 정답 출력 포맷
            System.out.print("#" + t + " " + maxSatis + " ");
            for (int p : bestPath) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }

    // DFS 완전 탐색 및 백트래킹
    static void dfs(int day, int curr, int timeSpent, int satis, List<Integer> path) {
        // 1. 마지막 날(M일차)인 경우: 무조건 공항으로 가야 함
        if (day == M) {
            int toAirport = dist[curr][airport];
            // 공항까지 갈 시간이 충분하다면 최댓값 갱신
            if (timeSpent + toAirport <= 540) {
                if (satis > maxSatis) {
                    maxSatis = satis;
                    bestPath = new ArrayList<>(path);
                    bestPath.add(airport);
                }
            }
        } 
        // 2. 마지막 날이 아닌 경우: 남은 시간 내에 호텔로 가야 함
        else {
            for (int h : hotels) {
                int toHotel = dist[curr][h];
                if (timeSpent + toHotel <= 540) {
                    path.add(h);
                    // 호텔에 도착했으므로 다음 날(day+1)로 넘어가고, 시간은 0으로 초기화
                    dfs(day + 1, h, 0, satis, path);
                    path.remove(path.size() - 1); // 백트래킹
                }
            }
        }

        // 3. 현재 날짜에 아직 방문하지 않은 관광지 탐색 (공통 로직)
        for (int i = 1; i <= N; i++) {
            if (points[i].type == 1 && !visited[i]) { // 관광지이고 미방문 상태라면
                int nextTime = timeSpent + dist[curr][i] + points[i].playTime;
                
                // 가지치기(Pruning): 해당 관광지를 들렀을 때, 540분을 초과하면 아예 가지 않음
                // (엄밀히는 이후에 호텔이나 공항으로 갈 수 있는 최소 시간까지 고려해야 더 빠릅니다)
                if (nextTime <= 540) {
                    visited[i] = true;
                    path.add(i);
                    
                    dfs(day, i, nextTime, satis + points[i].satis, path);
                    
                    path.remove(path.size() - 1); // 백트래킹
                    visited[i] = false;
                }
            }
        }
    }
}