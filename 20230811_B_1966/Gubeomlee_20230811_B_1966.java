package algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Gubeomlee_20230811_B_1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int len = sc.nextInt();
			int idx = sc.nextInt();

			Queue<Integer> que = new LinkedList<>();
			for (int i = 0; i < len; i++) {
				que.offer(sc.nextInt());
			}

			int result = 1;
			while (true) {
				int maxNum = Integer.MIN_VALUE;
				for (int num : que) {
					maxNum = Math.max(maxNum, num);
				}

				if (idx == 0) { // 큐의 맨 앞 요소가 목표값인 경우
					if (que.peek() == maxNum) { // 해당 값이 최댓값인 경우
						break;
					} else { // 해당 값이 최댓값이 아닌 경우
						que.offer(que.poll());
						idx = que.size() - 1; // 목표값 인덱스 갱신
					}
				} else { // 큐의 맨 앞 요소가 목표값이 아닌 경우
					idx--;
					if (que.peek() == maxNum) { // 해당 값이 최댓값인 경우
						que.poll();
						result++;
					} else { // 해당 값이 최댓값이 아닌 경우
						que.offer(que.poll());
					}
				}
			}
			System.out.println(result);
		}
		sc.close();
	}
}
