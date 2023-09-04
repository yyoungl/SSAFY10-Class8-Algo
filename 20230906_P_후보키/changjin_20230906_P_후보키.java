package Programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class �ĺ�Ű {

	static String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
			{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
			{ "600", "apeach", "music", "2" } };
	// �� n���� �Ӽ��� ������, 1~n���� �Ӽ��� Ȯ���ؾ� �ϹǷ� ũ�⸦ �Ӽ��� ������ �ϴ� üũ �迭 ����.
	static boolean[] check = new boolean[relation[0].length];
	// String�� ���ľ� �ϴ� ���� �����Ƿ� StringBuilder ���
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	// �ߺ����� �ִ��� Ȯ���ϱ� ���� HashSet ����.
	static HashSet<String> set = new HashSet<>();
	// �ĺ�Ű ���� ������ list ����
	static ArrayList<String> list = new ArrayList<>();

	static void DFS(int idx, int k, int L) {
		if (k == L) {
			// k���� �ĺ�Ű�� �̾����� ���� ���� �Ӽ����� �ε����� sb�� ����.
			// String��ü�� ��� �����ϸ� �ð�, �޸� �������� ��ȿ����.
			for (int i = 0; i < relation[0].length; i++) {
				if (check[i])
					sb.append(i);
			}
			// list ũ�Ⱑ 0�� ������ list�߿� �ߺ��� �ִ��� Ȯ�� �� �ʿ� x
			if (list.size() != 0) {

				for (int i = 0; i < list.size(); i++) {
					boolean flag = true;
					for (int j = 0; j < list.get(i).length(); j++) {
						if (sb.toString().contains(list.get(i).substring(j, j + 1)))
							continue;
						else {
							flag = false;
							break;
						}
					}
					if (flag) {
						sb.setLength(0);
						return;
					}
				}

			}

			for (int i = 0; i < relation.length; i++) {
				for (int j = 0; j < sb.length(); j++) {
					sb2.append(relation[i][Integer.parseInt(sb.substring(j, j + 1))]);
				}
				set.add(sb2.toString());
				sb2.setLength(0);
			}

			if (set.size() == relation.length) {
				list.add(sb.toString());
			}
			sb.setLength(0);
			set.clear();

		} else {
			// k�� ��ŭ�� �ĺ�Ű�� ����
			for (int i = idx; i < relation[0].length; i++) {
				check[i] = true;
				DFS(i + 1, k, L + 1);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		// ���� �ε���, ���� �Ӽ��� ����, ���̸� �������ڷ� �������ش�.
		// �ߺ��� �ʿ䰡 ���� ������ �ε����� ���ڷ� �־��־���, ũ�Ⱑ ���� �ĺ�Ű ���� �̾ƾ� ��Ҽ� ������ ���� �� �����Ƿ� �̴� ������ 1����
		// ����.
		for (int i = 1; i <= relation[0].length; i++) {
			DFS(0, i, 0);
		}
		System.out.println(list.size());
	}
}
