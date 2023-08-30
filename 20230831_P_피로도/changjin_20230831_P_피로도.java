/*
 * 완전탐색 문제
 * 방문하는 던전이 최대 8개이므로 완벽탐색으로 푼다.
 * 모든 던전을 방문하는 경우가 있는지 탐색 후 없을 시 하나씩 줄여나가다가 답이 나오면 중지한다.
 */
package Programmers;

public class 피로도_Re {
	static int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
	static int k = 80;
	// 방문 여부를 확인 할 체크 배열 선언.
	static int[] check;
	static int answer = 0;
	// 정답이 나오면 나머지 실행을 중지시킬 불린 변수 선언.
	static boolean flag = false;

	// x : 탐색 할 목표 던전의 개수, L : 현재 탐색 한 던전의 개수. sum : 현재 남은 피로도
	static void DFS(int x, int L, int sum) {
		// flag가 true이면 이미 답을 구한 것이므로 시스템에 남은 모든 메서드를 중단시킨다.
		if (flag)
			return;
		// 목표한 던전의 수 x까지 도달하는데 성공 하였다면 답을 저장하고, flag를 true로 바꿔준다.
		if (x == L) {
			answer = x;
			flag = true;
		} else {
			/*
			 * 틀렸던 부분 / 일반적인 완전탐색에서는 1 2 3의 탐색 순서는 중요하지 않기 때문에 DFS에 i의 시작 인덱스를 넣어줌. ex)
			 * DFS(int idx) int i=idx; i< N 하지만 이 문제에서는 1 2 3 순서로 탐색하는 것과 1 3 2, 2 3 1은 다르다
			 * 같은 던전을 탐색 하더라도 결과가 다를 수 있기 때문에 무조건 i=0이고 체크배열에 체크가 되어있으면 contonue 하는 방향으로 로직을
			 * 짠다. 즉, 같은 던전을 탐색하더라도 탐색 순서가 다르면 다른 경우로 계산해야함. 순서가 상관없다면 DFS 인자에 인덱스를 넣어 중복을
			 * 제거한다.
			 */
			for (int i = 0; i < dungeons.length; i++) {
				// 체크 배열이 0인 경우만 다음단계로 넘어 갈 수 있는 가능성이 있음.
				if (check[i] == 0) {
					/*
					 * 남은 피로도와 던전을 도는데 필요한 필요도 비교 로직을 체크배열 비교시 &&로 묶지않고 따로 뺸 이유 남은 피로도가 현재 인덱스의 필요
					 * 피로도 보다 적은 경우. return을 해줘 시간복잡도를 낮추려고 했음. -> 이는 5개의 던전중. 최대 3개의 던전을 선택 할 수 있고
					 * 1-> 4-> 5번 순으로 선택을 해야 할 때 두번째 던전을 선택시 2번 던전의 필요 피로도가 아주 높다면, 3,4,5번 던전은 보지도
					 * 않고 첫번째 던전을 2번 던전을 선택하는 방향으로 탐색하게 됨. DFS과정에서 리턴을 선택하지 않아야 하는 것과 선택해야 하는 것이 있을때
					 * 성급히 return 사용 x
					 */
					if (sum >= dungeons[i][0]) {
						check[i] = 1;
						DFS(x, L + 1, sum - dungeons[i][1]);
						check[i] = 0;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		check = new int[dungeons.length];
		for (int i = dungeons.length; i >= 0; i--) {
			DFS(i, 0, 80);
			if (flag)
				break;
		}
		System.out.println(answer);
	}
}