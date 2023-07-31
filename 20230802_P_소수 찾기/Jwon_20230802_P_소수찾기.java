package programmers;

import java.util.HashSet;
import java.util.Set;

public class Jwon_20230802_P_소수찾기 {
	// 테스트를 하기 위해선 main 메서드가 필요 했고
	// 그에 따른 solution 메서드와 다른 메서드들이 static이어야 한다.
	// 따라서 전역 변수들도 static이어야만 static 메서드들 안에 들어가므로 모두 static으로 선언해준다.
	
	// numbers안에 각 숫자들을 받을 배열
	static String[] arr;
	
	// 완전탐색으로 숫자들을 찾을 때 
	// 각 수들이 이미 그 만들어진 숫자에 포함되었는 지 확인하기 위한 
	// check(boolean) 배열
	static boolean[] chk;
	
	// 완성된 소수를 담아놓을 바구니 같은 set (중복이 되는 것을 방지하기 위해 set에 넣었다.)
	static Set<Integer> set = new HashSet<>();
	
	public static int solution(String numbers) {
		// 매개변수로 받아온 숫자의 수만큼 배열들을 초기화한다.
		arr = new String[numbers.length()];
		chk = new boolean[numbers.length()];
		for(int i = 0 ; i < arr.length; i++) {
			// 받아온 숫자들을 쪼개서 배열에 넣는다.
			arr[i] = Character.toString(numbers.charAt(i));
		}
		
		// 숫자를 만드는 makeNum 함수 실행.
		// 매개변수는 계속 새로운 수가 추가되어야 함으로 편하게 String으로 하였다.
		// 따라서 첫 매개변수는 빈 문자열을 넣어주었다.
		makeNum("");
		
		// 소수들의 갯수가 곧 정답.
		return set.size();
	}

	private static void makeNum(String nums) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < arr.length ; i++) {
			// 만약 현재의 수가 이미 들어간 수라면 다음 수로 넘어간다.
			// # for 문에서 continue를 활용하면 밑에 것들을 무시하고 넘어간다. #
			if(chk[i])
				continue;
			
			// 다음으로 넘어갈 makeNum에서 현재 이 수는 활용이 되었다는 것을 알리기 위한 check
			chk[i] = true;
			// 전에 만들어진 수에 현재 수를 붙혀준다.
			nums += arr[i];
			// 소수 찾기 메서드를 실행.
			findPrime(Integer.parseInt(nums));
			// 현재 만들어진 수를 가지고 남은 수가 있다면 새로운 수를 계속 붙혀서 만든다.
			makeNum(nums);
			// 현재 수가 아닌 다른 수를 전에 받아온 nums에 붙히기 위해 방금 붙힌 현재 수를 제거해준다.
			nums = nums.substring(0, nums.length() - 1);
			// 현재 수는 제거되었고 따라서 활용된 것이 아니므로 다시 false 해준다.
			chk[i] = false;
		}
	}

	private static void findPrime(int num) {
		// TODO Auto-generated method stub
		// 이미 소수이거나 1이라면 리턴.
		if(num < 2 || set.contains(num)) {
			return ;
		}
		// 2라면 소수에 넣기.
		if(num == 2) {
			set.add(num);
			return ;
		}
		// 소수 찾기.
		for(int i = 2 ; i <= Math.sqrt(num) ; i++) {
			// 만약 나 자신과 1이 아닌 다른 수로 나눠진다면 이 수는 소수가 아니다.
			if(num % i == 0) {
				return ;
			}
		}
		// 소수를 찾았으니 소수 주머니에 넣기.
		set.add(num);
	}
	
	public static void main(String[] args) {
		// 이곳은 테스트 케이스 하는 메인 메서드.
		System.out.println("answer : " + solution("17"));
		System.out.println("answer : " + solution("011"));
	}
}
