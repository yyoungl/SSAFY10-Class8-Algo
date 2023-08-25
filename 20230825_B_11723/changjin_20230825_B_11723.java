package Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class С§Че_11723_2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int num = 0;
		int M = Integer.parseInt(st.nextToken());
		String str = "";
		String N = "";
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			if (st.hasMoreTokens())
				N = st.nextToken();
			if (str.equals("add")) {
				num = num | (1 << Integer.parseInt(N));
			} else if (str.equals("remove")) {
				num = num & ~(1 << Integer.parseInt(N));
			} else if (str.equals("toggle")) {
				num = num ^ (1 << Integer.parseInt(N));
			} else if (str.equals("empty")) {
				num = 0;
			} else if (str.equals("all")) {
				num = (1 << 21) - 1;
			} else {
				if ((num & (1 << Integer.parseInt(N))) != 0)
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
			}

		}
		System.out.println(sb);

	}
}
