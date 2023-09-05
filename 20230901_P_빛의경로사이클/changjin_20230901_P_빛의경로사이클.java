package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Node {
	char name;
	boolean[] dir = new boolean[4];

	public Node(char name) {
		this.name = name;
	}
}

public class 빛의경로사이클 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static String[] grid = { "S" };
	static Node[][] arr;
	static int cnt;

	static void check(int x, int y, int dir) {

		while (!arr[x][y].dir[dir]) {
			arr[x][y].dir[dir] = true;
			cnt++;
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx == -1)
				nx = grid.length - 1;
			if (nx == grid.length)
				nx = 0;

			if (ny == -1)
				ny = grid[0].length() - 1;
			if (ny == grid[0].length())
				ny = 0;
			if (arr[nx][ny].name == 'R')
				dir++;
			if (arr[nx][ny].name == 'L')
				dir--;
			if (dir == -1)
				dir = 3;
			if (dir == 4)
				dir = 0;
			x = nx;
			y = ny;
		}

	}

	public static void main(String[] args) {

		arr = new Node[grid.length][grid[0].length()];
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length(); j++) {
				arr[i][j] = new Node(grid[i].charAt(j));
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length(); j++) {
				for (int k = 0; k < 4; k++) {
					if (arr[i][j].dir[k] == false) {
						cnt = 0;
						check(i, j, k);
						list.add(cnt);
					}
				}
			}
		}
		Collections.sort(list);
		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		System.out.println(Arrays.toString(answer));
	}
}
