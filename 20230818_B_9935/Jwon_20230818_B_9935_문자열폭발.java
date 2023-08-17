package Algorithm.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Jwon_20230818_B_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] strArr = br.readLine().toCharArray();
		char[] bumbStrArr = br.readLine().toCharArray();
		int bumbStrArrLen = bumbStrArr.length;
		int strLen = strArr.length;
		Stack<Character> stack = new Stack<>();

		// 문자열의 하나하나를 들고와서 매 번 확인을 해주는 방법을 사용했다.
		for(int i = 0 ; i < strLen; i++){
			// 우선 문자열을 스택에 넣는다.
			stack.push(strArr[i]);
			int idx = bumbStrArrLen-1;
			// 만약 스택에 가장 높은 곳에 있는 문자가 폭발할 문자열의 마지막과 같다면
			// Index를 하나하나 줄여가면서 계속 스택에 가장 높은 문자와 폭발할 문자열의 index위치한 문자와 같은 지 확인한다.
			// 만약 index가 -1이 된다면 모두 같다는 의미이므로 다음 다음 문자를 확인한다.
			while(!stack.isEmpty() && stack.peek() == bumbStrArr[idx]) {
				stack.pop();
				idx--;
				if(idx == -1) {
					break;
				}
			}
			// 그러나  index가 -1이 아니라면 폭발할 문자열과 같은 문자열을 가진 것이 아니기 때문에
			if(idx != -1) {
				// 제거된 문자들을 다시 넣어주는 작업이 필요하다.
				for(int j = idx + 1; j < bumbStrArrLen; j++) {
					stack.push(bumbStrArr[j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		// 여기서 시간초과가 났다.
		// 처음엔 sb.insert(0,stack.pop());을 했는데 
		// 어떠한 값을 맨 앞에 넣어주면 원래 있던 값들이 모두 뒤로 밀려야하기 때문에 시간초과가 났다.
		// 따라서 값을 넣어주고 뒤집었다.
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();
		String result = sb.toString();
		if(result.length() == 0) {
			result = "FRULA";
		}
		System.out.println(result);
	}
}
