package algorithm.baekjoon;

import java.util.Scanner;

public class Jwon_202380804_B_NQueen {
	
	static int count = 0;
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[] arr = new int[n];
		find(0,arr);
		System.out.println(count);
	}

	private static void find(int x, int[] arr) {
		if(x == n) {
			count++;
			return ;
		}
		// i는 y라고 생각을 하자.
		// 현재 x 좌표에서의 y를 0부터 끝까지 확인한다.
		for(int i = 0; i < n ; i++) {
			// tf는 현재 숫자가 현재 포지션에 적합한 지 확인용.
			boolean tf = true;
			for(int j = 0 ; j < x ; j++) {
				// 현재 x좌표까지의 퀸의 위치를 비교해 현재 좌표에 퀸을 놓을 수 있는 지 확인
				// x는 무조건 다르기 때문에 y가 같으면 false
				if(i == arr[j]) {
					tf = false;
					break;
				}
				// 기울기가 1이면 false
				if(Math.abs(i - arr[j])  == Math.abs(x - j)) {
					tf = false;
					break;
				}
			}
			// false라면 그 퀸을 포함한 다음으로 넘어가고 아니라면 다른 퀸으로 확인.
			if(tf) {
				arr[x] = i;
				find(x+1,arr);
			}
		}
		
	}
}
