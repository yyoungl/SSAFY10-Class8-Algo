package Algorithm.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jwon_20231102_B_로봇_조종하기 {
	static int[][] result;
	static int[][] arr;
	static boolean[][] visited;
	static int n;
	static int m;
	static final int MIN = -(100 * 1000 * 1000) - 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		result = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				result[i][j] = MIN;
			}
		}
		result[n - 1][m - 1] = arr[n - 1][m - 1];
		visited[0][0] = true;
		
//		dfs(0, 0);
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(result[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(result[0][0]);
	}

	static int[] dirX = { 1, -1, 0 };
	static int[] dirY = { 0, 0, 1 };

	private static int dfs(int y, int x) {
		if (visited[y][x]) {
			return result[y][x];
		}
		visited[y][x] = true;
		int temp = MIN;
		for (int i = 0; i < 3; i++) {
			int nextY = y + dirY[i];
			int nextX = x + dirX[i];
			if (nextY < n && nextX >= 0 && nextX < m) {
				temp = Math.max(temp, dfs(nextY, nextX));
			}
		}
//		System.out.println("temp : " + temp);
		return result[y][x] = arr[y][x] + temp;
	}

}