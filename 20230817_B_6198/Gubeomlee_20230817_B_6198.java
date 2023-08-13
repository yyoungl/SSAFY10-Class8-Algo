package Solution;

import java.util.Scanner;
import java.util.Stack;

public class Gubeomlee_20230817_B_6198 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		long[] arr = new long[len];
		for (int i = 0; i < len; i++) {
			arr[i] = sc.nextLong();
		}

		Stack<Long> stack = new Stack<>();
		stack.add(arr[0]);
		long result = 0;
		for (int i = 1; i < len; i++) {
			while (!stack.isEmpty() && arr[i] >= stack.peek()) { // 스택 건물의 탑보다 현재 건물의 높이가 높다면
				stack.pop(); // 스택의 탑이 현재 건물의 높이보다 높아질 때까지 스택을 제거한다.
			}
			if (!stack.isEmpty() && arr[i] < stack.peek()) { // 스택 건물의 탑보다 현재 건물의 높이가 낮다면
				result += stack.size(); // 스택 사이즈 만큼 옥상을 볼 수 있다.
			}
			stack.add(arr[i]);
		}
		System.out.println(result);
		sc.close();
	}
}