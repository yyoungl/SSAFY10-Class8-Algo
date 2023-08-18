package algo;

import java.util.Stack;

public class Gubeomlee_20230821_조이스틱 {
	// 조이스틱 좌우 조작 횟수 계산하는 메서드
	public static void getVisitedCnt(String name, int idx, int[] result) {
		Stack<Character> stack = new Stack<>();
		// 반대 방향으로 이동한 위치가 idx다.
		// 시작점부터 idx 이전까지 스택에 추가한다.
		for (int i = 0; i < idx; i++) {
			stack.add(name.charAt(i));
		}
		// idx 이전부터 시작점까지 스택에 추가한다.
		for (int i = idx - 2; i >= 0; i--) {
			stack.add(name.charAt(i));
		}
		// 문자열 끝부터 idx까지 스택에 추가한다.
		for (int i = name.length() - 1; i >= idx; i--) {
			stack.add(name.charAt(i));
		}
		// 연속되는 'A' 제거한다.
		while (stack.peek() == 'A') {
			stack.pop();
		}
		// 스택의 길이가 좌우 이동횟수가 된다.
		int cnt = stack.size() - 1;
		// idx가 0인 경우 이 값은 스택의 top에 위치한다.
		// idx가 0이고 그 값이 'A'인 경우 스택에서 제거되는 문제가 있어 좌우 이동 횟수를 보정해야 한다.
		if (idx == 0 && name.charAt(0) == 'A') {
			cnt++;
		}
		// 좌우 이동 횟수를 더 작은 값으로 갱신한다.
		result[0] = Math.min(result[0], cnt);
	}

	// 조이스틱 상하 조작 횟수 계산하는 메서드
	public static int joystick(String name) {
		int result = 0;
		for (int i = 0; i < name.length(); i++) {
			result += Math.min(name.charAt(i) - 'A', 26 - name.charAt(i) + 'A');
		}
		return result;
	}

	public static int solution(String name) {
		int[] visitedCnt = { name.length() };
		int joystickCnt = joystick(name);
		if (joystickCnt == 0) { // 조이스틱 상하 조작 횟수가 0이라면
			visitedCnt[0] = 0; // 조이스틱 좌우 조작 횟수도 0이다.
		} else {
			// 첫 이동이 왼쪽인 경우에 사용할 문자열을 생성한다.
			StringBuilder sb = new StringBuilder();
			sb.append(name.charAt(0));
			for (int i = name.length() - 1; i >= 1; i--) {
				sb.append(name.charAt(i));
			}
			// 첫 이동이 오른쪽일 때 좌우 조작 횟수를 계산한다.
			for (int i = 0; i < name.length(); i++) {
				// 시작 위치 또는 현재 위치가 'A'일때 반대 방향으로 이동한다.
				if (i == 0 || name.charAt(i) == 'A') {
					getVisitedCnt(name, i, visitedCnt);
				}
			}
			// 첫 이동이 왼쪽일 때 좌우 조작 횟수를 계산한다.
			for (int i = 0; i < name.length(); i++) {
				if (i == 0 || sb.toString().charAt(i) == 'A') {
					getVisitedCnt(sb.toString(), i, visitedCnt);
				}
			}
		}
		return joystickCnt + visitedCnt[0];
	}

	public static void main(String args[]) {
		String name1 = "JEROEN";
		String name2 = "JAN";
		String name3 = "BBAAAAAAAAAAAAAAAAAB";
		String name4 = "BBAAABAAAAAAAAAAAAAB";
		String name5 = "AAAAAAAAAAAAAAAAAABB";
		String name6 = "AAAAAAAAAAAAAAABAAAB";
		String name7 = "BBAAAAAAAAAAAAAAAAAA";
		String name8 = "ABABABABABABABABABAB";
		String name9 = "B";
		String name10 = "BB";
		String name11 = "A";
		String name12 = "AA";
		System.out.println(solution(name1));
		System.out.println(solution(name2));
		System.out.println(solution(name3));
		System.out.println(solution(name4));
		System.out.println(solution(name5));
		System.out.println(solution(name6));
		System.out.println(solution(name7));
		System.out.println(solution(name8));
		System.out.println(solution(name9));
		System.out.println(solution(name10));
		System.out.println(solution(name11));
		System.out.println(solution(name12));
	}
}