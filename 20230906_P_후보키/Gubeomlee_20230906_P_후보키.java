package algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gubeomlee_20230906_P_후보키 {
	public static boolean isValid(String[][] relation, List<Integer> temp) {
		Set<String> set = new HashSet<>();
		for (int row = 0; row < relation.length; row++) {
			StringBuilder sb = new StringBuilder();
			for (int col : temp) {
				sb.append(relation[row][col]);
			}
			if (!set.add(sb.toString())) {
				return false;
			}
		}
		return true;
	}

	public static void backtrack(String[][] relation, int idx, List<Integer> temp, List<List<Integer>> collection) {
		for (int i = idx; i < relation[0].length; i++) {
			temp.add(i);
			if (isValid(relation, temp)) { // 후보키인 경우
				collection.add(new ArrayList<>(temp));
			} else { // 후보키가 아닌 경우
				backtrack(relation, i + 1, temp, collection);
			}
			temp.remove(temp.size() - 1);
		}
	}

	// 부분집합인지 확인하는 메서드
	public static boolean isSubSet(List<List<Integer>> collection, int i, int j) {
		if (i == j) {
			return false;
		}
		for (int num : collection.get(j)) {
			if (!collection.get(i).contains(num)) {
				return false;
			}
		}
		return true;
	}

	// 부분집합의 개수를 구하는 메서드
	public static int cntSubSet(List<List<Integer>> collection) {
		int cnt = 0;
		for (int i = 0; i < collection.size(); i++) {
			for (int j = 0; j < collection.size(); j++) {
				if (isSubSet(collection, i, j)) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}

	public static int solution(String[][] relation) {
		List<List<Integer>> collection = new ArrayList<>();
		backtrack(relation, 0, new ArrayList<>(), collection);

		return collection.size() - cntSubSet(collection);
	}

	public static void main(String[] args) {
		String[][] relation1 = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "music", "2" } };
		System.out.println(solution(relation1));
		System.out.println();
		String[][] relation2 = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
				{ "600", "apeach", "math", "3" } };
		System.out.println(solution(relation2));
		System.out.println();
		String[][] relation3 = { { "123", "100", "ryan", "music", "2" }, { "123", "200", "apeach", "math", "2" },
				{ "123", "300", "tube", "computer", "3" }, { "123", "400", "con", "computer", "4" },
				{ "123", "500", "muzi", "music", "3" }, { "123", "600", "apeach", "math", "3" } };
		System.out.println(solution(relation3));
		System.out.println();
		String[][] relation4 = { { "123", "100" }, { "123", "200" }, { "123", "300" }, { "123", "400" },
				{ "123", "500" }, { "123", "600" } };
		System.out.println(solution(relation4));
		System.out.println();
		String[][] relation5 = { { "100" }, { "200" }, { "300" }, { "400" }, { "500" }, { "600" } };
		System.out.println(solution(relation5));
		System.out.println();
		String[][] relation6 = { { "100" }, { "100" }, { "300" }, { "400" }, { "500" }, { "600" } };
		System.out.println(solution(relation6));
		System.out.println();
		String[][] relation7 = { { "100", "200" } };
		System.out.println(solution(relation7));
	}
}