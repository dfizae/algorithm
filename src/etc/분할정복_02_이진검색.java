package etc;

public class 분할정복_02_이진검색 {
	
	static int[] arr;
	
	
	
	public static void main(String[] args) {
		

	}
	
	public static int binarySearch(int key){
		//범위를 컨트롤하는 변수 2개
		int L = 0;
		int R = arr.length-1; // 마지막 인덱스
		
		// 교차가 되면 멈춘다.
		while(L <= R) {
			int mid = (L+R)/2; //소수점은 버려진다.
			if(arr[mid] == key) return mid;
			else if(arr[mid] > key) // 왼쪽구간으로 축소
				R = mid+1;
			else
				L = mid + 1;
		}
		
		return -1;
	}
	
	public static int binarySearch2(int left, int right, int key) {
		// 종료
		if(left > right) {
			return -1;
		}
		// 재귀
		int mid = (left + right) / 2; //내림처리
		//1. 같을 때
		if(arr[mid] == key) {
			return mid;
		} else if(arr[mid] > key) {
			return binarySearch2(left, mid-1, key);
		} else {
			return binarySearch2(mid+1, right, key);
		}
	}
	
}
