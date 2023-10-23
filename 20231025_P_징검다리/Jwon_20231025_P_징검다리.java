package Algorithm.algorithm.programmers;

import java.util.*;

public class Jwon_20231025_P_징검다리 {

	// 문제에서 주어지는 입력값이 상당히 크고 조합으로 뽑기엔...
	// 50000 C 25000인데 한 번 시간이 상당히 많이 남는다면 해보자 (나는 안할란다.)
	// 즉, 단순히 조합으로 풀면 시간이 매우 많이 걸려 양자컴퓨터가 발명되기 전까진 조합으로 풀 수 없는 문제였다.
	// 따라서 시간을 상당히 많이 줄일 필요가 있었고, 이분탐색으로 접근을 하였다.
	// 최소값 중 가장 큰 값을 예시로 하나 정하고, 그 값을 정답인 지 확인하는 식으로 간다.
	// 만약 정답일 가능성을 판단으로 크거나 줄이며 결국 정답으로 만들어내는 방식을 택한다.
	// 위의 방식은 곧 이분탐색을 의미한다.

	// 이 문제에서는 거리를 정해놓고
	// 현재의 거리가 돌을 더 이상 안 부숴도 돌 간의 간격이 가능한 지를 확인한다.
	// 만약 가능하다면 정답일 가능성이 있으므로 answer에 mid 값을 저장(mid는 곧 거리가 된다.)
	// 그리고, 가능하다면 거리가 더 클 경우에도 정답이 될 수 있는 가능성이 있으므로 더 큰 경우에도 확인한다.
	// 만약 가능하지 않다면 (거리가 너무 커 돌을 더 부숴야할 경우) 거리를 줄여서 확인한다.
	public int solution(int distance, int[] rocks, int n) {
		int answer = distance;
		// 이분탐색 특징!
		// 이분탐색은 정렬이 되어있는 상태에서만 가능하다.
		// 따라서 돌들도 정렬이 되어있어야 한다.
		Arrays.sort(rocks);
		int[] newRocks = new int[rocks.length + 2];
		newRocks[0] = 0;
		newRocks[newRocks.length - 1] = distance;
		for (int i = 0; i < rocks.length; i++) {
			newRocks[i + 1] = rocks[i];
		}

		int left = 1;
		int right = distance;
		while (left <= right) {
			int mid = (left + right) / 2;
			int temp = n;

			for (int i = 1, j = 0; i < newRocks.length; i++) {
				int tempDis = newRocks[i] - newRocks[j];
				// 돌 사이의 간격이 현재의 거리보다 작다면
				// 계속 돌을 부숴주며 돌사이의 간격을 늘린다.
				while (tempDis < mid) {
					i++;
					temp--;
					if (i >= newRocks.length) {
						break;
					}
					tempDis = newRocks[i] - newRocks[j];
				}
				j = i;
			}
			// 만약 정해진 부숴야할 돌의 갯수보다 부순 돌의 갯수가 더 많다면
			// 거리가 너무 큰 것,
			// 돌의 갯수가 남거나 딱 맞다면
			// 거리가 작거나 정답인 것이다.
			if (temp >= 0) {
				left = mid + 1;
				answer = mid;
			} else if (temp < 0) {
				right = mid - 1;
			}
		}

		return answer;
	}
}
