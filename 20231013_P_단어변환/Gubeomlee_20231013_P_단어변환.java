package Solution;

public class Gubeomlee_20231013_P_단어변환 {
	public boolean isValid(String str1, String str2) {
		int cnt = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				cnt++;
			}
		}

		if (cnt == 1) {
			return true;
		}
		return false;
	}

	public void dfs(String[] words, boolean[] check, String cur, String target, int cnt, int[] result) {
		if (cur.equals(target)) {
			result[0] = Math.min(result[0], cnt);
		} else {
			for (int i = 0; i < words.length; i++) {
				if (isValid(cur, words[i]) && !check[i]) {
					check[i] = true;
					dfs(words, check, words[i], target, cnt + 1, result);
					check[i] = false;
				}
			}
		}
	}

	public int getCnt(String begin, String target, String[] words) {
		int[] result = { Integer.MAX_VALUE };
		dfs(words, new boolean[words.length], begin, target, 0, result);

		if (result[0] == Integer.MAX_VALUE) {
			return 0;
		}
		return result[0];
	}

	public int solution(String begin, String target, String[] words) {
		int answer = getCnt(begin, target, words);
		return answer;
	}
}