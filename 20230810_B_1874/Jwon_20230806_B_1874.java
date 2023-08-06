package backjoon;

import java.util.Scanner;
import java.util.Stack;

public class Jwon_20230806_B_1874 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		int number = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < num ; i++) {
			int n = sc.nextInt();
			while(stack.peek() < n) {
				stack.push(++number);
				sb.append("+\n");
			}
			
			while(true){
				int temp = stack.pop();
				sb.append("-\n");
				if(temp == n) {
					break;
				}
				if(stack.isEmpty()) {
					System.out.println("NO");
					System.exit(0);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
