package etc;

public class 분할정복_01_거듭제곱 {

	public static void main(String[] args) {
		

	}

	static int pow3(int C, int N) {
		if(N == 0) return 1;
		//N이 홀수인 경우
		if(N % 2 == 1)
			return pow3(C, (N-1)/2) * pow3(C, (N-1)/2) * C;
		// N이 짝수인 경우
		else
			return pow3(C, N / 2) * pow3(C, N / 2);
	}
	
	// 모든 요리를 이용하여 거듭제곱을 구해보자
	static int pow4(int C, int N) {
		if(N == 0) return 1;
		
		int tmp = pow4(C, N/2);
		if(N % 2 == 1)
			return tmp*tmp*C;
		return tmp*tmp;
	}
}
