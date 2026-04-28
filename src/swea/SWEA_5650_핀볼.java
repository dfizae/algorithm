import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5650_핀볼 {

    static int N;
    static int[][] board;
    static int maxScore;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
   

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            maxScore = 0;
            

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 모든 빈칸에서 4가지 방향으로 핀볼 출발 시켜보기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) {
                        for (int dir = 0; dir < 4; dir++) {
                            maxScore = Math.max(maxScore, play(r, c, dir));
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + maxScore);
        }
    }
    
    static int play(int startR, int startC, int startDir) {
        int r = startR;
        int c = startC;
        int dir = startDir; // 시작 방
        int score = 0;

        while (true) {
            r += dr[dir];
            c += dc[dir];

            // 1. 벽에 부딪힌 경우
            if (r < 0 || r >= N || c < 0 || c >= N) {
                
                continue;
            }

            // 2. 블랙홀(-1)을 만나거나 시작점(startR, startC)으로 돌아온 경우
            if (board[r][c] == -1 || (r == startR && c == startC)) {
                return score; // 게임 종료, 점수 반환
            }

            // 3. 블록(1~5)을 만난 경우
            if (board[r][c] >= 1 && board[r][c] <= 5) {
            	
            }

            // 4. 웜홀(6~10)을 만난 경우
            else if (board[r][c] >= 6 && board[r][c] <= 10) {
                
            }
        }
    }
}