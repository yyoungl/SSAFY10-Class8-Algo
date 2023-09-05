package algo_study;

import java.util.ArrayList;
import java.util.HashSet;

public class PCandidateKey {
	
	static String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
			{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
			{ "600", "apeach", "music", "2" } };
	static int R;
	static int C;
	
	// 결과 조합을 담을 integer hashset
	static HashSet<Integer> result = new HashSet<>();

	public static int solution(String[][] relation) {
		int answer = 0;
		R = relation.length;
		C = relation[0].length;
		combination(C);
		answer = result.size();
		return answer;
	}
	
	// 여기서 조합을 구하면서 유일성 체크 이후 최소성 체크
	// 혹시 [1], [1, 2]를 체크하고 [1, 2]가 조건을 만족한다면 [1, 2, 3]은 체크하지 않는 식으로 코드를 짤 수는 없을까요? 어쨌든 구려질까요?
	static void combination(int C) {

		for (int i = 0; i < (1 << C); i++) {
			// 깔끔하게 배열로 풀고 싶은 나의 꿈... 어떻게 하는지 아는 분?
			ArrayList<Integer> comb = new ArrayList<>();
			for (int j = 0; j < C; j++) {
				if ((i & (1 << j)) > 0) {
					comb.add(j);
				}
			}

			HashSet<String> chk = new HashSet<String>();

			// 유일성 검사
			// 인덱스 조합으로 만든 개인정보의 조합으로 string을 만들고 set에 담는다.
			boolean isOkay = true;
			for (int d = 0; d < R; d++) {
				// 답이 넘 크게 나와서 확인해보니 여기서 str 초기화를 안 하고 윗줄에서 해서 하염없이 길어지는 스트링을 만듦;;
				String str = "";
				for (int com : comb) {
					str += relation[d][com];
				}
				
				if (chk.contains(str)) {
					isOkay = false;
				} else {
					chk.add(str);
				}
			}

			if (!isOkay)
				continue;

			boolean isMinimum = true;
			// 최소성 검사
			for (int re : result) {
				// 비트연산자로 해당 조합을 사용했는지 안 했는지 체크합니다.
				// 이때 result에 담긴 값이랑 같은지 확인해야 중복 조합이 있는지 없는지 알 수 있음
				if ((re & i) == re) {
					isMinimum = false;
				}
			}
			if (isMinimum)
				result.add(i);

		}

	}

	public static void main(String[] args) {
		solution(relation);
	}

}
