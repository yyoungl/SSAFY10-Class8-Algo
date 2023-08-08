import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class damongsanga_20230808_B_1003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			// 0이 호출된 횟수와 1이 호출된 횟수를 저장하는 dp를 만든다
			int[][] dp = new int[N + 1][2];
			
			for (int j = 0; j < N + 1; j++) {
				// 0이면 0만 1번 호출
				if (j == 0) {
					dp[j][0] = 1;
				
				// 1이면 1만 1번 호출
				} else if (j == 1) {
					dp[j][1] = 1;
				
				// 2 이상이면 1 작은 dp값과 2 작은 dp 값을 더한다 (피보나치 구현)
				} else {
					dp[j][0] = dp[j - 1][0] + dp[j-2][0];
					dp[j][1] = dp[j - 1][1] + dp[j-2][1];
				}
			}
			System.out.println(dp[N][0] + " " + dp[N][1]);

		}

	}
}
