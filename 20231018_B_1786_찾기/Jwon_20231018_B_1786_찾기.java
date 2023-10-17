package Algorithm.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jwon_20231018_B_1786_찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// t문자에서 p의 갯수와 위치를 구하는 문제.
		String t = br.readLine();
		String p = br.readLine();

		// KMP를 위한 실패 값들 저장.
		// KMP에서의 저장값들은 어떠한 수 다음에 값을 넣는다.
		// 어느 부분에서 실패하였는 지를 알고 있다면 다음에 들어올 값들을 확인해볼 때 같을 수도 있다.
		// 뭐라카노;; 암튼 그럼
		int[] preFix = new int[p.length()];

		// j : 접두사가 접미사의 일치가 어디에서 실패하였는 지 보여주는 값이다.
		// j는 인덱스이고 접미사는 접두사가 0부터 j-1의 인덱스인 문자열과 일치하는 것이다.
		// 따라서 0 ~ j-1 까지의 문자열은 일치 j번째 문자는 일치하지 않으므로 불일치 하다고 볼 수 있다.
		// j가 인덱스임을 감안하면 0 ~ j-1까지의 문자열이 일치하기 때문에
		// 현재 완성된 문자열 (0 ~ i까지로 만들어진 문자열)의 접미사와 접두사가 같은 문자열의 길이는 곧 j이다.
		// 즉, j의 값이 곧 현재 위치의 접미사와 접두사가 일치하는 길이의 값이라고 생각하자.
		// 이는 나중에 t에서 p의 갯수를 구할 때 이용된다.
		int j = 0;
		for (int i = 1; i < p.length(); i++) {
			// 만약 실패한다면 0이되거나 혹은 성공할 때까지 j를 찾는다.
			// 현재 j는 실패한 곳을 보여주는 곳으로 만약 같다면 j-1까지 갖고 j까지 같아짐을 알 수 있다.
			// 만약 j가 지금도 실패한 곳이라면 j-1일 때 실패한 곳을 찾아가서 성공할 때까지 찾아준다.
			while (j > 0 && p.charAt(j) != p.charAt(i)) {
				j = preFix[j - 1];
			}
			// 만약 같다면 j에 +1을 해줘서 실패한 곳을 찾아준다.
			if (p.charAt(j) == p.charAt(i)) {
				j++;
			}
			// 실패한 곳 저장.
			preFix[i] = j;
		}
		int count = 0;
		j = 0;
		StringBuilder sb = new StringBuilder();
		// t에서 p를 찾는 것도 똑같다.
		// t의 i에서 p의 j-1까지 똑같다면 j를 확인해주고 실패한다면 다시 찾아준다.
		for (int i = 0; i < t.length(); i++) {
			while (j > 0 && p.charAt(j) != t.charAt(i)) {
				j = preFix[j - 1];
			}
			if (p.charAt(j) == t.charAt(i)) {
				j++;
			}
			if (j == p.length()) {
				j = preFix[p.length() - 1];
				count++;
				// i는 인덱스를 고려하여 몇 번째 위치는 곧 + 1 해줘야 한다.
				// len인 길이의 첫번째 위치를 구하기 위해선 len + 1 - len이므로
				// 첫 번째 위치를 구하기 위한 + 1
				// p의 길이를 빼서 첫 번째 위치를 구한다.
				sb.append(i + 1 + 1 - p.length()).append(" ");
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}
}
