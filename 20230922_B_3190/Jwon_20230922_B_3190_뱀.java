package Algorithm.algorithm.baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Jwon_20230922_B_3190_뱀 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		boolean[][] apples = new boolean[n + 1][n + 1];
		// 아니 대게 세로가 y지 않나..?
		// 이거 떔에 진짜 얼마나 헤맸는 지 아냐고
		for (int i = 0; i < k; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			apples[y][x] = true;
		}
		int l = sc.nextInt();
		// 방향과 시간의 배열들
		char[] directionArr = new char[l];
		int[] timeArr = new int[l];
		for (int i = 0; i < l; i++) {
			int length = sc.nextInt();
			char dir = sc.next().charAt(0);
			timeArr[i] = length;
			directionArr[i] = dir;
		}

		// 우 하 좌 상,
		// 0 1 2 3
		int[] dirX = { 1, 0, -1, 0 };
		int[] dirY = { 0, 1, 0, -1 };

		// first가 대가리, last가 꼬리로 생각을 하고 문제를 풀었다.
		Deque<Node> deque = new ArrayDeque<>();
		deque.offer(new Node(1, 1));
		int time = 1;
		int direction = 0;
		int index = 0;
		while (true) {
			Node node = deque.peekFirst();
			int tempX = dirX[direction] + node.x;
			int tempY = dirY[direction] + node.y;
			Node chkNode = new Node(tempY, tempX);
			// 뱀이 자신 몸에 부딪히거나, 맵 밖으로 나가면 게임 끝.
			if (tempX <= 0 || tempX > n || tempY <= 0 || tempY > n || deque.contains(chkNode)) {
				System.out.println(time);
				System.exit(0);
			}
			// 이동가능하면 대가리 늘리고.
			deque.addFirst(chkNode);
			// 사과 있으면 먹고
			if (apples[tempY][tempX]) {
				apples[tempY][tempX] = false;
			}
			// 없으면 꼬리 자르고
			else {
				deque.pollLast();
			}

			// 시간이 다되면 방향 바꾸기.
			if (index < l && time == timeArr[index]) {
				if (directionArr[index] == 'L') {
					direction -= 1;
				} else {
					direction += 1;
				}
				direction += 4;
				direction %= 4;
				index++;
			}

			// 모든 행동이 끝나고 다음으로 넘어갈 때 time++..
			time++;
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		// 자신 몸에 부딪혔을 때 contains를 이용하기 위한 equals
		// contains는 equals를 활용해서 확인한다.
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Node) {
				Node temp = (Node) obj;
				if (temp.y == this.y && temp.x == this.x) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}

	}
}
