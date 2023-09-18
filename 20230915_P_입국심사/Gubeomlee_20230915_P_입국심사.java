package Solution;

public class Gubeomlee_20230915_P_입국심사 {
	// 최댓값을 찾는 메서드
	public static long getMax(int n, int[] times) {
		long max = 1;
		while (true) {
			long temp = 0;
			for (int i = 0; i < times.length; i++) {
				temp += max / times[i];
			}
			if (temp < n) {
				max *= 2L;
			} else {
				break;
			}
		}
		return max;
	}

	// 이진탐색을 통해 최적 시간을 찾는 메서드
	public static long solution(int n, int[] times) {
		long result = 0;
		long max = getMax(n, times);
		long min = max / 2;

		while (min <= max) {
			long mid = (min + max) / 2;
			long temp = 0;
			for (int i = 0; i < times.length; i++) {
				temp += mid / times[i];
			}

			if (temp >= n) {
				max = mid - 1;
				result = mid;
			} else {
				min = mid + 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 6;
		int[] times = { 7, 10 };
		System.out.println(solution(n, times));
	}
}