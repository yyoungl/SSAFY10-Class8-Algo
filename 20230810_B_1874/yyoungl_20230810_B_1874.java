import java.util.Stack;
import java.util.Scanner;

public class yyoungl_20230810_B_1874 {

	public static void main(String[] args) {
		// 스택이라는 클래스를 처음 써봅니다...
		// 처음엔 수열에 사용된 숫자들이 무엇인지 다 체크해야 한다고 생각했는데 아니더라고요...
		Stack<Integer> stack = new Stack<>();
		// 스택에 숫자를 오름차순으로 넣어야 하기 때문에 마지막 넣은 숫자를 담을 변수
		int last = 0;
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 빈 stack에 처음 0을 넣어 준다 #주원아고마워
		stack.push(0);
		int N = sc.nextInt();
		// N 번의 command
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			// stack 가장 위에 있는 값이 입력받은 숫자보다 작으면
			if (stack.peek() < num) {
				for (int j=last+1; j<=num; j++) {
					// 하나씩 넣어 주고
					stack.push(j);
					// 문자열에 + 추가
					sb.append("+\n");
					// 마지막 숫자 +1
					last+=1;
				}
			}
			// stack 가장 위에 있는 값이 num 보다 크다면
			while (stack.peek() > num) {
				// n이 나오기 직전까지 빼준다
				stack.pop();
				sb.append("-\n");
			}
			// 이렇게 체크했는데 가장 위에 num이 없다면 불가능하니까
			if (stack.pop() != num) {
					// NO. 표시
					System.out.println("NO");
					return;
			} else {
				sb.append("-\n");
			}	
		}
		// NO 가 출력되지 않고 반복문을 잘 마쳤다면 만들어놓은 문자열을 출력합니다...
		System.out.println(sb.toString());
	}

}
