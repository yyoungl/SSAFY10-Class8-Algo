

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int min = 1;
		int answer = 0;
		int max = arr[n - 1] - arr[0];
		while (min <= max) {
			int mid = (min + max) / 2;
			int sum = 0;
			int cnt = 1;
			for (int i = 1; i < n; i++) {
				sum += arr[i] - arr[i - 1];
				if (sum < mid)
					continue;
				else {
					cnt++;
					sum = 0;
				}
			}
			if (cnt < m) {
				max = mid - 1;
			} else {
				answer = mid;
				min = mid + 1;
			}
		}
		System.out.println(answer);

	}
}
