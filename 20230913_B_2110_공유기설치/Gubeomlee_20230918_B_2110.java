import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gubeomlee_20230918_B_2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		// 시작 최솟값과 최댓값을 설정한다.
		int min = 1;
		int max = arr[n - 1] - arr[0];

		int result = 0;

		while (min <= max) {
			int mid = (min + max) / 2; // 2분탐색 중간값
			int sum = 0;
			int cnt = 1;

			for (int i = 0; i < n - 1; i++) {
				sum += arr[i + 1] - arr[i];
				if (sum >= mid) { // 중간값보다 짧은 구간의 수를 구한다.
					sum = 0;
					cnt++;
				}
			}

			if (cnt >= c) { // 구간의 수가 많을 때는 최솟값을 키우고
				result = mid; //
				min = mid + 1;
			} else { // 구간의 수가 작을 때는 최댓값을 줄인다.
				max = mid - 1;
			}
		}
		System.out.println(result);
		br.close();
	}
}
