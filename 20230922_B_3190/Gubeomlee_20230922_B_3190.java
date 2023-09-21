package Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Gubeomlee_20230922_B_3190 {
	// 게임이 종료되는 조건을 확인하는 메서드
	public static boolean isValid(Deque<int[]> deque, int len, int row, int col) {
		if (row < 1 || row > len || col < 1 || col > len) {
			return false;
		}
		for (int[] body : deque) {
			if (body[0] == row && body[1] == col) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int appleCnt = sc.nextInt();

		List<int[]> apple = new ArrayList<>();
		for (int i = 0; i < appleCnt; i++) {
			apple.add(new int[] { sc.nextInt(), sc.nextInt() });
		}

		int dirCnt = sc.nextInt();
		Map<Integer, String> dirMap = new HashMap<>();
		for (int i = 0; i < dirCnt; i++) {
			dirMap.put(sc.nextInt(), sc.next());
		}

		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[] { 1, 1 });

		int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int idx = 0;

		int result = 0;

		while (true) {
			result++; // 시간 증가
			int row = deque.peekFirst()[0] + dir[idx][0]; // 다음 행 좌표
			int col = deque.peekFirst()[1] + dir[idx][1]; // 다음 열 좌표

			if (!isValid(deque, len, row, col)) { // 게임이 종료되는지 확인
				break;
			}

			deque.addFirst(new int[] { row, col }); // 머리 위치 추가

			// 사과인지 확인
			boolean isApple = false;
			for (int i = 0; i < apple.size(); i++) {
				if (apple.get(i)[0] == row && apple.get(i)[1] == col) {
					isApple = true;
					apple.remove(i);
					break;
				}
			}

			if (!isApple) { // 사과가 아니라면 꼬리 제거
				deque.pollLast();
			}

			if (dirMap.containsKey(result)) { // 방향전환 확인 후 방향 인덱스 갱신
				if (dirMap.get(result).equals("D")) {
					idx = (idx + 1) % 4;
				} else {
					idx = (idx + 3) % 4;
				}
			}
		}

		System.out.println(result);
		sc.close();
	}
}