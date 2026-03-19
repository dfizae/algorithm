package dfs;

import java.util.Scanner;
import java.util.Stack;

public class 그래프탐색_DFS_01_그래프 {

	static int V, E; //정점의 수, 간선의 수
	static int[][] adjArr; //인접행렬
	static boolean[] visited; //방문처리
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//static 변수들을 초기화하는 과정은 tc for문안에서 초기화를 하자!
		V = sc.nextInt();
		E = sc.nextInt();
		
		adjArr = new int[V+1][V+1]; // 중요: 1번정점부터 시작하므로 한칸 더 크게 생성
		visited = new boolean[V+1];
		
		//간선입력
		for (int i = 0; i < E; i++) { // 한 쌍의 노드 입력
			int st = sc.nextInt(); // 시작 정점
			int ed = sc.nextInt(); // 도착 정점
			adjArr[st][ed] = 1;
			adjArr[ed][st] = 1; // 무향이니까 이것도 해줘야한다.
		}	
	}
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.println(v); // 추후에 어떠한 로직을 작성해야할 가능성 높음
		
		for (int i = 1; i <=V; i++) {
			//인접하니?이면서 방문하지 않았다면...
			if (adjArr[v][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
		
	}
	
	static void dfsStack(int start) {
		//정수 스택을 생성하였다. (정점만 넣을 거라...)
		Stack<Integer> stack = new Stack<>();
		
		stack.push(start); // 시작정점을 스택에 넣는다.
		
		//중복된 노드가 조금 나오는 것 같다. -> 이를 해결하는 방법이 있다!
		//내일이면 해결이되지만 오늘 알려주지는 않는 부분
		while(!stack.isEmpty()){
			//현재 정점의 번호를 꺼냈다!
			int curr = stack.pop();
			
			visited[curr] = true;
			System.out.println(curr);
		
			//나오는 순서를 재귀랑 일치하고 싶다?
			for (int i = V; i > 0; i++) {
				if (!visited[i] && adjArr[curr][i] == 1) {
					stack.push(i);
				}
			}
		}
	}
}
