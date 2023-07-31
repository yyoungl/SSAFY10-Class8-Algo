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
		for(int i = 0; i < n ; i++) {
			boolean tf = true;
			for(int j = 0 ; j < x ; j++) {
				if(i == arr[j]) {
					tf = false;
					break;
				}
				if(Math.abs(i - arr[j])  == Math.abs(x - j)) {
					tf = false;
					break;
				}
			}
			if(tf) {
				arr[x] = i;
				find(x+1,arr);
			}
		}
		
	}
}
