package Algorithm.algorithm.programmers;

public class Jwon_20231011_P_보행자천국 {
	static final int MOD = 20170805;
	static int[][] visited;

	public static int solution(int m, int n, int[][] cityMap) {
		int answer = 0;
		visited = new int[m][n];
		answer = dfs(cityMap, m, n, 0, 0, 0, 0);
		return answer;
	}

	private static int dfs(int[][] cityMap, int m, int n, int r, int c, int beforeR, int beforeC) {
		if(visited[r][c] != 0 && cityMap[r][c] == 0) {
			return visited[r][c];
		}
		if (r == m - 1 && c == n - 1) {
			return 1;
		}
		int right = 0;
		int down = 0;
		if (cityMap[r][c] == 0) {
			if (r + 1 < m && cityMap[r + 1][c] != 1) {
				visited[r + 1][c] = dfs(cityMap, m, n, r + 1, c, r, c) % MOD;
				down = visited[r + 1][c];
			}
			if (c + 1 < n && cityMap[r][c + 1] != 1) {
				visited[r][c + 1] = dfs(cityMap, m, n, r, c + 1, r, c) % MOD;
				right = visited[r][c + 1];
			}
		} else if (cityMap[r][c] == 2) {
			if (beforeR == r) {
				if (c + 1 < n && cityMap[r][c + 1] != 1) {
					visited[r][c + 1] = dfs(cityMap, m, n, r, c + 1, r, c) % MOD;
					right = visited[r][c + 1];
				}
			} else if (beforeC == c) {
				if (r + 1 < m && cityMap[r + 1][c] != 1) {
					visited[r + 1][c] = dfs(cityMap, m, n, r + 1, c, r, c) % MOD;
					down = visited[r + 1][c];
				}
			}
		}
		return visited[r][c] = (right + down) % MOD;
	}

	public static void main(String[] args) {
		int m = 3;
		int n = 3;
		int[][] cityMap = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		System.out.println(solution(m, n, cityMap));
		m = 3;
		n = 6;
		int[][] cityMap2 = { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } };
		System.out.println(solution(m, n, cityMap2));
	}
}
