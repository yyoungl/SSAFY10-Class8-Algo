package algo_study;

import java.util.Arrays;
import java.util.Scanner;

public class yyoungl_20230802_B_12865 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] weight = new int[N + 1];
		int[] value = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			// 물건의 무게, 물건의 가치
			weight[i] = sc.nextInt();
			value[i] = sc.nextInt();
		}

		int dp[][] = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - weight[i] >= 0) {
					// 무게 j에서... i번째 무게 가방을 조합해서 넣을 수 있는가
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + value[i]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
