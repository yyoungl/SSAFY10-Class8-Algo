package Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Gubeomlee_20230908_P_양과늑대 {
	public static void backtrack(int[] info, Map<Integer, List<Integer>> parentToChildren, List<Integer> list, int node,
			int sheep, int wolf, int[] result) {
		if (sheep > wolf || node == 0) {
			result[0] = Math.max(result[0], sheep); // 결과값 갱신
			// 자식 노드가 없고 늑대면 멈춤
			if (info[node] == 1 && !parentToChildren.containsKey(node)) {
			} else {
				List<Integer> testList = new ArrayList<>(list);
				// 현재 노드의 자식 노드를 testList에 추가
				if (parentToChildren.containsKey(node))
					for (int nextNode : parentToChildren.get(node))
						testList.add(nextNode);

				for (int i = 0; i < testList.size(); i++) {
					int temp = testList.get(i);
					testList.remove(i);
					backtrack(info, parentToChildren, testList, temp, sheep + (info[temp] ^ 1), wolf + info[temp],
							result);
					testList.add(i, temp);
				}
			}
		}
	}

	public static int solution(int[] info, int[][] edges) {
		Map<Integer, List<Integer>> parentToChildren = new HashMap<>();

		for (int i = 0; i < edges.length; i++) {
			parentToChildren.putIfAbsent(edges[i][0], new ArrayList<>());
			parentToChildren.get(edges[i][0]).add(edges[i][1]);
		}

		int[] result = { 0 };
		backtrack(info, parentToChildren, new LinkedList<>(), 0, 1, 0, result);
		return result[0];
	}

	public static void main(String[] args) {
		int[] info1 = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges1 = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		System.out.println(solution(info1, edges1));
		int[] info2 = { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
		int[][] edges2 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 },
				{ 9, 10 } };
		System.out.println(solution(info2, edges2));
	}
}