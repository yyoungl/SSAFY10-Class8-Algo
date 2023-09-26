package Baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class point_s {
	int x, y;

	public point_s(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// contain 메서드 활용을 위해 equals 재정의
	@Override
	public boolean equals(Object obj) {
		if (((point_s) obj).x == this.x && ((point_s) obj).y == this.y)
			return true;
		return false;
	}
}

public class 뱀 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		int k = sc.nextInt();
		for (int i = 0; i < k; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a - 1][b - 1] = 1;
		}
		int l = sc.nextInt();
		int[] Time = new int[l];
		char[] Dir = new char[l];
		for (int i = 0; i < l; i++) {
			Time[i] = sc.nextInt();
			Dir[i] = sc.next().charAt(0);
		}
		Deque<point_s> dq = new ArrayDeque<point_s>();
		int t = 0;
		dq.add(new point_s(0, 0));
		int dir = 0;
		int idx = 0;
		while (true) {
			// 시간증가
			t++;
			// 다음 위치 nx ny에 저장
			int nx = dq.peek().x + dx[dir];
			int ny = dq.peek().y + dy[dir];
			// 중단조건 확인
			if (!(nx >= 0 && nx < n && ny >= 0 && ny < n) || dq.contains(new point_s(nx, ny)))
				break;
			// 뱀의 머리 위치 추가
			dq.addFirst(new point_s(nx, ny));
			// 뱀의 머리 위치에 사과가 없으면 뱀 꼬리 삭제
			if (arr[nx][ny] == 0)
				dq.removeLast();
			// 사과가 있으면 꼬리를 삭제하지 않고, 사과를 없애준다.
			else
				arr[nx][ny] = 0;
			// 문제에서 입력받은 시간이 되면 입력값에 따라 방향 전환
			if (idx < l && t == Time[idx]) {
				if (Dir[idx] == 'D') {
					dir++;
				} else {
					dir--;
				}
				dir = (dir + 4) % 4;
				idx++;
			}

		}
		System.out.println(t);

	}
}
