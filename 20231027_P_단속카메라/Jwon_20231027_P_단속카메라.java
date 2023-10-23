package Algorithm.algorithm.programmers;

import java.util.*;

public class Jwon_20231027_P_단속카메라 {
	public int solution(int[][] routes) {
		int answer = 0;
		// 회의실 배정 문제를 생각하며 풀었다.
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		// 끝을 기준으로 오름차순 정렬을 하고, 끝에만 카메라를 달아준다.
		answer = 1;
		int camera = routes[0][1];
		for (int i = 1; i < routes.length; i++) {
			// 만약 마지막으로 단 카메라가 다음 자동차도 확인할 수 있다면
			// 패스
			if (routes[i][0] <= camera && routes[i][1] >= camera) {
				continue;
			}
			// 그렇지 않다면 카메라를 설치하고, 카메라 수 늘리기.
			camera = routes[i][1];
			answer++;
		}
		return answer;
	}
}
