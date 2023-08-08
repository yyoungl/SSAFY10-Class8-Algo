import java.util.Stack;
import java.util.Scanner;

public class yyoungl_20230810_B_1874 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> stack = new Stack<>();
		int last = 0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		stack.push(0);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			if (stack.peek() < num) {
				for (int j=last+1; j<=num; j++) {
					stack.push(j);
					sb.append("+\n");
					last+=1;
				}
			}

			System.out.println();
			while (stack.peek() > num) {
				stack.pop();
				sb.append("-\n");
			}
			if (stack.pop() != num) {
					System.out.println("NO");
					return;
			} else {
				sb.append("-\n");
			}	
		}
		System.out.println(sb.toString());

	}

}
