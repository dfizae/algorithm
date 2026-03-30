package swea;

import java.util.Scanner;

public class swea1961_숫자배열회전 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스 수 입력
        int T = sc.nextInt(); 

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 배열의 크기 N
            int[][] arr = new int[N][N];

            // 1. 원본 2차원 배열 입력받기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // 2. 90도, 180도, 270도 회전 배열 생성
            // 만든 rotate 메서드를 연쇄적으로 호출하여 결과를 얻습니다.
            int[][] deg90 = rotate(arr);
            int[][] deg180 = rotate(deg90);
            int[][] deg270 = rotate(deg180);

            // 3. 결과 출력 형식에 맞게 출력
            System.out.println("#" + tc);
            for (int i = 0; i < N; i++) {
                // 90도 배열의 i번째 행 출력
                for (int j = 0; j < N; j++) {
                    System.out.print(deg90[i][j]);
                }
                System.out.print(" "); // 공백

                // 180도 배열의 i번째 행 출력
                for (int j = 0; j < N; j++) {
                    System.out.print(deg180[i][j]);
                }
                System.out.print(" "); // 공백

                // 270도 배열의 i번째 행 출력
                for (int j = 0; j < N; j++) {
                    System.out.print(deg270[i][j]);
                }
                System.out.println(); // 한 행의 출력이 끝났으므로 줄바꿈
            }
        }
        sc.close();
    }

    // 2차원 배열을 시계방향으로 90도 회전시키는 메서드
    public static int[][] rotate(int[][] original) {
        int N = original.length;
        int[][] rotated = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 90도 회전의 핵심 인덱스 공식
                rotated[i][j] = original[N - 1 - j][i];
            }
        }
        return rotated;
    }
}
