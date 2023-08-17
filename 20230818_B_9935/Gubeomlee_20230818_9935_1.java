package algo;

import java.util.Scanner;
import java.util.Stack;

public class Gubeomlee_20230818_9935_1 {
	// 문자열 폭발 메서드
	public static String explosion(String str, String test) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			stack.add(str.charAt(i));
			// 현재 문자를 스택에 추가한다.
			if (str.charAt(i) == test.charAt(test.length() - 1)) {
				// 현재 문자가 폭발 문자열의 마지막 문자와 일치하는 경우
				Stack<Character> temp = new Stack<>();
				for (int j = test.length() - 1; j >= 0; j--) {
					// 스택이 비어있거나, 일치하지 않는 문자를 만나면 임시스택의 내용을 스택에 추가한다.
					if (stack.isEmpty() || stack.peek() != test.charAt(j)) {
						while (!temp.isEmpty()) {
							stack.add(temp.pop());
						}
						break;
					}
					// 일치하는 문자를 임시스택에 추가한다.
					temp.add(stack.pop());
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		if (sb.length() == 0) {
			return "FRULA"; // 남은 문자가 없는 경우
		} else {
			return sb.reverse().toString();
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String test = sc.next();
		String result = explosion(str, test);
		System.out.println(result);
		sc.close();
	}
}