package _Baekjune;

// 스티커
import java.util.Arrays;
import java.util.Scanner;

public class _9465 {

	static int n;
	// 스티커의 값을 저장하기 위한 배열.
	static int[][] arr;
	// 특정위치까지 누적 값의 최대를 저장하기 위한 배열
	static int[][] check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// 테스트케이스 개수 만큼 값을 저장할 배열 선언
		int[] as = new int[T];
		for (int tc = 0; tc < T; tc++) {
			n = sc.nextInt();
			arr = new int[2][n];

			// arr배열에 스티커의 값 입력.
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			if (n == 1) {
				// n==1 일때는 스티커가 두개 뿐이므로 직접 비교
				as[tc] = Math.max(arr[0][0], arr[1][0]);
			} else {
				// 1열, 2열의 값은 직접 계산해서 넣어줌.
				check = new int[2][n];
				check[0][0] = arr[0][0];
				check[1][0] = arr[1][0];
				check[1][1] = arr[1][1] + arr[0][0];
				check[0][1] = arr[0][1] + arr[1][0];
				//check[0][i]와 check[1][i]로 바로 올 수 있는 지점은 각각 두 군데이므로
				//비교 후 큰 값을 선정하여 그 지점의 arr값을 더해줌.
				//이렇게 하는 이유는 불필요한 탐색의 수를 줄여 시간적으로 이득을 얻기 위함.
				for (int i = 2; i < n; i++) {
					check[0][i] = Math.max(check[1][i - 1], check[1][i - 2]) + arr[0][i];
					check[1][i] = Math.max(check[0][i - 1], check[0][i - 2]) + arr[1][i];
				}
				//가장 끝의 두 값을 비교하여 큰 값을 저장
				as[tc] = Math.max(check[0][n - 1], check[1][n - 1]);
			}
		}
		for (int x : as) {
			System.out.println(x);
		}
	}
}
