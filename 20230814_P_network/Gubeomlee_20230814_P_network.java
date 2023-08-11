package Solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Gubeomlee_20230814_P_network {
	// 네트워크 담색하면서 연결된 노드 수 찾는 메서드
	public static int bfs(int n, List<int[]> wires) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int[] arr : wires) {
			map.putIfAbsent(arr[0], new HashSet<>());
			map.putIfAbsent(arr[1], new HashSet<>());
			map.get(arr[0]).add(arr[1]);
			map.get(arr[1]).add(arr[0]);
		}

		Set<Integer> set = new HashSet<>();
		Queue<Integer> que = new LinkedList<>(); // BFS를 위한 큐
		que.add(wires.get(0)[0]); // 첫 노드를 시작으로 연결된 노드들을 큐에 추가하여 탐색 시작
		que.addAll(map.get(wires.get(0)[0]));
		while (!que.isEmpty()) { // BFS 탐색
			int cur = que.poll();
			if (set.add(cur)) {
				que.addAll(map.get(cur)); // 연결된 노드 큐에 추가
			}
		}
		// 두 네트워크 크기 비교
		return Math.abs(Math.abs(n - set.size()) - set.size());
	}

	public static int solution(int n, int[][] wires) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n - 1; i++) {
			List<int[]> list = new ArrayList<>();
			for (int j = 0; j < n - 1; j++) {
				if (i != j) { // 하나의 연결을 끊었을 때 네트워크 크기 계산
					list.add(wires[j]);
				}
			}
			result = Math.min(result, bfs(n, list));
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
		int result = solution(n, wires);
		System.out.println(result);
	}
}