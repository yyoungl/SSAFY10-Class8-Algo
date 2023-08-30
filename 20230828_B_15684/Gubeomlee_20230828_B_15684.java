package Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Gubeomlee_20230828_B_15684 {
	// 가로선이 연속해서 나온경우 제거하는 메서드
	public static boolean inSameNode(boolean[][] matrix, int n, int h) {
		for (int i = 2; i < n; i++) {
			for (int j = 1; j < h + 1; j++) {
				if (matrix[i - 1][j] && matrix[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// 자기 자신으로 도달하는 사다리인지 확인하는 메서드
	public static boolean isValid(boolean[][] matrix, int n, int h) {
		for (int i = 1; i < n; i++) {
			int node = i;
			int edge = 1;
			for (edge = 1; edge < h + 1; edge++) {
				if (node < n && matrix[node][edge]) {
					node++;
				} else if (1 < node && matrix[node - 1][edge]) {
					node--;
				}
			}
			if (node != i) {
				return false; // 자기자신으로 도달하지 못하는 경우
			}
		}
		return true;
	}

	// 추가 가능한 가로선 조합을 만드는 메서드
	public static void backtrack(List<int[]> edge, int depth, List<int[]> temp, List<List<int[]>> combination) {
		if (depth == 0) {
			combination.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < edge.size(); i++) {
				List<int[]> subList = new ArrayList<>(edge.subList(i + 1, edge.size()));
				temp.add(edge.get(i));
				backtrack(subList, depth - 1, temp, combination);
				temp.remove(temp.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int h = sc.nextInt();

		boolean[][] matrix = new boolean[n][h + 1];
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			matrix[b][a] = true; // 가로선 존재하는 경우
		}
		// 추가 할 수 있는 가로선 리스트
		List<int[]> edge = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < h + 1; j++) {
				if (!matrix[i][j]) {
					edge.add(new int[] { i, j });
				}
			}
		}

		for (int i = 0; i <= m; i++) {
			if (i <= 3) {
				List<List<int[]>> combination = new ArrayList<>();
				// i개의 가로선 조합 구하기
				backtrack(edge, i, new ArrayList<>(), combination);
				for (List<int[]> com : combination) {
					boolean[][] copyM = new boolean[n][h + 1];
					for (int j = 0; j < n; j++) {
						copyM[j] = Arrays.copyOf(matrix[j], h + 1);
					}
					for (int[] idx : com) {
						copyM[idx[0]][idx[1]] = true;
					}
					if (inSameNode(copyM, n, h) && isValid(copyM, n, h)) {
						System.out.println(i);
						return;
					}
				}
			} else {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(-1);
		sc.close();
	}
}