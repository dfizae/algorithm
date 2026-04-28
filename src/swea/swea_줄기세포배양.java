package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_줄기세포배양 {
    // 줄기세포의 상태와 시간 정보를 담는 클래스
    static class Cell {
        int r, c;
        int life;         // 생명력 
        int timeToActive; // 비활성 -> 활성까지 남은 시간
        int timeToDead;   // 활성 -> 죽음까지 남은 시간
        int state;        // 0: 비활성, 1: 활성
        
        public Cell(int r, int c, int life) {
            this.r = r;
            this.c = c;
            this.life = life;
            this.timeToActive = life; // 처음엔 생명력만큼 비활성 시간 필요
            this.timeToDead = life;   // 활성 상태가 된 후 생명력만큼 살아있음
            this.state = 0;           // 초기 상태는 비활성화 상태 
        }
    }

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken()); // 배양 시간

            boolean[][] visited = new boolean[700][700];
            Queue<Cell> q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        // 중앙(300, 300)을 기점으로 배치하여 배열 인덱스 초과 방지
                        int r = i + 300;
                        int c = j + 300;
                        visited[r][c] = true;
                        q.add(new Cell(r, c, life));
                    }
                }
            }

            // K시간 동안 시뮬레이션 진행
            for (int time = 0; time < K; time++) {
                int size = q.size();
                
                // 이번 턴에 번식 할 예정인 세포들을 담는 우선순위 큐 (생명력 내림차순 정렬)
                PriorityQueue<Cell> breedingPq = new PriorityQueue<>((a, b) -> b.life - a.life);
                Queue<Cell> nextQ = new LinkedList<>(); // 다음 턴까지 살아남은 세포들

                for (int i = 0; i < size; i++) {
                    Cell c = q.poll();
                    
                    if (c.state == 0) { // 1. 비활성 상태인 경우
                        c.timeToActive--;
                        if (c.timeToActive == 0) {
                            c.state = 1; // 활성 상태로 전환
                        }
                        nextQ.add(c); // 아직 살아있으므로 다음 큐에 추가
                        
                    } else if (c.state == 1) { // 2. 활성 상태인 경우
                        // 활성 상태가 된 첫 1시간 동안 번식
                        if (c.timeToDead == c.life) {
                            breedingPq.add(c);
                        }
                        
                        c.timeToDead--;
                        
                        if (c.timeToDead > 0) {
                            nextQ.add(c); // 아직 수명이 남았으면 다음 큐에 추가
                        } 
                        // timeToDead == 0이면 죽은 상태가 되므로 nextQ에 넣지 않음
                    }
                }

                // 3. 번식 처리 (생명력이 높은 세포부터 먼저 퍼짐)
                while (!breedingPq.isEmpty()) {
                    Cell c = breedingPq.poll();
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = c.r + dr[d];
                        int nc = c.c + dc[d];
                        
                        // 아직 다른 세포가 차지하지 않은 빈 공간이라면
                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true; // 방문(차지) 처리
                            nextQ.add(new Cell(nr, nc, c.life)); // 새로운 세포 생성
                        }
                    }
                }
                
                q = nextQ; // 큐 갱신
            }

            // 큐에 남아있는 세포 수 = 살아있는 줄기세포(비활성 + 활성)
            System.out.println("#" + t + " " + q.size());
        }
    }
}