package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2 {
	
	static int[] numbers = new int[1000000];
	static int left, right;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<numbers.length; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = numbers.length-1;
		quickSort(left, right);
		System.out.println(numbers[500000]);
	}
	
	static void quickSort(int left, int right) {
		if(left >= right) return;
		
		int pivot = numbers[(left + right) / 2];
		int i = left;
		int j = right;
		while(i <= j) {
			while(numbers[i] < pivot) i++;
			while(numbers[j] > pivot) j--;
			
			if(i <= j) {
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
				i++;
				j--;
			}
		}
		quickSort(left, j);
		quickSort(i, right);
	}
}
