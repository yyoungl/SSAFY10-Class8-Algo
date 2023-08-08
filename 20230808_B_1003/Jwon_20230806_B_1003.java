package backjoon;

import java.util.Scanner;

public class Jwon_20230806_B_1003 {
	
	static boolean[] chk;
	static int[][] arr;
	
	// 얼핏보면 간단한 문제 같지만 상당한 시간초과가 예상이 되는 문제였다.
	// 따라서 시간을 줄이는 것을 주로 생각하며 문제를 풀었다.
	// 자 드가자
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		for(int i = 0 ; i < test ; i++) {
			int n = sc.nextInt();
			// 현재의 수까지의 0과 1의 갯수를 arr에 저장해놓았는 지 확인하기 위한 chk배열
			chk = new boolean[n+1];
			// 현재의 수까지의 0과 1의 갯수를 저장하는 arr배열 
			// arr[n][0] : n까지 피보나치를 할 때 0의 갯수
			// arr[n][1] : n까지 피보나치를 할 때 1의 갯수
			arr = new int[n+1][2];
			int[][] result = fibonacci(n);
			System.out.println(result[n][0] +" " + result[n][1]);
		}
	}	
	
	
	// 최대한 문제에서 구현한 피보나치 함수를 이용할려고 노력하였다.
	static int[][] fibonacci(int n) {
		// n == 1일 때 
		if(n == 1) {
			arr[1][1] = 1;
			return arr;
		}
		// n == 0일 때
		else if(n == 0) {
			arr[0][0] = 1;
			return arr;
		}
		
		// 만약 지금 숫자까지 오는 데 필요한 0과 1의 수가 저장되어 있지 않다면
		// 저장해준다.
		if(!chk[n]) {
			// 저장해줄 것이기 떄문에 이제 이 수는 true
			chk[n] = true;
			// 값 저장해주기
			int[][] arr1 = fibonacci(n-1);
			int[][] arr2 = fibonacci(n-2);
			arr[n][0] = arr1[n-1][0] + arr[n-2][0];
			arr[n][1] = arr2[n-1][1] + arr[n-2][1];
		}
		// 저장되어 있는 배열을 리턴한다.
		return arr;
	}
}
