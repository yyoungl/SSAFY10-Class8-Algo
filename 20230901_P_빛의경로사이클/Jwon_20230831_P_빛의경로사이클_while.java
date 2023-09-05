package Algorithm.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jwon_20230831_P_빛의경로사이클_while {
	static int[] dirY = { 1, 0, -1, 0 };
	static int[] dirX = { 0, 1, 0, -1 };
	static Node[][] arr;

	// 하나의 방향으로 빠져나간다면 하나의 방향으로 무조건 들어온다는 것은 자명하기 때문에 모든 노드의 빠져 나가는 부분만 확인해주도록 한다.
	// 빠져나가는 방향과, 가지고 있는 빛의 굴절 방향을 저장해 놓은 Node를 만들어서 2차원 배열을 만든다.
	// 계속 빛의 방향을 변경하며 빛이 이동하다가 한 번 갔던 방향을 만난다면 한 번 사이클 돈 것이기 때문에 그만둔다.
	// 모든 방향으로 빠져나간다면 정답이 나온다.
	// 나는 글을 되게 못 쓴다. 내가 봐도 뭐라는 지 모르겠네
	
	public static int[] solution(String[] grid) {
		int n = grid.length;
		int m = grid[0].length();
		arr = new Node[n][m];
		for (int i = 0; i < n; i++) {
			String str = grid[i];
			for (int j = 0; j < m; j++) {
				// 빛이 어느 방향으로 빠져나갔다면 true를 해준다.
				// 따라서 모든 방향이 false이다.
				// 빛의 변경 방향을 저장해준다.
				Node node = new Node(str.charAt(j));
				arr[i][j] = node;
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int dir = 0; dir < 4; dir++) {
					// 모든 노드와 모든 방향을 확인한다.
					int temp = find(i,j,dir);
					if(temp != 0) {
						list.add(temp);
					}
				}
			}
		}
		
		// list를 오름차순 해준다.
		Collections.sort(list);
		int[] answer = new int[list.size()];
		for(int i = 0 ; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

	static private int find(int y, int x, int dir) {
		// 한 사이클을 할 때 빛의 경로 갯수
		int count = 0;
		// 만약 갈려는 방향이 이미 간 곳이라면 어처피 같은 사이클을 돌 것이기 때문에 확인을 할 필요가 없다.
		while(!arr[y][x].urld[dir]) {
			// 현재 방향으로 가기 때문에 true를 해준다.
			arr[y][x].urld[dir] = true;
			// 방향을 변경해준다.
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
			
			// 다음 y와 x를 변경해준다.
			y = nextY;
			x = nextX;
			count++;
		}
		
		return count;
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
