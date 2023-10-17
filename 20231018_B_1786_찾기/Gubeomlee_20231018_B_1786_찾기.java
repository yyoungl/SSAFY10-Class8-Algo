
import java.util.Scanner;

public class Gubeomlee_20231018_B_1786_찾기 {
	public static void getLps(String p, int[] lps) {
		int idx = 0;
		for (int i = 1; i < p.length(); i++) {
			while (idx > 0 && p.charAt(i) != p.charAt(idx)) {
				idx = lps[idx - 1];
			}
			if (p.charAt(i) == p.charAt(idx)) {
				lps[i] = ++idx;
			}
		}
	}

	public static void search(String t, String p, int[] lps, int[] find, StringBuilder sb) {
		int cnt = 0;
		for (int i = 0; i < t.length(); i++) {
			while (cnt > 0 && t.charAt(i) != p.charAt(cnt)) {
				cnt = lps[cnt - 1];
			}

			if (t.charAt(i) == p.charAt(cnt)) {
				if (cnt == p.length() - 1) {
					find[0]++;
					sb.append(Integer.toString(i - cnt + 1) + " ");
					cnt = lps[cnt];
				} else {
					cnt++;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String t = sc.nextLine();
		String p = sc.nextLine();

		int[] lps = new int[p.length()];
		getLps(p, lps);

		int[] find = { 0 };
		StringBuilder sb = new StringBuilder();
		search(t, p, lps, find, sb);

		System.out.println(find[0]);
		if (find[0] != 0) {
			System.out.println(sb.toString());
		}

		sc.close();
	}
}
