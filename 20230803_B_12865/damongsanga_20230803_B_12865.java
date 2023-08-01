import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class damongsanga_20230803_B_12865 {

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] arr = new int[N+1][2];
            // dp 2차원 구성
            int[][] dp = new int[N+1][K+1];

            // 무게, 가치 순으로 무게 입력
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

        /*
        물건을 새로 하나 씩 추가하면서 최대 값을 비교해본다면 전의 값을 활용할 수 있지 않을까? => dynamic programming
        그런데 이 경우 물건을 새로 추가하기 전 값을 활용하려면 새로 추가되는 무게 W보다 적을 때의 dp 값도 알고 있어야 한다.
        따라서 ( 물건 * 최대로 들 수 있는 무게 )를 각 축으로 하는 2차원 dp를 구성해야한다.
        시간 복잡도는 100 * 100_000 = 1000만으로 시간 내에 충분히 계산할 수 있다.
         */

            for (int i = 1; i <= N; i++) {
                int W = arr[i][0];
                int V = arr[i][1];
                for (int j = 1; j <= K; j++) {
                    // 들 수 있는 무게가 1 적었을 때와 물건의 종류가 1개 적었을 때 중 더 높은 가치의 경우를 dp에 기록한다.
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                    // 현재 dp에 기록된 가치와 새로 들어온 물건을 포함했을 때의 가치 중 더 높은 가치의 경우를 dp에 갱신한다.
                    if (j >= W){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-W] + V);
                    }

                }
            }

            System.out.println(dp[N][K]);

        }

}
