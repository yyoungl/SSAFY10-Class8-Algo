package Algorithm.algorithm.baekjoon;

import java.util.Scanner;

public class Jwon_20230824_B_2839_설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[5001];
		// 3과 5는 1임이 자명하다.
		arr[3] = 1;
		arr[5] = 1;

		for (int i = 0; i <= n; i++) {
			// 만약 현재 설탕의 무게가 어떠한 방법으로 3과 5를 이용하여 최소 배달이 가능하다면,
			// 설탕 봉지의 갯수는 0이 아닐 것이다.
			if (arr[i] != 0) {
				// 그렇다면 현재보다 3 많은 것과 5 많은 것도 최소 배달이 가능한 것이기 때문에 
				// i + 3과  i + 5인 곳에 +1 혹은 (그때의 최소배달 설탕이 0이 아니라면) 최솟값을 구해준다.
				if (i + 3 < 5001) {
					if (arr[i + 3] != 0) {
						arr[i + 3] = Math.min(arr[i + 3], arr[i] + 1);
					} else {
						arr[i + 3] = arr[i] + 1;
					}
				}
				if (i + 5 < 5001) {
					if (arr[i + 5] != 0) {
						arr[i + 5] = Math.min(arr[i + 5], arr[i] + 1);
					} else {
						arr[i + 5] = arr[i] + 1;
					}
				}
			}
		}
		
		// 만약 0이라면 어떠한 방법으로 3과 5를 이용하여 최소 배달이 불가한 것이므로,
		// -1을 출력한다.
		if (arr[n] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(arr[n]);
		}
	}
}
