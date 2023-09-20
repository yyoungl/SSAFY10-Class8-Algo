package Algorithm.algorithm.baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class Jwon_20230921_B_2812_크게만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String str = sc.next();
		Stack<Character> stack = new Stack<>();
		// 문자열 폭발 처럼 새로새로 넣어주면서 더 큰 값이 나오면 최대한 제거해준다.
		// 큰 값이 최대한 앞 자릿수로 와야 하게 한다.
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() < str.charAt(i) && k > 0) {
				stack.pop();
				k--;
			}
			stack.push(str.charAt(i));
		}
		while(k > 0) {
			stack.pop();
			k--;
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();
		System.out.println(sb.toString());
	}
}
