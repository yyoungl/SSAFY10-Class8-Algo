package Solution;

import java.util.Scanner;

public class Gubeomlee_20230804_9663 {
	// 해당 위치에 퀸을 배치했을 때 대각선 방향으로 다른 퀸이 있는지 확인하는 메서드
	public static boolean isSafe(int[][] matrix, int len, int row, int col) {
		// 같은 열에 있는지 확인
		for (int i = 0; i < row; i++) {
			if (matrix[i][col] == 1) {
				return false;
			}
		}
		// 왼쪽 위 대각선에 있는지 확인
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (matrix[i][j] == 1) {
				return false;
			}
		}
		// 오른쪽 위 대각선에 있는지 확인
		for (int i = row, j = col; i >= 0 && j < len; i--, j++) {
			if (matrix[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

	// 퀸을 배치하는 백트래킹 메소드
	public static void backtrack(int[][] matrix, int len, int row, int[] result) {
		if (len == row) {
			result[0] += 1;
		} else {
			for (int col = 0; col < len; col++) {
				if (isSafe(matrix, len, row, col)) { // 현재 위치에 퀸을 배치할 수 있는 경우
					matrix[row][col] = 1;
					backtrack(matrix, len, row + 1, result); // 재귀 호출 시 다음 행으로 넘어간다.
					matrix[row][col] = 0; // 백트레킹
				}
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int[][] matrix = new int[len][len];

		int[] result = { 0 };
		backtrack(matrix, len, 0, result);

		System.out.println(result[0]);
		sc.close();
	}
}