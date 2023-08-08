package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Gubeomlee_20230810_B_1874 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = sc.nextInt();
		}

		Stack<Integer> stack = new Stack<>();
		List<String> result = new ArrayList<>();

		int idx = 0;
		for (int i = 1; i <= len; i++) {
			stack.push(i);
			result.add("+");
			while (!stack.isEmpty() && stack.peek() == arr[idx]) {
				// 스택 상단의 값이 숫자 배열의 현재값과 같은 경우 반복한다.
				idx++;
				stack.pop();
				result.add("-");
			}
		}

		if (stack.isEmpty()) {
			for (String str : result) {
				System.out.println(str);
			}
		} else {
			// 스택이 비어있지 않다. 숫자배열을 스택으로 만들 수 없는 경우다.
			System.out.println("NO");
		}
		sc.close();
	}
}