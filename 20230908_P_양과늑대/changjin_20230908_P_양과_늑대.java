package Programmers;

import java.util.ArrayList;

class Solution {

	static int answer = Integer.MIN_VALUE;
	static ArrayList<Integer>[] arr;

	static void DFS(ArrayList<Integer> list, int sheep, int wolf, int nextnode, int[] info, int[][] edges) {
		// 전달 받은 노드에 저장되어있는게 양인지 늑대인지 확인 후 적절 한 값을 올려준다.
		if (info[nextnode] == 0)
			sheep++;
		else
			wolf++;
		// 양과 늑대의 값을 재 할당 한 후 양의 값을 갱신.
		// if문 내에서 하지 않는 이유는 중단 조건 중 하나인 list.isEmpty()가 밑에서 전달받은 노드를 제거하기 떄문에 작동하지 않음.
		answer = Math.max(answer, sheep);
		// 중단 조건일 시 리턴 , 갈 수 있는 노드가 없는 경우는 전달 받은 노드를 밑에서 제거하므로 여기서 걸리지 않는다.
		if (sheep == wolf)
			return;
		// 전달 받은 리스트를 복사하기 위한 리스트2 선언
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		// 복사 및 현재 온 노드 제거
		for (int i = 0; i < list.size(); i++) {
			list2.add(list.get(i));
		}
		list2.remove(Integer.valueOf(nextnode));
		// 현재 노드에서 갈 수 있는 노드들 리스트에 추가
		for (int i = 0; i < arr[nextnode].size(); i++) {
			list2.add(arr[nextnode].get(i));
		}

		for (int i = 0; i < list2.size(); i++) {
			DFS(list2, sheep, wolf, list2.get(i), info, edges);
		}

	}

	public int solution(int[] info, int[][] edges) {
		// 각 노드에서 갈 수 있는 노드들의 넘버를 담기 위해 ArrayList 배열을 생성.
		// 각 노드에서 갈 수 있는 곳은 0~2 이므로 list가 적절.
		arr = new ArrayList[info.length];
		// 배열의 각각의 공간에 list 선언 해 할당.
		for (int i = 0; i < info.length; i++) {
			arr[i] = new ArrayList<>();
		}
		// i번째 로드에서 j번 노드로 갈 수 있다는 정보를 arr[i] 인덱스에 저장되어있는 list에 j값을 추가하는 것으로 선언.
		for (int i = 0; i < edges.length; i++) {
			arr[edges[i][0]].add(edges[i][1]);
		}
		// DFS의 인자가 (현재 상황에서 갈 수 있는 노드들을 저장한 리스트, 양의 개수, 늑대의 개수, 다음에 갈 노드, info, edges)
		// 빈 리스트와 처음 시작 노드는 0이므로 0을 넘겨준다.
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		DFS(list, 0, 0, 0, info, edges);
		return answer;
	}
}