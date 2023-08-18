package Solution;

import java.util.Arrays;
import java.util.Scanner;

public class Gubeomlee_20230816_B_9465_2 {
	// 최대 점수 계산하는 메서드
	public static int dpFunc(int len, int idx, int[][] matrix) {
		if (len == 0) { // 배열의 길이가 0인 경우
			return 0;
		} else if (len == 1) { // 배열의 길이가 1인 경우
			return matrix[idx][0];
		} else {
			int[] dp = new int[len];
			dp[0] = matrix[idx][0];
			dp[1] = dp[0] + matrix[idx ^ 1][1];
			for (int i = 2; i < len; i++) {
				if (dp[i - 1] + matrix[idx][i] == dp[i - 2] + matrix[idx ^ 1][i]) {
					// 갱신 값이 같은 경우 재귀를 돈다.
					dp[i] = dp[i - 1] + matrix[idx][i];
					// 남은 행렬 복사
					int[][] temp = new int[2][len - i];
					temp[0] = Arrays.copyOfRange(matrix[0], i + 1, len);
					temp[1] = Arrays.copyOfRange(matrix[1], i + 1, len);
					return dp[i] + Math.max(dpFunc(len - i - 1, 0, temp), dpFunc(len - i - 1, 1, temp));
				} else if (dp[i - 1] + matrix[idx][i] < dp[i - 2] + matrix[idx ^ 1][i]) {
					dp[i] = dp[i - 2] + matrix[idx ^ 1][i];
				} else {
					dp[i] = dp[i - 1] + matrix[idx][i];
					idx ^= 1;
				}
			}
			return dp[len - 1];
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int len = sc.nextInt();
			int[][] matrix = new int[2][len];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < len; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			int result1 = dpFunc(len, 0, matrix); // 첫번째 행에서 시작하는 경우
			int result2 = dpFunc(len, 1, matrix); // 두번째 행에서 시작하는 경우
			System.out.println(Math.max(result1, result2));
		}
		sc.close();
	}
}