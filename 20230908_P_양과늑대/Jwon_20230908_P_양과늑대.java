package Algorithm.algorithm.programmers;

import java.util.ArrayList;

public class Jwon_20230908_P_양과늑대 {
	/*
	 * 양을 만날 때마다 새롭게 갈 수 있는 (뚫을 수 있는 늑대) 노드들이 새로 생길 수 있음을 알 수 있다. 따라서 양을 만날 때마다 (늑대를
	 * 만날 때는 새로 갈 수 있는 곳이 나타나지 않으므로 양을 만날 때만 고려해준다.) 새롭게 갈 수 있는 list를 만들어준다. (기존에 있던
	 * 것에 새로 갈 수 있는 노드들을 추가해주는 식으로 하였다.) 각 노드를 방문한다면 갈 수 있는 list에서 삭제를 해준다.
	 */
	
	// 최종 양의 결과
	static int result = 0;
	// 각 노드와 자식노드간의 관계
	// 0 : left, 1 : right
	// 만약 -1이 나온다면 자식 노드가 없는 것이다.
	static int[][] arr;

	public static int solution(int[] info, int[][] edges) {
		int n = info.length;
		// 0 : left, 1 : right
		arr = new int[n][2];
		// 우선 -1로 초기화 해준다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = -1;
			}
		}
		
		for (int i = 0; i < n - 1; i++) {
			int from = edges[i][0];
			int to = edges[i][1];
			// left가 없다면 left부터 left가 있다면 right에 간선들을 연결해준다.
			if (arr[from][0] == -1) {
				arr[from][0] = to;
			} else {
				arr[from][1] = to;
			}
		}
		// 현재 상황에서 갈 수 있는 list를 선언한다.
		ArrayList<Integer> list = new ArrayList<>();
		// 현재 상황에서 갈 수 있는 노드는 0.
		list.add(0);
		find(1, 0, list, 0, info);
		return result;
	}

	private static void find(int sheep, int wolf, ArrayList<Integer> list, int node, int[] info) {
		// 만약 새로 들어온 노드에서 양과 늑대의 마릿수가 같다면 안되기 때문에 리턴.
		if (sheep == wolf) {
			return;
		}
		// 새로 들어온 곳에서 양의 최대를 갱신해준다.
		result = Math.max(result, sheep);
		
		// 처음엔 list 파라미터를 넘기고 받으면 지역변수로 되는 줄 알았는데 아니었다.
		// list의 주소값 때문인 지 계속 list가 이전 레벨에서도 변경되었다.
		// 따라서 tempList로 지역 변수를 만들어주고 해야하였다.
		// java.util.ConcurrentModificationException 에러가 떠서 알게 되었다.
		// 위의 에러는 iterator를 이용하여 반복문을 돌릴 때 만약 list에 제거된 어떠한 object가 있다면 나오는 에러이다.
		// for문을 돌리고 다음 레벨로 list를 넘겨줘서 list안의 object를 제거해주었는데 위의 에러가 떴다는 것은 
		// 이전 레벨에서의 list도 변경되고 있음을 알 수 있다.
		ArrayList<Integer> tempList = (ArrayList<Integer>)list.clone();
		
		// 방문한 곳의 node는 삭제한다.
		tempList.remove(tempList.indexOf(node));

		// 노드와 연결되어 있는 자식 노드들을 현재 상황에서 방문할 수 있는 가능성이 있는 노드들이기 때문에 list에 추가해준다.
		for (int i = 0; i < 2; i++) {
			if (arr[node][i] != -1) {
				tempList.add(arr[node][i]);
			}
		}
		for (int i = 0; i < tempList.size(); i++) {
			int temp = tempList.get(i);
			if (info[temp] == 0) {
				// 만약 방문할 수 있는 가능성이 있는 노드가 양이라면 양의 마릿수를 +1 한 후 다음 레벨로 들어간다.
				find(sheep + 1, wolf, tempList, temp, info);
			} else {
				// 만약 방문할 수 있는 가능성이 있는 노드가 늑대라면 늑대의 마릿수를 +1 한 후 다음 레벨로 들어간다.
				find(sheep, wolf + 1, tempList, temp, info);
			}
		}
	}

	public static void main(String[] args) {
		int[] info1 = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges1 = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		int[] info2 = {0,1,0,1,1,0,1,0,0,1,0};
		int[][] edges2 = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
		System.out.println(solution(info1, edges1));
		System.out.println(solution(info2, edges2));
	}
}
