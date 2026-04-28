package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1231_중위순회 {
	
	static String[] nodeVal;
	static int[] leftChild, rightChild;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			nodeVal = new String[N+1];
			leftChild = new int[N+1];
			rightChild = new int[N+1];
			
			
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				nodeVal[idx] = st.nextToken();
				
				if(st.hasMoreTokens()) {
					leftChild[idx] = Integer.parseInt(st.nextToken());
				}
				
				if(st.hasMoreTokens()) {
					rightChild[idx] = Integer.parseInt(st.nextToken());
				}
				
			}
			sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			inOrder(1);
			sb.append("\n");
			System.out.println(sb.toString());
		}
	
	}
	
	static void inOrder(int node) {
		if(node == 0) return;
		sb.append(leftChild[node]);
		sb.append(nodeVal[node]);
		sb.append(rightChild[node]);
	}
}
