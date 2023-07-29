package Solution;

import java.util.Scanner;

public class Gubeomlee_20230804_9663_2 {
	public static boolean isSafe(int[] arr, int row, int col) {
		for (int i = 0; i < row; i++) {
			// 같은 열 또는 대각선 상에 퀸이 위치한 경우
			if (arr[i] == col || Math.abs(i - row) == Math.abs(arr[i] - col)) {
				return false;
			}
		}
		return true;
	}

	public static void backtrack(int[] arr, int len, int row, int[] result) {
		if (len == row) {
			result[0]++;
		} else {
			for (int col = 0; col < len; col++) {
				if (isSafe(arr, row, col)) {
					arr[row] = col;
					backtrack(arr, len, row + 1, result); // 재귀를 호출하며 다음 행으로 넘어간다.
					arr[row] = 0;
				}
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		// 배열의 인덱스 값을 row로 하고, 값을 col로 한다.
		int[] arr = new int[len];

		int[] result = { 0 };
		backtrack(arr, len, 0, result);

		System.out.println(result[0]);
		sc.close();
	}
}