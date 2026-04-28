package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1232 {
	
	static String[] nodeVals;
	static int[] leftChild, rightChild;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			nodeVals = new String[N+1];
			leftChild = new int[N+1];
			rightChild = new int[N+1];
			
			for(int m = 0; m<N; m++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken());
				nodeVals[node] = st.nextToken(); // 부호 저장 
				if(st.hasMoreTokens()) {
					leftChild[node] = Integer.parseInt(st.nextToken());
					rightChild[node] = Integer.parseInt(st.nextToken());
				}
			}
			
			double result = calculator(1);
			System.out.println("#" + t + " " + (int)result);
		}
	
	}
		
	static double calculator(int node) {
		String val = nodeVals[node];
		if(val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) {
			double x = calculator(leftChild[node]);
			double y = calculator(rightChild[node]);
		
			switch(val) {
				case "+":
					return x + y;
				case "-":
					return x - y;
				case "*":
					return x * y;
				case "/":
					return x / y;
			}
		}
		
		return Double.parseDouble(val);
	}
}
