import java.util.Scanner;

public class yyoungl_20230816_B_9465 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] stickers = new int[2][N + 1];
			// 스티커 초기화
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j < N + 1; j++) {
					stickers[i][j] = sc.nextInt();
				}
			}

			// dp 배열 초기화
			int[][] dp = new int[2][N + 1];
			// 앞 경우 살펴봐야 하기 때문에 N+1 로 하고 index 0인 열은 0으로 가져간다 ..
			dp[0][1] = stickers[0][1];
			dp[1][1] = stickers[1][1];

			for (int j = 2; j < N + 1; j++) {
				dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + stickers[0][j];
				dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + stickers[1][j];

			}
			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}

	}
}
