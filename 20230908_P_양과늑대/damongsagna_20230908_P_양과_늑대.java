
public class damongsagna_20230908_P_양과_늑대 {
	static int answer = 0;

	public int solution(int[] info, int[][] edges) {
		
		// 문제는 Tree 형태로 주었지만 위로 다시 올라가야하기 때문에 그래프 탐색(DFS)로 푸는 것이 좋아보임		
		int N = info.length;
		boolean[][] edge = new boolean[N][N];
		for (int i = 0; i < N - 1; i++) { // 양방향 간선 (위로 올라가는 경우가 있음)
			edge[edges[i][0]][edges[i][1]] = true;
			edge[edges[i][1]][edges[i][0]] = true;
		}
		
		boolean[] visited = new boolean[N]; // 방문배열
		boolean[] selected = new boolean[N]; // 선택배열, 방문배열과 구분해야 한다. 다시 방문했을 때 sheep or wolf 카운트를 늘리면 안되기 때문
		selected[0] = true; // 선택은 먼저 (나중에 원복해야함)
		
		dfs(0, 1, 0, selected, visited, N, info, edge);

		return answer;
	}

	static void dfs(int now, int sheep, int wolf, boolean[] selected, boolean[] visited, int N, int[] info, boolean[][] edge) {
		
		System.out.println(now + " " + sheep + " " + wolf);
		if (wolf >= sheep) // 탈출조건
			return;
	
		answer = Math.max(answer, sheep);	
		visited[now] = true;
		
		for (int i = 0; i < N; i++) {
			if (!edge[now][i]) continue;
			if (visited[i]) continue;
			if (selected[i]) {
				dfs(i, sheep, wolf, selected, visited, N, info, edge);
			} else {
				selected[i] = true; // selected는 원복해야함
				if (info[i] == 0)
					// select되지 않은 양 (새로운 양)을 만났을 때 visited 초기화
					dfs(i, sheep+1, wolf, selected, new boolean[N], N, info, edge); // 양을 찾았다면 visited 배열을 초기화
				else
					dfs(i, sheep, wolf+1, selected, visited, N, info, edge);
				selected[i] = false; // selected는 원복해야함
			}
		}

	}

}
