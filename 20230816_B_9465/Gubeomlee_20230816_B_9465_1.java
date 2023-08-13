package Solution;

import java.io.IOException;
import java.util.Scanner;

public class Gubeomlee_20230816_B_9465_1 {
	public static int dpFunc(int len, int[][] matrix) {
		int[][] dp = new int[2][len + 1];
		dp[0][1] = matrix[0][1];
		dp[1][1] = matrix[1][1];
		// bottom-up 방식으로 최대 점수 계산
		for (int i = 2; i <= len; i++) {
			dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + matrix[0][i];
			dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + matrix[1][i];
		}
		return Math.max(dp[0][len], dp[1][len]);
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int len = sc.nextInt();
			int[][] matrix = new int[2][len + 1];
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= len; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			int result = dpFunc(len, matrix);
			System.out.println(result);
		}
		sc.close();
	}
}