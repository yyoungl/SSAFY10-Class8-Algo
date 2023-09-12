package Algorithm.algorithm.programmers;

import java.util.Arrays;

public class Jwon_20230915_P_입국심사 {
	// 공유기 설치와 같이 정답을 이분탐색으로 찾는다고 생각을 하자.
	// 여기서 문제는 비어있는 심사관에 사람이 꼭 갈 필요는 없다는 것이다.
	// 따라서 가능하더라도 시간을 더 줄여가면서 확인한다.
	public long solution(int n, int[] times) {
		long answer = 0;
		Arrays.sort(times);
		// 가장 오래 걸리는 시간
		long end = (long) n * (long) times[times.length - 1];
		// 가장 오래 짧게 걸리는 시간
		long start = 1;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long count = 0;
			// 각 심사관마다 걸리는 시간을 전체 시간으로 나눈 후 모두 더해주면,
			// 심사관마다 대할 수 있는 인원 수가 나오고 시간에 따른 가능한 인원 수가 나온다.
			for (int i = 0; i < times.length; i++) {
				count += mid / times[i];
			}

			// 가능한 인원 수가 심사받아야할 인원 수보다 많거나 같다면 
			// 시간을 더 줄였을 때 가능한 지 확인한다.
			if (count >= n) {
				answer = mid;
				end = mid - 1;
			}
			// 가능한 인원 수가 심사받아야할 인원 수보다 적다면
			// 시간을 더 늘려야 하므로 늘린 후 확인한다.
			else {
				start = mid + 1;
			}
		}

		return answer;
	}
}
