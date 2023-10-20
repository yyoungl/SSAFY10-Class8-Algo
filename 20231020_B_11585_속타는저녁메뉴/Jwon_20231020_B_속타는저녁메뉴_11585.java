package Algorithm.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jwon_20231020_B_속타는저녁메뉴_11585 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			sb.append(st.nextToken());
		}
		String want = sb.toString();
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			sb.append(st.nextToken());
		}
		sb.append(sb.toString());
		String board = sb.toString();
		int[] preFix = new int[n];
		int j = 0;
		for (int i = 1; i < n; i++) {
			while (j > 0 && want.charAt(j) != want.charAt(i)) {
				j = preFix[j - 1];
			}
			if (want.charAt(j) == want.charAt(i)) {
				j++;
			}
			preFix[i] = j;
		}
		int count = 0;
		j = 0;
		for (int i = 1; i < board.length(); i++) {
			while (j > 0 && want.charAt(j) != board.charAt(i)) {
				j = preFix[j - 1];
			}
			if (want.charAt(j) == board.charAt(i)) {
				j++;
			}
			if (j == n) {
				j = preFix[want.length() - 1];
				count++;
			}
		}
		
		int a = count;
		int b = n;
		int max = 1;
		for (int i = 1; i <= n; i++) {
			if (a % i == 0 && b % i == 0) {
				max = i;
			}
		}
		a /= max;
		b /= max;
		sb = new StringBuilder();
		sb.append(a).append("/").append(b);
		System.out.println(sb.toString());

	}
}
