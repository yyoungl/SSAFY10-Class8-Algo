import java.util.Arrays;
import java.util.Collections;

public class yyoungl_20230809_P_minerals {
	public static void main(String[] args) {
		// 처음엔 ArrayList로 size를 추출한 다음 0부터 모든 숫자를 탐색하지 않아도 될 수 있을 것 같았는데...
		// 뭔가 인덱스 이슈가 발생해서 Array로 풀었다.
		// 0과 1을 호출하는 횟수 또한 피보나치 수열의 원리와 같기 때문에 0을 호출하는 횟수를 보관하는 배열, 1을 호출하는 횟수를 보관하는 배열 두 개를 만들었다.

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		fibo0[0] = 1;
		fibo0[1] = 0;
		fibo1[0] = 0;
		fibo1[1] = 1;

		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
//			System.out.println(fibo1.size());
			for (int j=2; j<=num; j++) {
				fibo0[j] = fibo0[j-1] + fibo0[j-2];
				fibo1[j] = fibo1[j-1] + fibo1[j-2];
			}
			System.out.println(fibo0[num] + " " + fibo1[num]);
		}
	}

	static int[] fibo0 = new int[41];
	static int[] fibo1 = new int[41];
}


