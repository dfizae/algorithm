package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 간선 정보 저장을 위한 클래스
class Edge implements Comparable<Edge> {
    int to;      // 도착 노드
    int weight;  // 거리(가중치)

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    // 우선순위 큐에서 거리가 짧은 순서대로 나오게 정렬 기준 설정
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 여러 개의 테스트 케이스를 처리하기 위한 무한 루프
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            
            StringTokenizer st = new StringTokenizer(line);
            int m = Integer.parseInt(st.nextToken()); // 집의 수 (노드 수)
            int n = Integer.parseInt(st.nextToken()); // 길의 수 (간선 수)

            // 종료 조건
            if (m == 0 && n == 0) break;

            // 그래프 인접 리스트 초기화
            ArrayList<Edge>[] graph = new ArrayList[m];
            for (int i = 0; i < m; i++) {
                graph[i] = new ArrayList<>();
            }

            int totalCost = 0; // 모든 길의 불을 켰을 때의 총비용

            // 간선 정보 입력
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                // 양방향 그래프이므로 양쪽에 모두 추가
                graph[u].add(new Edge(v, w));
                graph[v].add(new Edge(u, w));
                
                totalCost += w; // 전체 비용 누적
            }

            // 프림 알고리즘 시작
            boolean[] visited = new boolean[m];
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            
            pq.offer(new Edge(0, 0));
            
            int minCost = 0; 
            int connectedCount = 0; // 연결된 집의 개수를 세어 탐색 조기 종료에 사용

            while (!pq.isEmpty()) {
                Edge current = pq.poll(); // 현재 큐에 있는 길 중 가장 짧은 길 뽑기

                if (visited[current.to]) continue;

                visited[current.to] = true;
                minCost += current.weight;
                connectedCount++;

                if (connectedCount == m) break;

                for (Edge next : graph[current.to]) {
                    if (!visited[next.to]) {
                        pq.offer(next);
                    }
                }
            }

            System.out.println(totalCost - minCost);
        }
    }
}