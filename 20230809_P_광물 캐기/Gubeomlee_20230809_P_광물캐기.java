package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
	// 광물의 종류에 따라 인덱스 반환하는 메서드
	public static int getMineralsNum(String str) {
		if (str.equals("diamond")) {
			return 0;
		} else if (str.equals("iron")) {
			return 1;
		} else {
			return 2;
		}
	}

	// 선택된 곡괭이 조합으로 스테미나 계산
	public static int getStamina(String[] minerals, List<Integer> temp, int[][] matrix) {
		int stamina = 0;
		for (int i = 0; i < temp.size(); i++) {
			for (int j = 0; j < 5; j++) {
				if (j + i * 5 < minerals.length) {
					stamina += matrix[temp.get(i)][getMineralsNum(minerals[j + i * 5])];
				}

			}
		}
		return stamina;
	}

	// 백트래킹을 통해 곡괭이 조합 생성 후 스테미나 계산
	public static int backtrack(int[] picks, String[] minerals, List<Integer> temp, int depth, int[] result,
			int[][] matrix) {
		if (depth == 0) {
			int stamina = getStamina(minerals, temp, matrix);
			result[0] = Math.min(result[0], stamina);
		} else {
			for (int i = 0; i < 3; i++) {
				if (picks[i] > 0) {
					picks[i]--;
					temp.add(i);
					backtrack(picks, minerals, temp, depth - 1, result, matrix);
					picks[i]++;
					temp.remove(temp.size() - 1);
				}
			}
		}
		return 1;
	}

	public static int solution(int[] picks, String[] minerals) {
		int[] usingPicks = new int[3];
		int len = minerals.length;
		int depth = 0;
		while (len > 0) {
			len -= 5;
			for (int i = 0; i < 3; i++) {
				if (picks[i] > 0) {
					picks[i]--;
					usingPicks[i]++;
					depth++;
					break;
				}
			}
		}

		int[] result = { Integer.MAX_VALUE };
		int[][] matrix = { { 1, 1, 1 }, { 5, 1, 1 }, { 25, 5, 1 } };
		backtrack(usingPicks, minerals, new ArrayList<>(), depth, result, matrix);
		return result[0];
	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int[] picks = { 0, 1, 1 };
		String[] minerals = { "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron",
				"iron", "diamond" };
		int result = solution(picks, minerals);
		System.out.println(result);
		sc.close();
	}
}