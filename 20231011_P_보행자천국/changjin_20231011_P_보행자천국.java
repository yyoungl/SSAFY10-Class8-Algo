import java.util.PriorityQueue;

class Solution {
    int MOD = 20170805;
    static long[][] Count;
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };



	static class point implements Comparable<point> {
		int x, y;
		long len;

		public point(int x, int y, long len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

		// 포인트의 거리가 작을수록 우선순위가 높음.
		@Override
		public int compareTo(point o) {
			// TODO Auto-generated method stub
			return (int) (this.len - o.len);
		}

	}
    public int solution(int m, int n, int[][] cityMap) {
       Count = new long[m][n];
		// 출발지는 1가지 이므로 1으로 초기화
		Count[0][0] = 1;
		PriorityQueue<point> pq = new PriorityQueue<>();
		// 시작점 넣어주기
		pq.add(new point(0, 0, 0));
		while (!pq.isEmpty()) {
			point p = pq.poll();
			// 오른쪽, 아래 방향 탐색
			for (int i = 0; i < 2; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 다음 칸이 0 인 경우
				if (nx >= 0 && nx < m && ny >= 0 && ny < n && cityMap[nx][ny] == 0) {
					// 처음 방문인 경우 pq 에 넣어주기
					if (Count[nx][ny] == 0)
						pq.add(new point(nx, ny, nx + ny));
                    // 카운트 배열 값 갱신
					Count[nx][ny] += Count[p.x][p.y];
					Count[nx][ny] %= MOD;
				} // 다음 칸의 값이 2인 경우  
                else if (nx >= 0 && nx < m && ny >= 0 && ny < n && cityMap[nx][ny] == 2) {
                    // map을 벗어나지 않고 2가 나오면 계속 진행방향으로 이동시킴
					while (nx >= 0 && nx < m && ny >= 0 && ny < n && cityMap[nx][ny] == 2) {
						nx += dx[i];
						ny += dy[i];
					}
                    // 멈춘 지점이 배열을 벗어나지 않았고 map의 값이 0이면 위와 같은 로직 수행.
					if (nx >= 0 && nx < m && ny >= 0 && ny < n && cityMap[nx][ny] == 0) {
						if (Count[nx][ny] == 0)
							pq.add(new point(nx, ny, nx + ny));
						Count[nx][ny] += Count[p.x][p.y];
						Count[nx][ny] %= MOD;
					}
				}
			}
		}
        
		return (int)Count[m - 1][n - 1];
    }
}