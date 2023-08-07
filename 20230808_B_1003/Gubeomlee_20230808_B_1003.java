package algo;

import java.util.Scanner;

public class Gubeomlee_20230808_B_1003 {
	public static int[] getFibonacci(int[] arr, int num) {
		arr[0] = 0;
		arr[1] = 1;
		if (num > 1) {
			for (int i = 2; i < num + 1; i++) {
				arr[i] = arr[i - 1] + arr[i - 2];
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] fibonacci = new int[41];
		getFibonacci(fibonacci, 40);
		for (int t = 0; t < T; t++) {
			int num = sc.nextInt();
			if (num == 0) {
				System.out.printf("%d %d\n", 1, 0);
			} else {
				// 0이 나온 횟수는 하나 아래 피보나치 수열 값이다.
				// 1이 나온 횟수는 현재 피보나치 수열 값이다.
				System.out.printf("%d %d\n", fibonacci[num - 1], fibonacci[num]);
			}
		}
		sc.close();
	}
}