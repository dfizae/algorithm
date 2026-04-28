package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 햄스 {
    static int N, X, M;
    static int maxSum;
    static int[] arr;
    static int[] bestAns;
    static ArrayList<Condition>[] checks;

    // 기록(조건)을 저장할 클래스
    static class Condition {
        int l, s;
        Condition(int l, int s) {
            this.l = l;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // r번 우리에서 끝나는 조건들을 담을 배열 리스트 초기화
            checks = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                checks[i] = new ArrayList<>();
            }

            // 조건 입력 받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                // 1-based index를 0-based index로 변환
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                checks[r].add(new Condition(l, s));
            }

            // 변수 초기화
            maxSum = -1;
            arr = new int[N];
            bestAns = new int[N];

            // 0번 인덱스, 합계 0부터 탐색 시작
            dfs(0, 0);

            // 결과 저장
            sb.append("#").append(tc).append(" ");
            if (maxSum == -1) {
                sb.append("-1\n");
            } else {
                for (int i = 0; i < N; i++) {
                    sb.append(bestAns[i]).append(i == N - 1 ? "" : " ");
                }
                sb.append("\n");
            }
        }
        
        // 전체 결과 출력
        System.out.print(sb.toString());
    }

    static void dfs(int cageIdx, int currentSum) {
        // 이전 단계(cageIdx - 1)에서 끝나는 기록들이 모두 일치하는지 확인 (가지치기)
        if (cageIdx > 0) {
            for (Condition c : checks[cageIdx - 1]) {
                int sum = 0;
                for (int i = c.l; i < cageIdx; i++) {
                    sum += arr[i];
                }
                // 기록된 햄스터 수와 다르면 더 이상 탐색하지 않고 종료
                if (sum != c.s) return;
            }
        }

        // 모든 우리에 배치가 완료되었을 때
        if (cageIdx == N) {
            // 총합이 기존 최대치보다 "클 때만" 갱신 (사전순 유지)
            if (currentSum > maxSum) {
                maxSum = currentSum;
                System.arraycopy(arr, 0, bestAns, 0, N); // 배열 깊은 복사
            }
            return;
        }

        // 0마리부터 X마리까지 오름차순으로 배치 시도
        for (int v = 0; v <= X; v++) {
            arr[cageIdx] = v;
            dfs(cageIdx + 1, currentSum + v);
        }
    }
}