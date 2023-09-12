import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
		long min = 1;
		long answer = 0;
		long max = times[times.length - 1] * (long)n;
		while (min <= max) {
			long sum = 0;
			long mid = (min + max) / 2;
			for (int i = 0; i < times.length; i++) {
				sum += mid / times[i];
			}
			if (sum < n) {
				min = mid + 1;
			} else {
				max = mid - 1;
                answer =  mid;
			}
		}
		
		return answer;
    }
}