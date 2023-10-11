package Algorithm.algorithm.programmers;

public class Jwon_20231013_P_단어변환 {
	static boolean[] chk;
	static int answer ;

	static public int solution(String begin, String target, String[] words) {
		chk = new boolean[words.length];
		answer = 0;
		dfs(begin, target, 0, words);
		return answer;
	}

	private static void dfs(String begin, String target, int count, String[] words) {
		// 만약 begin이 target이 되면 끝!
		if (begin.equals(target)) {
			answer = count;
			return;
		}
		// dfs하면 되네요.
		for (int i = 0; i < words.length; i++) {
			if (!chk[i]) {
				// 단, 알파벳 하나만 변경해서 변경할 수 있는 친구인 지 확인해줘야하는 작업이 필요하다.
				if (canChange(words[i], begin)) {
					chk[i] = true;
					dfs(words[i], target, count + 1, words);
					chk[i] = false;
				}
			}
		}
	}

	private static boolean canChange(String word, String begin) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			// word와 begin에서 다른 알파벳이 단 하나만 있는 지 확인.
			if (word.charAt(i) != begin.charAt(i)) {
				count++;
			}
			if (count > 1) {
				return false;
			}
		}
		// 중복된 것은 없다고 되어있으므로 count == 0일 경우는 없다.
		return true;
	}

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(solution(begin, target, words));
		String begin1 = "hit";
		String target1 = "cog";
		String[] words1 = { "hot", "dot", "dog", "lot", "log" };
		System.out.println(solution(begin1, target1, words1));
	}
}
