public class yyoungl_20231018_B_1786 {
    static int answer1 = 0;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String parent = sc.nextLine();
		String pattern = sc.nextLine();
		sb = new StringBuilder();
		KMP(parent, pattern);
		System.out.println(answer1);
		System.out.println(sb.toString());
	}

	static void KMP(String parent, String pattern) {
		int[] table = makeTable(pattern);

		int n1 = parent.length();
		int n2 = pattern.length();

		int idx = 0; // 현재 대응되는 글자 수
		for (int i = 0; i < n1; i++) {
			while (idx > 0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			// 글자가 대응될 경우
			if (parent.charAt(i) == pattern.charAt(idx)) {
				if (idx == n2 - 1) {
					answer1++;
					sb.append((i-idx+1) + " ");
					// System.out.println(i - idx + 1);
					idx = table[idx];
				} else {
					idx += 1;
				}
			}
		}
	}

	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		int idx = 0;
		for (int i = 1; i < n; i++) {
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;
				table[i] = idx;
			}
		}
		return table;
	}
}
