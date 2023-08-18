import java.util.Scanner;
import java.util.Stack;

public class B9935 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 기존 글자를 담을 스택
		Stack<Character> originStr = new Stack<>();
		// 꺼낸 글자를 담을 스택 
		Stack<Character> tempStr = new Stack<>();
		String inputS = sc.next();

		// 처음 풀이!! 글자를 하나씩 char 로 받을까 했는데 나중에 String으로 바꿔줘야 했기 때문에 그냥 String 스택과 배열로...

		// char 에 넣어주기
		for (int i = 0; i < inputS.length(); i++) {
			originStr.push(inputS.charAt(i));
		}

		// 터뜨릴 글자
		String pung = sc.next();

		// 문자열을 뒤에서부터 pung의 길이만큼 확인하고, pung과 같다면 없애고 같지 않다면 앞으로 한 칸 이동해서 확인을 반복하는 방식

		// originStr 스택을 다 확인할 때까지
		while (!originStr.isEmpty()) {
			// check... 이게 뭐냐면 pung의 길이만큼 뒤에서부터 꺼내서 이어붙일 문자열. 이걸로 확인할 것
			String check = "";
			
			// 만약에 확인할 남은 문자열이 "펑"보다 짧다면 그냥 빼준다
			if (originStr.size() < pung.length()) {
				tempStr.push(originStr.pop());
				continue;
			}
		
			// originStr의 뒤글자부터 나오는 거니까 check 앞에 붙여주기 (펑 길이만큼)
			for (int i = 0; i < pung.length(); i++) {
				check = originStr.pop() + check;
			}

			// 펑과 check가 같다면
			if (check.equals(pung)) {
				// CC44 -> 펑 -> C4 같은 경우가 생길 수도 있으므로 다시 넣어주기
				while (!tempStr.isEmpty())
					originStr.push(tempStr.pop());
				continue;
			} else {
				// 같지 않다면 check의 가장 마지막 글자를 제외하고 originStr에 넣어준다
				for (int i = 0; i < check.length() - 1; i++)
					originStr.push(check.charAt(i));
				// 그리고 확인한 마지막 글자를 tempStr에 넣어준당
				tempStr.push(check.charAt(pung.length() - 1));
			}

		}
		// 문자열이 다 터졌다면
		if (tempStr.isEmpty()) System.out.println("FRULA"); 
		else {
			// stack 에 제일 마지막 글자부터 들어가 있기 때문에 pop을 이용해서 첫 글자부터 출력합니다
			while (!tempStr.isEmpty())
				System.out.print(tempStr.pop());
		}

	}

}
