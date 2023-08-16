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

		for(int i = 0 ; i < strLen; i++){
			stack.push(strArr[i]);
			int idx = bumbStrArrLen-1;
			while(!stack.isEmpty() && stack.peek() == bumbStrArr[idx]) {
				stack.pop();
				idx--;
				if(idx == -1) {
					break;
				}
			}
			if(idx != -1) {
				for(int j = idx + 1; j < bumbStrArrLen; j++) {
					stack.push(bumbStrArr[j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
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
