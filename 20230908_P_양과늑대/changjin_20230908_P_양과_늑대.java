package Programmers;

import java.util.ArrayList;

class Solution {

	static int answer = Integer.MIN_VALUE;
	static ArrayList<Integer>[] arr;

	static void DFS(ArrayList<Integer> list, int sheep, int wolf, int nextnode, int[] info, int[][] edges) {
		// ���� ���� ��忡 ����Ǿ��ִ°� ������ �������� Ȯ�� �� ���� �� ���� �÷��ش�.
		if (info[nextnode] == 0)
			sheep++;
		else
			wolf++;
		// ��� ������ ���� �� �Ҵ� �� �� ���� ���� ����.
		// if�� ������ ���� �ʴ� ������ �ߴ� ���� �� �ϳ��� list.isEmpty()�� �ؿ��� ���޹��� ��带 �����ϱ� ������ �۵����� ����.
		answer = Math.max(answer, sheep);
		// �ߴ� ������ �� ���� , �� �� �ִ� ��尡 ���� ���� ���� ���� ��带 �ؿ��� �����ϹǷ� ���⼭ �ɸ��� �ʴ´�.
		if (sheep == wolf)
			return;
		// ���� ���� ����Ʈ�� �����ϱ� ���� ����Ʈ2 ����
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		// ���� �� ���� �� ��� ����
		for (int i = 0; i < list.size(); i++) {
			list2.add(list.get(i));
		}
		list2.remove(Integer.valueOf(nextnode));
		// ���� ��忡�� �� �� �ִ� ���� ����Ʈ�� �߰�
		for (int i = 0; i < arr[nextnode].size(); i++) {
			list2.add(arr[nextnode].get(i));
		}

		for (int i = 0; i < list2.size(); i++) {
			DFS(list2, sheep, wolf, list2.get(i), info, edges);
		}

	}

	public int solution(int[] info, int[][] edges) {
		// �� ��忡�� �� �� �ִ� ������ �ѹ��� ��� ���� ArrayList �迭�� ����.
		// �� ��忡�� �� �� �ִ� ���� 0~2 �̹Ƿ� list�� ����.
		arr = new ArrayList[info.length];
		// �迭�� ������ ������ list ���� �� �Ҵ�.
		for (int i = 0; i < info.length; i++) {
			arr[i] = new ArrayList<>();
		}
		// i��° �ε忡�� j�� ���� �� �� �ִٴ� ������ arr[i] �ε����� ����Ǿ��ִ� list�� j���� �߰��ϴ� ������ ����.
		for (int i = 0; i < edges.length; i++) {
			arr[edges[i][0]].add(edges[i][1]);
		}
		// DFS�� ���ڰ� (���� ��Ȳ���� �� �� �ִ� ������ ������ ����Ʈ, ���� ����, ������ ����, ������ �� ���, info, edges)
		// �� ����Ʈ�� ó�� ���� ���� 0�̹Ƿ� 0�� �Ѱ��ش�.
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		DFS(list, 0, 0, 0, info, edges);
		return answer;
	}
}