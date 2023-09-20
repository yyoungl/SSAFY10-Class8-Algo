package _Baekjune;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class 뒤집기3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		Deque<Character> dq = new ArrayDeque<Character>();
		dq.add(str.charAt(0));
		for (int i = 1; i < str.length(); i++) {
			if (dq.peekFirst() >= str.charAt(i)) {
				dq.addFirst(str.charAt(i));
			} else
				dq.addLast(str.charAt(i));
		}
		for (char x : dq) {
			System.out.print(x);
		}

	}
}
