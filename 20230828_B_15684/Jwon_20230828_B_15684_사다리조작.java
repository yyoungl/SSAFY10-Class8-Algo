package Algorithm.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Jwon_20230828_B_15684_사다리조작 {

	static int n;
	static int m;
	static int h;
	static int result;
	static int[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		// h+2인 이유 만약 6이라면 0~7까지 라고 생각
		// 0 : 시작, 7 : 끝.
		ladder = new int[h + 2][n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			// 사다리 만들기 ladder[y][x]의 값은 같은 y의 x값이다.
			ladder[y][x] = x + 1;
			// 양방향으로 만들어준다.
			ladder[y][x + 1] = x;
		}

		// 다리를 연결할 수 있는 후보군들.
		ArrayList<Ladder> ladderList = new ArrayList<>();
		for (int i = 1; i < h + 1; i++) {
			for (int j = 1; j < n; j++) {
				if (ladder[i][j] == 0) {
					ladderList.add(new Ladder(i, j));
				}
			}
		}
		// 최대 값은 0 이므로 4로 두고 result가 여전히 4라면 -1을 출력한다.
		result = 4;
		combination(ladderList, 0, 0, 0);
		if(result == 4) {
			result = -1;
		}
		System.out.println(result);
	}

	private static void combination(ArrayList<Ladder> ladderList, int start, int count, int depth) {

		// result가 현재 세는 수보다 작다면 더 이상 할 필요가 없으므로 return.
		if(result <= count) {
			return ;
		}
		// 현재의 깊이가 3이라면 완벽한 사다리인 지 확인.
		if (depth == 3) {
			// 사다리의 가로는 1~n이라서 j < n + 1이어야 하지만 n까지만 해도 나머진 확정이므로 n까지만 했다.
			for (int j = 1; j < n ; j++) {
				int result = dfs(j, 0);
				// j에서 출발한 사다리의 목적지가 j와 같지 않다면 리턴.
				if (result != j) {
					return ;
				}
			}
			// 완벽한 사다리라면 result 변경.
			result = count;
			return ;
		}
		// 우선 제일 깊게 들어간 후 depth가 3일 때 무조건 사다리 확인을 해주었다.
		// 그렇다면 처음엔 아무것도 들어가지 않은 count = 0일 때를 확인하고,
		// 그 후 count = 1일 때 (하나만 들어간 경우)를 확인하고,
		// 그 후 count = 2일 때 (두개만 들어간 경우)를 확인하고,
		// 계속  count가 증가하고 depth가 하나씩 작아지면서 확인할 갯수가 많아진도록 한다. 
		// (depth가 하나씩 작아지면 detph가 3까지 와야되므로 count가 하나씩 증가하면서 확인한다.)
		combination(ladderList, start, count, depth+1);
		for (int i = start; i < ladderList.size(); i++) {
			Ladder temp = ladderList.get(i);
			// 현재의 값과 다음의 값이 0이어야 다리를 놓을 수 있다.
			// 조합을 찾아준다.
			if (ladder[temp.y][temp.x] == 0 && ladder[temp.y][temp.x + 1] == 0) {
				ladder[temp.y][temp.x] = temp.x + 1;
				ladder[temp.y][temp.x + 1] = temp.x;
				combination(ladderList, start + 1, count + 1, depth + 1);
				ladder[temp.y][temp.x] = 0;
				ladder[temp.y][temp.x + 1] = 0;
			} 
		}
	}

	private static int dfs(int x, int y) {
		// 끝까지 온다면 그 때의 x를 반환한다.
		if (h + 1 == y) {
			return x;
		}

		// 다리가 없다면 y만 증가
		if (ladder[y][x] == 0) {
			return dfs(x, y + 1);
		}

		// 다리가 있다면 가르키는 x와 y 증가
		return dfs(ladder[y][x], y + 1);
	}

	static class Ladder {
		int y;
		int x;

		public Ladder(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}