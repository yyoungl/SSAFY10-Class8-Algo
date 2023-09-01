package Algorithm.algorithm.programmers;

import java.util.HashSet;
import java.util.Set;

public class Jwon_20230906_P_후보키_onlyBitMask {
	static int col;
	static int row;
	// 가능한 후보키들의 집합
	// 비트마스크를 이용하여 저장한다.
	static Set<Integer> candidatekeys = new HashSet<>();

	public int solution(String[][] relation) {
		row = relation.length;
		col = relation[0].length;

		// 컬럼이 하나일 때 후보키부터 여러개 후보키가 있을 수 있기 때문에
		// 컬럼의 갯수를 1 ~ col까지 확인한다.
		for (int i = 1; i <= col; i++) {
			combination(i, relation);
		}
		// 후보키의 갯수를 뽑는다.
		return candidatekeys.size();
	}

	private static void combination(int num, String[][] relation) {

		// 조합 만들기.
		for (int bit = 0; bit < 1 << col; bit++) {
			// 갯수가 num개인 조합 완성.
			if (Integer.bitCount(bit) == num) {
				// 후보키 중 새로 만들어진 집합에 부분집합이 있는 지 확인.
				// 최소성을 만족시키기 위해서 확인하는 작업이다.
				boolean tf = true;
				for (int temp : candidatekeys) {
					if ((bit & temp) == temp) {
						tf = false;
						break;
					}
				}
				// 최소성을 만족한다면!
				if(tf) {
					// 유일성을 만족시키기 위해서 확인하는 작업이다.
					// 컬럼이 여러개여도 유일한 지 확인하기 위해서 각 컬럼의 문자열들을
					// 하나로 합쳐서 유일성을 확인하였다.
					// 그렇게 해서 모여진 튜플의 갯수가 row와 같다면 유일성이 확인된다.
					HashSet<String> set = new HashSet<>();
					for (int i = 0; i < row; i++) {
						StringBuilder sb = new StringBuilder();
						for (int j = 0; j < col; j++) {
							if ((bit & 1 << j) > 0) {
								sb.append(relation[i][j]);
							}
						}
						set.add(sb.toString());
					}
					if (set.size() == row) {
						candidatekeys.add(bit);
					}
				}
			}
		}
		return;
	}
}
