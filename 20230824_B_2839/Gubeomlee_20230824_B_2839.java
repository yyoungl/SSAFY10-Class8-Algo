package Solution;

import java.util.Scanner;

public class Gubeomlee_20230824_B_2839 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();

		int kilo3 = 0;
		// 무게가 5의 배수가 될때까지 3킬로그램 봉지를 이용해서 무게를 줄인다.
		while (weight % 5 != 0) {
			if (weight < 5 && weight != 3) {
				System.out.println(-1); // 3킬로그램, 5킬로그램 봉지로 정확히 나눌 수 없는 경우 -1을 출력한다.
				return;
			}
			weight -= 3; // weight이 0이되는 경우 반복문이 종료되어야 하기때문에 조건문 이후에 무게를 줄인다.
			kilo3++;
		}

		System.out.println(kilo3 + weight / 5);
		sc.close();
	}
}
