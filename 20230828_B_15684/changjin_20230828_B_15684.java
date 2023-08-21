// 사다리조작 (골드3)
package Baekjune;

import java.util.Scanner;

public class 사다리조작_15684 {
	// 사다리를 2차원 배열로 저장할 배열 arr 선언.
	static int[][] arr;
	static int N, M, H;

	// Answer-> 추가한 다리의 개수. 다리를 추가 할 수 있는 위치에 다리를 추가해주는 logic
	static void DFS(int Answer, int L) {
		if (L == Answer) {
			// check 메서드가 true를 반환하면 Answer을 프린트하고 시스템을 종료.
			if (check()) {
				System.out.println(Answer);
				System.exit(0);
			}
		} else {
			// 사다리를 탐색하며 다리를 놓을 수 있는 위치에 다리를 추가한다.
			// arr[i][j]와 arr[i][j+1] 모두 0이어야 다리를 놓을 수 있으므로 j는 N-1보다 작을때 까지만 탐색한다.
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= N - 1; j++) {
					if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
						// 다리를 놓고 DFS를 돌고 다시 다리를 끊어주는 logic
						arr[i][j] = 1;
						arr[i][j + 1] = -1;
						DFS(Answer, L + 1);
						arr[i][j] = 0;
						arr[i][j + 1] = 0;
					}
				}
			}
		}
	}

	// BFS를 통해 다리를 추가한 맵으로 문제의 조건이 맞는지 확인해보는 메소드.
	static boolean check() {
		// i는 1번사다리부터 N번사다리
		for (int i = 1; i <= N; i++) {
			// cnt는 사다리를 타고 내려가면서 위치 변화를 저장할 변수. i 위치에서 시작하므로 i로 초기화
			int cnt = i;
			// j는 세로방향 깊이를 나타내는 것. 1부터 H 깊이까지 내려간다.
			for (int j = 1; j <= H; j++) {
				// 내려가다가 arr[j][cnt]==1 이면 우측으로 이어진 사다리이므로 cnt++ 해준다.
				if (arr[j][cnt] == 1)
					cnt++;
				// 내려가다가 arr[j][cnt]==-1 이면 좌측으로 이어진 사다리이므로 cnt-- 해준다.
				else if (arr[j][cnt] == -1)
					cnt--;

			}
			// 시작지점과 최대 깊이까지 내려갔을때 cnt값이 다르면 false반환 (하나의 사다리만 불 일치해도 나머지 사다리는 볼 필요없다 -
			// 백트래킹)
			if (i != cnt)
				return false;
		}
		// N개의 사다리 모두 통과 시 조건 만족이므로 ture 리턴
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		// 행에 깊이 저장. 1부터 H까지 저장해야하므로 크기는 H+1, 열에는 사다리의 넘버 저장.
		arr = new int[H + 1][N + 1];

		// 사다리를 입력받는다. i와 i+1이 이어져있는데 i에서는 오른쪽으로 가야한다는 의미로 1 저장 i+1에는 왼쪽으로 가야 한다는 의미로 -1
		// 저장.
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[a][b + 1] = -1;
		}

		// 최소로 놔야하는 다리를 찾는데 최대 3개만 보라고 하였으므로 Answer 즉, 다리를 놔야하는 개수를 0부터 3까지 증가시키며 각각에 대해
		// DFS확인.
		for (int Answer = 0; Answer <= 3; Answer++) {
			DFS(Answer, 0);
		}
		// for 문을 도는동안 시스템이 종료되지 않으면 0 ~ 3 개로는 조건을 만족할 수 없는 것이므로 -1 출력.
		System.out.println(-1);
	}

}
