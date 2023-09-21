package Solution;

import java.util.Scanner;
import java.util.Stack;

public class Gubeomlee_20230921_B_2812 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int cnt = sc.nextInt();
		String input = sc.next();

		Stack<Integer> stack = new Stack<>();
		// 스택의 상단 값을 최댓값으로 유지하며 스택을 채운다.
		for (int i = 0; i < input.length(); i++) {
			int num = input.charAt(i) - '0';
			while (cnt > 0 && !stack.isEmpty() && stack.peek() < num) {
				stack.pop();
				cnt--;
			}
			stack.add(num);
		}
		// 제거해야 할 문자가 남았다면 스택상단부터 제거한다.
		while (cnt > 0) {
			stack.pop();
			cnt--;
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}

		System.out.println(sb.toString());
		sc.close();
	}
}