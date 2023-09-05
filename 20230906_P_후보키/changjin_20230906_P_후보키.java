package Programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class 후보키 {

	static String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
			{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
			{ "600", "apeach", "music", "2" } };
	// 총 n개의 속성이 있을때, 1~n개의 속성을 확인해야 하므로 크기를 속성의 개수로 하는 체크 배열 선언.
	static boolean[] check = new boolean[relation[0].length];
	// String을 합쳐야 하는 일이 많으므로 StringBuilder 사용
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	// 중복값이 있는지 확인하기 위한 HashSet 선언.
	static HashSet<String> set = new HashSet<>();
	// 후보키 들을 저장할 list 선언
	static ArrayList<String> list = new ArrayList<>();

	static void DFS(int idx, int k, int L) {
		if (k == L) {
			// k개의 후보키를 뽑았을때 먼저 뽑은 속성값의 인덱스를 sb에 저장.
			// String객체를 계속 생성하면 시간, 메모리 차원에서 비효율적.
			for (int i = 0; i < relation[0].length; i++) {
				if (check[i])
					sb.append(i);
			}
			// list 크기가 0일 때에는 list중에 중복이 있는지 확인 할 필요 x
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
			// k개 만큼의 후보키를 뽑음
			for (int i = idx; i < relation[0].length; i++) {
				check[i] = true;
				DFS(i + 1, k, L + 1);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		// 시작 인덱스, 뽑을 속성의 개수, 깊이를 전달인자로 전달해준다.
		// 중복이 필요가 없기 떄문에 인덱스를 인자로 넣어주었고, 크기가 작은 후보키 부터 뽑아야 희소성 조건을 맞출 수 있으므로 뽑는 개수를 1부터
		// 넣음.
		for (int i = 1; i <= relation[0].length; i++) {
			DFS(0, i, 0);
		}
		System.out.println(list.size());
	}
}
