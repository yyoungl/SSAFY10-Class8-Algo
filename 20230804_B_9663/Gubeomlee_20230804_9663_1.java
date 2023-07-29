package Solution;

import java.util.Scanner;

public class Gubeomlee_20230804_9663_1 {
	public static boolean isSafe(int[] arr, int row, int col) {
		for (int i = 0; i < row; i++) {
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
					backtrack(arr, len, row + 1, result);
					arr[row] = 0;
				}
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int[] arr = new int[len];

		int[] result = { 0 };
		backtrack(arr, len, 0, result);

		System.out.println(result[0]);
		sc.close();
	}
}