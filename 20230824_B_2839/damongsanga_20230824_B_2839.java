import java.util.Scanner;

public class damongsanga_20230824_B_2839 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 987654321;
        }
        int answer = 0;
        dp[3] = 1;
        if (N >= 5) dp[5] = 1;
        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i-5], dp[i-3]) + 1;
        }
        answer = dp[N];
        System.out.println(answer >= 987654321 ? -1 : answer);


    }
}