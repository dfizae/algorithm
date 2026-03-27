package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class swea1974_bfs_스도쿠_검증 {
	
	static StringTokenizer st;
	
	static int[][] sudoku;
	static boolean[] rowNum;
	static boolean[] colNum;
	static boolean[] squareNum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			sudoku = new int[9][9];

			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 1;
			
			
			for (int r = 0; r < 9; r++) {
				rowNum = new boolean[10];
				colNum = new boolean[10];
				for (int c = 0; c < 9; c++) {
					if(!rowNum[sudoku[r][c]]) {
						rowNum[sudoku[r][c]] = true;
					} else {
						answer = 0;
						break;
					}
					if(!colNum[sudoku[c][r]]) {
						colNum[sudoku[c][r]] = true;
					} else {
						answer = 0;
						break;
					}	
				}
				if(answer == 0) {
					break;
				}
			}
			
			for (int r = 0; r < 9; r+=3) {
				for (int c = 0; c < 9; c+=3) {
					squareNum = new boolean[10];
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(!squareNum[sudoku[r+i][c+j]]) {
								squareNum[sudoku[r+i][c+j]] = true;
							} else {
								answer = 0;
								break;
							}
						}
						if (answer == 0) {
							break;
						}
					}
					if (answer == 0) {
						break;
					}
				}
				if (answer == 0) {
					break;
				}
			}
		
			System.out.println("#" + t + " " + answer);
		}
	}
}
