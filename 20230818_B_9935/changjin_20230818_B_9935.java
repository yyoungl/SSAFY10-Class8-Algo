// 문자열 폭발
//전체 문자열의 길이가 최대 백만이고, 폭발 문자열의 크기가 최대 36이다
//예상되는 문제점. 
//1. contain이나 replace와 같은 함수를 이용해 새로운 str하여 폭발 문자열을 제거하는 방법. -> 길이 백만의 문자열로 인한 메모리 초과
//2. 폭발 문자열을 제거 한 후 정답 값 출력시 bw, sb를 사용하지 않고 sysout을 사용시 시간초과 문제 발생 -> sysout은 매우 느려 br이나 sb보다 20배 가령의 실행 시간 차이를 보일 수 있음.
package _Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _9935 {

	public static void main(String[] args) throws IOException {
		
		// 문자열의 길이가 최대 백만이므로 효율적인 메모리와 빠른 실행속도를 보여주는 br, sb 생성.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
	    // 문자열을 저장할 main stack과 폭발 문자열의 끝이 main stack의 peek과 일치할때 폭발 문자열인지 확인하는동안 임시로 값을 저장할 sub stack 정의 
		Stack<Character> main=new Stack<>();
		Stack<Character> sub=new Stack<>();
		
		String str=br.readLine();
		String target=br.readLine();
		
		for(int i=0;i<str.length();i++) {
			// 메인 스택에 추가.
			main.add(str.charAt(i));
			// 메인 스택에 추가하는 경우 : 메인의 peek와 폭발 문자열의 마지막 문자가 일치하지 않는 경우
			if(main.peek()!=target.charAt(target.length()-1)) continue;
			else {
				// 메인 스택의 peek와 폭발 문자열의 마지막 문자가 일치하는 경우.
				// 폭발 문자열의 끝 문자열부터 맨 앞 문자열까지 순회하며 메인 문자열과 비교하며 같으면 메인문자열을 pop한 후 sub 스택에 담아놓는다.  
				for(int j=target.length()-1;j>=0;j--) {
					// 폭발 문자열의 끝 부터 맨 앞 문자열 까지 순회를 마치기 전에 메인 스택이 비거나, 메인의 peek과 일치하지 않는 경우 
					// 폭발 문자열을 보유하고 있지 않는 경우이므로 그동안 담아놨던 sub 스택의 원소들을 메인에 다시 넣어준다. 
					if(main.isEmpty()||main.peek()!=target.charAt(j)) {
						while(!sub.isEmpty()) {
							main.add(sub.pop());
						}
					}
					// 메인 문자열이 비지 않았고 peek가 폭발 문자열의 j번째와 일치하면 메인 스택을 pop후 sub 스택에 저장한다.
					else sub.add(main.pop());
					
				}
				// for 문을 다돌면 메인 스택은 폭발 문자열을 가지고 있던 것이므로 sub 스택의 원소들을 메인 스택으로 반환하지 않고 서브 스택을 비운다.
				sub.clear();
			}
		}
		// 메인 스택이 비었으면 FRULA 반환
		if(main.size()==0) System.out.println("FRULA");
		else {
			// 주어진 문자열의 최대크기가 백만이므로 이를 하나하나 sysout으로 출력시 시간초과가 발생. StringBuilder에 모아서 한번에 출력 이는 그렇지 않는것고 20배 이상의 차이 보임.
			for(char c:main) {
				sb.append(c);
			}
			System.out.println(sb);
		}
		
	}
}
