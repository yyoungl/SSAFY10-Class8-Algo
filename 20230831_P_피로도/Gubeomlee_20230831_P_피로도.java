package algo;

import java.util.ArrayList;
import java.util.List;

public class Gubeomlee_20230831_P_피로도 {
	public static void backtrack(int[][] matrix, List<Integer> list, int k, int cnt, int depth, int[] result) {
		if (k < 0) { // 탐험 불가능한 상황
			result[0] = Math.max(result[0], cnt - 1);
		} else if (depth == 0) { // 모든 던전을 탐험한 경우
			result[0] = Math.max(result[0], cnt);
		} else {
			for (int i = 0; i < list.size(); i++) {
				List<Integer> subList = new ArrayList<>(list.subList(0, i));
				subList.addAll(list.subList(i + 1, list.size()));
				if (k >= matrix[list.get(i)][0]) { // 탐험 가능한 경우
					backtrack(matrix, subList, k - matrix[list.get(i)][1], cnt + 1, depth - 1, result);
				} else { // 탐험 불가능한 경우
					result[0] = Math.max(result[0], cnt);
				}
			}
		}
	}

	public static int solution(int k, int[][] dungeons) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < dungeons.length; i++) {
			list.add(i);
		}
		int[] result = { 0 };
		backtrack(dungeons, list, k, 0, dungeons.length, result);

		return result[0];
	}

	public static void main(String[] args) {
		int k1 = 80;
		int[][] dungeons1 = { { 80, 20 }, { 50, 40 }, { 30, 20 } };
		System.out.println(solution(k1, dungeons1));
	}
}