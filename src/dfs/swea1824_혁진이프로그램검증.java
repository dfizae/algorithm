package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 💡 힌트 1: Point 클래스에 '방향'과 '메모리' 상태 추가
class Point {
    int r, c, dir, mem;

    public Point(int r, int c, int dir, int mem) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.mem = mem;
    }
}

public class swea1824_혁진이프로그램검증 { // SWEA 제출용 기본 클래스명
    static int R, C;
    static char[][] lines;
    
    // 💡 힌트 2: 4차원 방문 배열 [행][열][방향][메모리]
    static boolean[][][][] visit; 

    // 우(0), 좌(1), 상(2), 하(3)
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            lines = new char[R][C];
            boolean hasAt = false;

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    lines[i][j] = line.charAt(j);
                    if (lines[i][j] == '@') {
                        hasAt = true; // 🌟 최적화: 맵에 '@'가 존재하는지 미리 체크
                    }
                }
            }

            // '@'가 아예 없으면 절대 프로그램이 종료될 수 없음
            if (!hasAt) {
                System.out.println("#" + t + " NO");
                continue;
            }

            visit = new boolean[R][C][4][16];
            
            if (bfs()) {
                System.out.println("#" + t + " YES");
            } else {
                System.out.println("#" + t + " NO");
            }
        }
    }

    static boolean bfs() {
        Queue<Point> q = new LinkedList<>();
        
        // 💡 힌트 3: 시작점 고정 -> (0, 0)에서 우측(0) 방향, 메모리 0으로 시작
        q.add(new Point(0, 0, 0, 0));
        visit[0][0][0][0] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            int r = curr.r;
            int c = curr.c;
            int dir = curr.dir;
            int mem = curr.mem;

            char cmd = lines[r][c];

            // 1. 종료 명령어 체크
            if (cmd == '@') {
                return true;
            }

            // 2. 명령어에 따른 메모리 값 업데이트
            int nextMem = mem;
            int nextDir = dir;

            if (cmd >= '0' && cmd <= '9') {
                nextMem = cmd - '0';
            } else if (cmd == '+') {
                nextMem = (mem == 15) ? 0 : mem + 1;
            } else if (cmd == '-') {
                nextMem = (mem == 0) ? 15 : mem - 1;
            }

            // 3. 방향 설정 및 다음 위치 큐에 삽입
            if (cmd == '?') {
                // '?' 인 경우 4가지 방향 모두 탐색
                for (int d = 0; d < 4; d++) {
                    // 💡 힌트 4: 격자를 벗어나면 반대편으로 넘어가도록 모듈러(%) 연산 활용
                    int nr = (r + dr[d] + R) % R; 
                    int nc = (c + dc[d] + C) % C;

                    if (!visit[nr][nc][d][nextMem]) {
                        visit[nr][nc][d][nextMem] = true;
                        q.add(new Point(nr, nc, d, nextMem));
                    }
                }
            } else {
                // 일반 방향 지시어 처리
                if (cmd == '<') nextDir = 1;
                else if (cmd == '>') nextDir = 0;
                else if (cmd == '^') nextDir = 2;
                else if (cmd == 'v') nextDir = 3;
                else if (cmd == '_') nextDir = (mem == 0) ? 0 : 1; // 0이면 우(0), 아니면 좌(1)
                else if (cmd == '|') nextDir = (mem == 0) ? 3 : 2; // 0이면 하(3), 아니면 상(2)

                int nr = (r + dr[nextDir] + R) % R;
                int nc = (c + dc[nextDir] + C) % C;

                if (!visit[nr][nc][nextDir][nextMem]) {
                    visit[nr][nc][nextDir][nextMem] = true;
                    q.add(new Point(nr, nc, nextDir, nextMem));
                }
            }
        }
        
        // 큐가 빌 때까지 '@'에 도달하지 못했다면 무한 루프이거나 도달 불가
        return false; 
    }
}