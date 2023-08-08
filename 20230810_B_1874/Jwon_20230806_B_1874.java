package backjoon;

import java.util.Scanner;
import java.util.Stack;

public class Jwon_20230806_B_1874 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		// 수들을 담아놓을 스택 생성.
		Stack<Integer> stack = new Stack<>();
		// 어처피 수들은 1부터 시작하고 stack.pop이나 peek을 할 때 stack이 비어있는 지 계속 확인해주는 것이 귀찮아서
		// 처음에 그냥 0을 넣어놓고 시작하였다.
		stack.push(0);
		
		// 스택에 계속 들어갈 number
		int number = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < num ; i++) {
			int n = sc.nextInt();
			// 스택에 가장 위에 있는 숫자가 찾는 숫자보다 작으면 number에 +1 해서 계속 넣는다.
			// number가 num 보다 클 수도 있는데 어처피 그러면 NO가 나오게 될 것이므로 따로 생각해주지 않는다.
			// number가 현재 찾고 있는 n보다 커질 수가 있는데 어처피 그러면 NO가 나오게 될 것이다.
			while(stack.peek() < n) {
				stack.push(++number);
				sb.append("+\n");
			}
			
			
			// stack.peek()이 n보다 크거나 같다면 하나하나 뽑아서 확인해본다.
			while(true){
				int temp = stack.pop();
				sb.append("-\n");
				
				// 같은 게 나온다면 더 이상 뽑지 않고 다음 숫자를 확인한다.
				if(temp == n) {
					break;
				}
				// 계속 뽑았는데도 같은 게 안나온다면 결국 스택은 비어있을 것이므로 
				// NO를 출력하고 시스템 종료.
				if(stack.isEmpty()) {
					System.out.println("NO");
					System.exit(0);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
