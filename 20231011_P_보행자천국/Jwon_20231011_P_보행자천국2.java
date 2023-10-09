package Algorithm.algorithm.programmers;

public class Jwon_20231011_P_보행자천국2 {
	static final int MOD = 20170805;

	// 가로 세로 따로 생각해준다.
	public static int solution(int m, int n, int[][] cityMap) {
		int answer = 0;
		int[][][] visited = new int[2][m + 1][n + 1];
		visited[0][0][0] = 1;
//		visited[0][0][1] = 1;
//		visited[1][1][0] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 1은 무시.
				if (cityMap[i][j] == 1) {
					continue;
				}
				// 0일 땐 현재 가로에서 왔는 지 세로에서 왔는 지 구별해줄 필요가 없다.
				// 다시말해, 가로에서 온 횟수와 세로에서 온 횟수를 더한 후 다음 단계로 넘어가야 한다.
				// 가로에서 온 횟수 + 세로에서 온 횟수 : visited[0][i][j] + visited[1][i][j]
				if (cityMap[i][j] == 0) {
					// 가로
					visited[0][i][j + 1] = (visited[0][i][j + 1] + visited[0][i][j] + visited[1][i][j]) % MOD;
					// 세로
					visited[1][i + 1][j] = (visited[1][i + 1][j] + visited[0][i][j] + visited[1][i][j]) % MOD;
				} else {
					// 방향을 신경써야 할 때 
					// 다음 단계에서 가로의 값은 가로에서 온 값에만 영향을 받고,
					// 세로의 값은 세로에서 온 값에만 영향을 받는다.
					
					// 가로
					visited[0][i][j + 1] = (visited[0][i][j + 1] + visited[0][i][j]) % MOD;
					// 세로
					visited[1][i + 1][j] = (visited[1][i + 1][j] + visited[1][i][j]) % MOD;
				}
			}
		}
		answer = (visited[0][m - 1][n - 1] + visited[1][m - 1][n - 1]) % MOD;
		return answer;
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
