import java.util.ArrayList;
import java.util.Arrays;

public class P피로도 {

    // 구한 순서 배열을 담을 arraylist. 만약 factorial로 배열의 크기를 계산한다면 메모리 아낄 수 있을 듯 ^ ^ ...
	static ArrayList<int[]> permu = new ArrayList<>();
	static int N;
	static int[] sequence;

	public static void main(String[] args) {
		int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
		int k = 80;

		System.out.println(solution(k, dungeons));

	}

	public static int solution(int k, int[][] dungeons) {
		int answer = -1;
		N = dungeons.length;
		boolean[] check = new boolean[N];
		sequence = new int[N];

		// 던전 탐색 순서를 싹 다 구했음
        permutation(0, check, sequence);

        // 하나씩 살펴보면서 
		for (int[] p : permu) {
            // 이 순서로 갈 경우 피로도 갱신
			int tired = k;
            // 갈 수 있는 던전의 수
			int cnt = 0;
			for (int i : p) {
                // 최소 필요 체력보다 많은 체력이 남았는가? 그리고 그 던전을 탐험할 체력이 되는가?
				if (tired>=dungeons[i][0] && tired>=dungeons[i][1]) {
					cnt++;
					tired -= dungeons[i][1];
				}
			}
			answer = Math.max(cnt, answer);
		}
		return answer;
	}

    // dfs로 순열 구하기
	public static void permutation(int r, boolean[] check, int[] sequence) {
		if (r == N) {
			int[] result = Arrays.copyOf(sequence, N);
			permu.add(result);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!check[i]) {
				check[i] = true;
				sequence[r] = i;
				permutation(r + 1, check, sequence);
				check[i] = false;
			}
		}
	}

}
