package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_14510 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] treeHeights = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int maxHeight = 0;
            
            for (int i = 0; i < N; i++) {
                treeHeights[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, treeHeights[i]);
            }

            int needOne = 0;
            int needTwo = 0;

            for (int i = 0; i < N; i++) {
                int diff = maxHeight - treeHeights[i];
                if(diff == 0) continue;
                needTwo += diff / 2; // 2��ŭ �ڶ� �� �ִ� Ƚ��
                needOne += diff % 2; // 1��ŭ �ڶ�� �ϴ� Ƚ��
            }

            // 2. ��¥�� 1�Ϻ��� �帣�� �ϸ鼭 Ž��
            int day = 0;
            while (needOne > 0 || needTwo > 0) {
                day++; 

                if (day % 2 == 1) { 
                	// Ȧ�� ��
                    if (needOne > 0) {
                        needOne--; 
                    } else if (needTwo > 1) {
                        needTwo--;
                        needOne++;
                    }
                } else { 
                    // ¦�� ��
                    if (needTwo > 0) {
                        needTwo--; 
                    }
                }
            }

            System.out.println("#" + t + " " + day);
        }
    }
}