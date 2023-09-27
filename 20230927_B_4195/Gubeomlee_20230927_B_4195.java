import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Gubeomlee_20230927_B_4195 {
	public static int find(int[][] set, int num) {
		if (set[num][1] != num) {
			set[num][1] = find(set, set[num][1]);
		}
		return set[num][1];
	}

	public static int getCnt(int[][] set, int min, int max) {
		// 대표값이 더 작은 쪽에 연결된 수를 더하고, 큰쪽은 0을로 만든다.
		if (min != max) {
			set[min][0] += set[max][0];
			set[max][0] = 0;
		}
		return set[min][0];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int len = sc.nextInt();

			int[][] set = new int[len * 2][2];
			for (int i = 0; i < len * 2; i++) {
				set[i][0] = 1; // 자기자신과 연결된 친구의 수
				set[i][1] = i; // 대표값
			}
			// 이름을 숫자로 저장한다.
			int idx = 0;
			Map<String, Integer> map = new HashMap<>();

			for (int i = 0; i < len; i++) {
				String p1 = sc.next();
				String p2 = sc.next();
				if (!map.containsKey(p1)) {
					map.put(p1, idx++);
				}
				if (!map.containsKey(p2)) {
					map.put(p2, idx++);
				}
				// 대푯값의 최솟값과 최댓값을 구한다.
				int min = Math.min(find(set, map.get(p1)), find(set, map.get(p2)));
				int max = Math.max(find(set, map.get(p1)), find(set, map.get(p2)));
				// 유니온
				set[find(set, max)][1] = find(set, min);
				System.out.println(getCnt(set, min, max));
			}
		}
		sc.close();
	}
}