package Algorithm.algorithm.softeer;

import java.io.*;
import java.util.*;

public class Jwon_20231110_S_수퍼바이러스_LV3 {

	static long p;
	static final long DIV = 1000000007;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long k = Long.parseLong(st.nextToken());
		p = Long.parseLong(st.nextToken());
		long n = Long.parseLong(st.nextToken());
		n *= 10;
		long result = find(n);
		result = ((result % DIV) * (k % DIV)) % DIV;
		System.out.println(result);
	}

	// 분할정복으로 풀어준다.
	// 2n = n + n; or 2n + 1 = n + n + 1이라는 것을 이용한다.
	public static long find(long n) {
		if (n == 1) {
			return p;
		}

		long temp = find(n / 2);
		if (n % 2 == 1) {
			return (((temp * temp) % DIV) * p) % DIV;
		} else {
			return (temp * temp) % DIV;
		}
	}

}
