package greedy;

public class KMP {

	public static void main(String[] args) {
		

	}
		
	public static int[] getPi(String pt) {
		int[] pi = new int[pt.length()];
	
		int j = 0;
		for (int i = 1; i < pt.length(); i++) {
			while (j > 0 && pt.charAt(i) != pt.charAt(j)) {
				j = pi[j-1];
				
			}
			if(pt.charAt(i) == pt.charAt(j)) {
				pi[i] = ++j;
			}
			
		}
		
		return pi;
	}

	
	public static void KMP(String t, String p) {
		int[] pi = getPi(p);
		
		int j = 0; // 길이 저장 포인터
		for (int i = 0; i < t.length(); i++) {
			//1. 다를때
			while (j>0 && t.charAt(i) != p.charAt(j)) {
				j = pi[j - 1];
			}
			//2. 같을때
			if (t.charAt(i) == p.charAt(j)) {
				//2-1. j가 패턴의 마지막 인덱스라면... (찾았다!)
				if (j == p.length()-1) {
					//패턴찾음 그만하던지 더하던지...
					j = pi[j];
				} else {
					++j;
				}
			}
			
			
			
		}//본문순회 반복문
	}
}
