import java.util.*;

public class damongsanga_20230816_B_9465_1 {

        public static void main(String args[]) throws Exception {
            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for (int tc = 0; tc < T; tc++) {
                int N = sc.nextInt();
                int[][] arr = new int[2][N];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < N; j++) {
                        arr[i][j] = sc.nextInt();
                    }
                }
                int[][] dp = new int[3][N];
                dp[0][0] = arr[0][0]; // 위쪽 스티커를 고를 때
                dp[1][0] = arr[1][0]; // 아래쪽 스티커를 고를 때
                dp[2][0] = 0; // 안 고를 때

                // 가로로 이동하며 dp 갱신
                for (int i = 1; i < N; i++) {
                    dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]) + arr[0][i];
                    dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + arr[1][i];
                    dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]);
                }

                System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));

            }


    }

}

