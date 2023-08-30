package Algorithm.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jwon_20230831_P_빛의경로사이클_재귀 {
	static int[] dirY = { 1, 0, -1, 0 };
	static int[] dirX = { 0, 1, 0, -1 };
	static Node[][] arr;

	public static int[] solution(String[] grid) {
		int n = grid.length;
		int m = grid[0].length();
		arr = new Node[n][m];
		for (int i = 0; i < n; i++) {
			String str = grid[i];
			for (int j = 0; j < m; j++) {
				Node node = new Node(str.charAt(j));
				arr[i][j] = node;
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int dir = 0; dir < 4; dir++) {
					int temp = find(i,j,dir,0);
					if(temp != 0) {
						list.add(temp);
					}
				}
			}
		}
		
		Collections.sort(list);
		int[] answer = new int[list.size()];
		for(int i = 0 ; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

	static private int find(int y, int x, int dir, int count) {
		if(!arr[y][x].urld[dir]) {
			arr[y][x].urld[dir] = true;
		}else {
			return count;
		}
		
		if (arr[y][x].dir == 'R') {
			dir++;
			dir %= 4;
		} else if (arr[y][x].dir == 'L') {
			dir--;
			if(dir < 0) {
				dir = 3;
			}
		}
		
		int nextY = y + dirY[dir];
		int nextX = x + dirX[dir];
		if(nextY < 0) {
			nextY = arr.length-1;
		}
		if(nextX < 0) {
			nextX = arr[y].length-1;
		}
		if(nextY >= arr.length) {
			nextY = 0;
		}
		if(nextX >= arr[y].length) {
			nextX = 0;
		}
		return find(nextY, nextX, dir, count + 1);
	}

	static class Node {
		boolean[] urld = new boolean[4];
		char dir;

		public Node(char dir) {
			this.dir = dir;
		}

	}

	public static void main(String[] args) {
		String[] str1 = {"SL","LR"};
		String[] str2 = {"S"};
		String[] str3 = {"R","R"};
		String[] str4 = {"R","SR"};
		
		System.out.println(Arrays.toString(solution(str1)));
		System.out.println(Arrays.toString(solution(str2)));
		System.out.println(Arrays.toString(solution(str3)));
		System.out.println(Arrays.toString(solution(str4)));
	}
}
