package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gubeomlee_20230901_P_빛의경로사이클 {
	// 특정위치, 특정방향의 경로 길이 측정하는 메서드
	public static void getPath(String[] grid, int h, int w, int row, int col, int dir, List<Integer> list,
			int[][][] check) {
		int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int cnt = 1;
		int r = row;
		int c = col;
		check[r][c][dir] = 1;
		// 경로방향대로 이동하면서 경로 길이 측정
		do {
			char ch = grid[r].charAt(c);
			if (ch == 'R') {
				dir -= 1;
				if (dir < 0) {
					dir = 3;
				}
			} else if (ch == 'L') {
				dir = (dir + 1) % 4;
			}

			r += direction[dir][0];
			c += direction[dir][1];

			if (r >= h)
				r = 0;
			if (r < 0)
				r = h - 1;
			if (c >= w)
				c = 0;
			if (c < 0)
				c = w - 1;

			if (check[r][c][dir] == 1) { // 방문했던 곳을 방면하면 중단
				break;
			} else {
				check[r][c][dir] = 1; // 방문 표시
			}
			cnt++;
		} while (true);

		if (row == r && col == c) { // 탐색 종료 위치가 시작위치인 경우
			list.add(cnt);
		}
	}

	public static int[] solution(String[] grid) {
		int h = grid.length;
		int w = grid[0].length();

		List<Integer> list = new ArrayList<>();
		int[][][] check = new int[h][w][4]; // 방문한 위치 및 그때 방향을 저장하는 메서드
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int k = 0; k < 4; k++) {
					if (check[i][j][k] == 0) { // 해당위치에서 해당방향으로 방문한 적이 없는 경우
						getPath(grid, h, w, i, j, k, list, check);
					}
				}
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		Arrays.sort(result);
		return result;
	}

	public static void main(String[] args) {
		String[] grid1 = { "SL", "LR" };
		System.out.println(Arrays.toString(solution(grid1)));
		String[] grid2 = { "S" };
		System.out.println(Arrays.toString(solution(grid2)));
		String[] grid3 = { "S", "S" };
		System.out.println(Arrays.toString(solution(grid3)));
		String[] grid4 = { "R", "R" };
		System.out.println(Arrays.toString(solution(grid4)));
	}
}