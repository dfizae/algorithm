package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1260_bfsdfs {

    static StringTokenizer st;
    static int N, M, V;
    static List<Integer>[] adj;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder(); // 빠른 출력을 위한 StringBuilder

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[N + 1];
        
        // 수정 1: 배열의 각 인덱스(1~N)에 ArrayList를 올바르게 할당합니다.
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            // 양방향 그래프 설정
            adj[start].add(end);
            adj[end].add(start);
        }
        
        // 수정 2: 정점 번호가 작은 것부터 방문하기 위해 인접 리스트를 오름차순 정렬합니다.
        for(int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }
        
        // DFS 실행
        visit = new boolean[N + 1];
        Dfs(V);
        sb.append("\n"); // DFS와 BFS 결과 사이의 줄바꿈
        
        // BFS 실행
        visit = new boolean[N + 1]; // BFS를 위해 방문 배열을 다시 초기화합니다.
        Bfs(V);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
    
    public static void Dfs(int start) {
        visit[start] = true;
        sb.append(start).append(" "); // 방문한 노드 기록
        
        // 수정 3: 향상된 for문을 사용하여 인덱스 에러를 방지합니다.
        for(int currNum : adj[start]) {
            // 수정 4: 현재 노드(start)가 아닌, 다음 방문할 노드(currNum)의 방문 여부를 확인합니다.
            if(!visit[currNum]) {
                Dfs(currNum);
            }
        }
    }

    // 수정 5: Queue를 사용하여 BFS를 구현합니다.
    public static void Bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true; // 큐에 넣을 때 방문 처리를 합니다.
        
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            sb.append(curr).append(" "); // 방문한 노드 기록
            
            for(int nextNum : adj[curr]) {
                if(!visit[nextNum]) {
                    queue.add(nextNum);
                    visit[nextNum] = true;
                }
            }
        }
    }
}