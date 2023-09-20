package _Baekjune;

import java.util.Scanner;
import java.util.Stack;

public class 크게만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String str = sc.next();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (stack.isEmpty() || stack.peek() >= str.charAt(i)) {
				stack.push(str.charAt(i));
			} else {
				while (!stack.isEmpty() && stack.peek() < str.charAt(i)) {
					if (stack.size() + str.length() - i <= n - k)
						break;
					stack.pop();
				}
				stack.push(str.charAt(i));
			}
		}
		while (stack.size() != n - k) {
			stack.pop();
		}
		for (char x : stack) {
			System.out.print(x);
		}
	}
}
