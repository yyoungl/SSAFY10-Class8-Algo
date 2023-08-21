
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Gubeomlee_20230822_B_17142 {
	// 바이러스의 확산 시간을 계산하는 메서드
	public static void getVirusTime(int[][] matrix, Queue<int[]> idx, int[] result) {
		int cnt = 0;
		int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int cntNotActive = 0;
		while (!idx.isEmpty()) {
			Queue<int[]> temp = new LinkedList<>();
			int tempCnt = 0;
			int checkNotActive = 0;
			while (!idx.isEmpty()) {
				int[] cur = idx.poll();
				matrix[cur[0]][cur[1]] = 3; // 바이러스 확산 지역 표시
				for (int[] dir : direction) {
					int row = cur[0] + dir[0];
					int col = cur[1] + dir[1];

					if (0 <= row && row < matrix.length && 0 <= col && col < matrix[0].length
							&& (matrix[row][col] == 0 || matrix[row][col] == 2)) {
						if (matrix[row][col] == 0) {
							tempCnt = 1; // 바이러스 확산 표시
						} else if (matrix[row][col] == 2) {
							checkNotActive = 1;
						}
						matrix[row][col] = 3; // 바이러스 확산 지역 표시
						temp.add(new int[] { row, col }); // 다음 확산 위치 추가
					}
				}
			}
			if (tempCnt == 0 && temp.size() > 0) {
				cntNotActive += checkNotActive;
			}
			if (tempCnt == 1) {
				tempCnt += cntNotActive;
			}
			idx = temp; // 확산된 위치 갱신
			cnt += tempCnt; // 확산 시간 갱신
		}

		// 모든 칸이 바이러스로 뒤덮였는지 검사
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					return; // 0인 칸이 있으면 종료
				}
			}
		}

		result[0] = Math.min(result[0], cnt); // 최소 시간 업데이트
	}

	// 조합을 생성하는 백트래킹 함수
	public static void backtrack(List<int[]> virusIdx, int depth, List<int[]> temp, List<List<int[]>> combination) {
		if (depth == 0) {
			combination.add(new ArrayList<>(temp)); // 조합을 결과 리스트에 추가
		} else {
			for (int i = 0; i < virusIdx.size(); i++) {
				List<int[]> subList = new ArrayList<>(virusIdx.subList(i + 1, virusIdx.size()));
				temp.add(virusIdx.get(i));
				backtrack(subList, depth - 1, temp, combination); // 다음 단계로 진행
				temp.remove(temp.size() - 1); // 현재 단계 완료 후 백트래킹
			}
		}
	}

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int m = sc.nextInt();

		int[][] matrix = new int[len][len];
		List<int[]> virusIdx = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				matrix[i][j] = sc.nextInt();
				if (matrix[i][j] == 2) {
					virusIdx.add(new int[] { i, j });
				}
			}
		}

		List<List<int[]>> combination = new ArrayList<>();
		backtrack(virusIdx, m, new ArrayList<>(), combination); // 조합 생성

		int[] result = { Integer.MAX_VALUE };
		for (List<int[]> com : combination) {
			int[][] testMatrix = new int[len][len];
			for (int i = 0; i < len; i++) {
				testMatrix[i] = Arrays.copyOf(matrix[i], len); // 테스트 매트릭스 생성
			}
			Queue<int[]> que = new LinkedList<>(com); // 큐에 시작 지점 추가
			getVirusTime(testMatrix, que, result); // 바이러스 확산 시간 계산
		}

		if (result[0] == Integer.MAX_VALUE) {
			result[0] = -1; // 최소 시간 갱신되지 않았을 경우 -1로 설정
		}
		System.out.println(result[0]);
		sc.close();
	}
}