package Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gubeomlee_20230802_P_소수찾기_2 {
	// 소수인지 확인하는 메서드
	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		// 소수확인할 때 제곱근 까지만 확인한다.
		for (int i = 2; i < Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	// 숫자 조합을 만드는 백트래킹 메서드
	public static void backtrack(List<Integer> arr, int depth, int value, Set<Integer> set) {
		if (isPrime(value)) {
			set.add(value);
		}
		if (depth != 0) {
			for (int i = 0; i < arr.size(); i++) {
				// 현재 숫자를 제외한 나머지 숫자들로 재귀 호출하여 다음 숫자 조합을 만든다.
				List<Integer> subList = new ArrayList<>(arr.subList(0, i));
				subList.addAll(arr.subList(i + 1, arr.size()));
				backtrack(subList, depth - 1, value * 10 + arr.get(i), set);
			}
		}
	}

	public int solution(String numbers) {
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < numbers.length(); i++) {
			arr.add(numbers.charAt(i) - '0');
		}
		Set<Integer> set = new HashSet<Integer>();
		// 백트래킹을 통해 숫자 조합을 만들고 소수 판별 결과를 set에 저장한다.
		backtrack(arr, numbers.length(), 0, set);
		return set.size();
	}
}
