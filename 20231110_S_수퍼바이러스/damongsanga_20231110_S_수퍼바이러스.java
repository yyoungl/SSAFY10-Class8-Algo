import java.io.*;
import java.util.*;

public class damongsanga_20231110_S_수퍼바이러스 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long K = Long.parseLong(st.nextToken());
		long P = Long.parseLong(st.nextToken());
		long N = Long.parseLong(st.nextToken());
		N *= 10;
		final long MOD = 1_000_000_007;
		System.out.println(recur(N, P, MOD) * K % MOD);
	}
	static long recur(long N, long P, long MOD) {
		if (N == 1) return P;
		
		long tmp = recur(N/2, P, MOD);
		return N%2==0? tmp * tmp % MOD : tmp * tmp % MOD * P % MOD;
		
	}
}