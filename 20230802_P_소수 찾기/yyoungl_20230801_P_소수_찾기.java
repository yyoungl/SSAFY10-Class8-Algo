package algostudy;

import java.util.ArrayList;

public class yyoungl_20230801_P_소수_찾기 {
	// arr -> 나올 수 있는 순열을 담을 배열. 개수가 몇개인지 모르기 때문에 arraylist로 한다!
	static ArrayList<Integer> arr = new ArrayList<>();
	// 방문 여부를 확인할 boolean 수열. numbers 인풋이 1 자리 이상 7 자리 이하 숫자이기 때문에 7칸으로 초기화
	static boolean check[] = new boolean[7];

	public int solution(String numbers) {
		int answer = 0;


		// 숫자를 1개 사용한 경우 ~ 모두 사용한 경우까지 만들 수 있는 숫자열 모두!!
		for (int i = 0; i < numbers.length(); i++) {
			dfs(numbers, "", i + 1); // 처음에 빈 문자열로 시
		}

		for (int i = 0; i < arr.size(); i++) {
			if (prime(arr.get(i)))
				answer++;
		}

		return answer;
	}

	// numList에서 나올 수 있는 순열을 구하는 dfs
	static void dfs(String str, String temp, int m) {
		if (temp.length() == m) {
			int num = Integer.parseInt(temp);
			if (!arr.contains(num))
				arr.add(num);
		}

		for (int i = 0; i < str.length(); i++) {
			// 숫자가 사용되었는지, 사용되지 않았는지 확인하며 중복되지 않은 숫자로 만드는 숫자열 만들기
			if (!check[i]) {
				// 인덱스 i 에 해당하는 숫자를 사용하지 않았다면
				check[i] = true;
				temp += str.charAt(i);
				// temp 에 숫자 i 이어붙이기
				// 재귀 호출 코드, 
				dfs(str, temp, m);
				// str에 담긴 숫자들을 다시 temp ex) "1"에 이어붙이고, 숫자의 길이가 m이 될 때까지 반복
				check[i] = false;
				temp = temp.substring(0, temp.length() - 1);
			}
		}
	}

	// 소수임을 확인하는 코드
	static boolean prime(int n) {
		if (n < 2) {
			return false;
		} else {
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0)
					return false;
			}
			return true;
		}
	}
}
