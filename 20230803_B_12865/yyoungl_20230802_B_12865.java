package algo_study;

import java.util.Arrays;
import java.util.Scanner;

public class yyoungl_20230802_B_12865 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		// 한 줄로 들어오는 input은 무조건 string으로 받아서 쪼갠 후 parseInt 해야 되는 줄 알았음...
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
		// 가방 탐색 -> 1번부터 N번까지: 왜냐면 가방 번호 1번~N번으로 생각하고, 무게를 1kg부터 Nkg까지 드는 경우를 탐색하고 싶음

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				
				if (j - weight[i] >= 0) {
					// 총 무게 j를 들 때, i번째 물건 이외의 물건을 담을 수 있는가? = 물건의 개수를 늘릴 수 있는가?
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + value[i]);
					// i번 물건이 나타나기 전 i-1 kg을 들었떤 최대 무게 (dp[i][j]) 와
					// (j kg - i번째 물건의 무게를 든 조합, 단 i번째 물건은 제외) 의 가치 + i번째 물건 가치
					// 를 비교해서 둘 중 큰 값을 넣는다. 들 수 있는 kg 안에서 가장 많은 가치를 구하는 문제.
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
