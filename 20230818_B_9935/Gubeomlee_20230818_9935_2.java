package algo;

import java.util.Scanner;
import java.util.Stack;

public class Gubeomlee_20230818_9935_2 {
	public static void explosion(String str, String test, boolean[] checked, Stack<Integer> stack, int i) {
		int temp = 0; // 현재 인덱스부터 일치하는 문자열 개수 저장
		int idx = i; // str 탐색 인덱스
		for (int j = 0; j < test.length(); j++) {
			while (idx < str.length() && checked[idx]) {
				idx++; // 이미 제거한 인덱스라면 다음 인덱스로 이동
			}
			if (idx >= str.length() || str.charAt(idx) != test.charAt(j)) {
				return;
			}
			idx++;
		}

		idx = i; // 탐색 인덱스 초기화
		for (int j = 0; j < test.length(); j++) {
			while (checked[idx]) {
				idx++;
			}
			checked[idx] = true; // 문자열을 제거한다.
			idx++;
		}
		stack.add(i - test.length() + 1); // 일치하는 부분의 시작 인덱스를 스택에 저장한다.
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String test = sc.next();

		boolean[] checked = new boolean[str.length()]; // 제거한 문자열을 표시한다.
		Stack<Integer> stack = new Stack<>(); // 일치하는 부분의 시작 인덱스를 저장하는 스택
		for (int i = 0; i < str.length() - test.length() + 1; i++) {
			explosion(str, test, checked, stack, i);
		}

		while (!stack.isEmpty()) {
			int testIdx = stack.pop(); // 스택에서 일치하는 부분의 시작 인덱스를 꺼내온다.
			for (int i = testIdx; i < testIdx + test.length(); i++) {
				if (i >= 0) {
					explosion(str, test, checked, stack, i);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (!checked[i]) {
				sb.append(str.charAt(i));
			}
		}

		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
		sc.close();
	}
}