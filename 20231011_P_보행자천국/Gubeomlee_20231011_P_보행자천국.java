package Solution;

public class Gubeomlee_20231011_P_보행자천국 {
	int MOD = 20170805;

	public int getCnt(int m, int n, int[][] map) {
		int[][][] dp = new int[m][n][2];
		dp[0][0][0] = 1;
		dp[0][0][1] = 1;

		for (int i = 1; i < m; i++) {
			if (map[i][0] == 0) {
				dp[i][0][0] = 1;
				dp[i][0][1] = 1;
			} else if (map[i][0] == 1) {
				break;
			} else {
				dp[i][0][0] = 1;
			}
		}

		for (int i = 0; i < n; i++) {
			if (map[0][i] == 0) {
				dp[0][i][0] = 1;
				dp[0][i][1] = 1;
			} else if (map[0][i] == 1) {
				break;
			} else {
				dp[0][i][1] = 1;
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (map[i][j] == 0) {
					int temp = (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
					dp[i][j][0] = temp;
					dp[i][j][1] = temp;
				} else if (map[i][j] == 2) {
					dp[i][j][0] = dp[i - 1][j][0] % MOD;
					dp[i][j][1] = dp[i][j - 1][1] % MOD;
				}
			}
		}

		return dp[m - 1][n - 1][0] % MOD;
	}

	public int solution(int m, int n, int[][] cityMap) {
		int answer = getCnt(m, n, cityMap);
		return answer;
	}
}