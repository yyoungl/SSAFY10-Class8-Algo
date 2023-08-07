package Solution;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Gubeomlee_20230803_B_12865 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int weight = sc.nextInt();
		sc.nextLine();
		int[][] matrix = new int[num][2];
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		// dp[i]는 중량이 i일 때 최대 가치
		int[] dp = new int[weight + 1];

		for (int i = 0; i < num; i++) {
			int w = matrix[i][0];
			int v = matrix[i][1];
			for (int j = weight; j > w - 1; j--) {
				// 중량 j에서 현재물건을 추가할지 말지 결정하며 최대 가치를 갱신한다.
				dp[j] = Math.max(dp[j], dp[j - w] + v);
			}
		}
		System.out.println(dp[weight]);
		sc.close();
	}
}
