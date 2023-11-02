import java.util.Arrays;

class damongsanga_20231027_P_단속카메라 {
	public int solution(int[][] routes) {
		int answer = 1;

		// 시작 기준으로 정렬 
		Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

		int left = routes[0][0]; // 겹치는 구간의 왼쪽
		int right = routes[0][1]; // 겹치는 구간의 오른쪽
		for (int i = 1; i < routes.length; i++) {
			left = Math.max(left, routes[i][0]);
			right = Math.min(right, routes[i][1]);
			if (left > right) { // 겹치는 구간의 왼쪽, 오른쪽이 바뀌면
				answer++; // 추가 카메라가 필요
				right = routes[i][1]; // 왼쪽은 갱신할 필요 없음 (정렬되어있음으로)
			}
		}

		return answer;
	}
}