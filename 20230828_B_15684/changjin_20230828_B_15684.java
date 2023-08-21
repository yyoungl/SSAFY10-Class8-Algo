// ��ٸ����� (���3)
package Baekjune;

import java.util.Scanner;

public class ��ٸ�����_15684 {
	// ��ٸ��� 2���� �迭�� ������ �迭 arr ����.
	static int[][] arr;
	static int N, M, H;

	// Answer-> �߰��� �ٸ��� ����. �ٸ��� �߰� �� �� �ִ� ��ġ�� �ٸ��� �߰����ִ� logic
	static void DFS(int Answer, int L) {
		if (L == Answer) {
			// check �޼��尡 true�� ��ȯ�ϸ� Answer�� ����Ʈ�ϰ� �ý����� ����.
			if (check()) {
				System.out.println(Answer);
				System.exit(0);
			}
		} else {
			// ��ٸ��� Ž���ϸ� �ٸ��� ���� �� �ִ� ��ġ�� �ٸ��� �߰��Ѵ�.
			// arr[i][j]�� arr[i][j+1] ��� 0�̾�� �ٸ��� ���� �� �����Ƿ� j�� N-1���� ������ ������ Ž���Ѵ�.
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= N - 1; j++) {
					if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
						// �ٸ��� ���� DFS�� ���� �ٽ� �ٸ��� �����ִ� logic
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

	// BFS�� ���� �ٸ��� �߰��� ������ ������ ������ �´��� Ȯ���غ��� �޼ҵ�.
	static boolean check() {
		// i�� 1����ٸ����� N����ٸ�
		for (int i = 1; i <= N; i++) {
			// cnt�� ��ٸ��� Ÿ�� �������鼭 ��ġ ��ȭ�� ������ ����. i ��ġ���� �����ϹǷ� i�� �ʱ�ȭ
			int cnt = i;
			// j�� ���ι��� ���̸� ��Ÿ���� ��. 1���� H ���̱��� ��������.
			for (int j = 1; j <= H; j++) {
				// �������ٰ� arr[j][cnt]==1 �̸� �������� �̾��� ��ٸ��̹Ƿ� cnt++ ���ش�.
				if (arr[j][cnt] == 1)
					cnt++;
				// �������ٰ� arr[j][cnt]==-1 �̸� �������� �̾��� ��ٸ��̹Ƿ� cnt-- ���ش�.
				else if (arr[j][cnt] == -1)
					cnt--;

			}
			// ���������� �ִ� ���̱��� ���������� cnt���� �ٸ��� false��ȯ (�ϳ��� ��ٸ��� �� ��ġ�ص� ������ ��ٸ��� �� �ʿ���� -
			// ��Ʈ��ŷ)
			if (i != cnt)
				return false;
		}
		// N���� ��ٸ� ��� ��� �� ���� �����̹Ƿ� ture ����
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		// �࿡ ���� ����. 1���� H���� �����ؾ��ϹǷ� ũ��� H+1, ������ ��ٸ��� �ѹ� ����.
		arr = new int[H + 1][N + 1];

		// ��ٸ��� �Է¹޴´�. i�� i+1�� �̾����ִµ� i������ ���������� �����Ѵٴ� �ǹ̷� 1 ���� i+1���� �������� ���� �Ѵٴ� �ǹ̷� -1
		// ����.
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = 1;
			arr[a][b + 1] = -1;
		}

		// �ּҷ� �����ϴ� �ٸ��� ã�µ� �ִ� 3���� ����� �Ͽ����Ƿ� Answer ��, �ٸ��� �����ϴ� ������ 0���� 3���� ������Ű�� ������ ����
		// DFSȮ��.
		for (int Answer = 0; Answer <= 3; Answer++) {
			DFS(Answer, 0);
		}
		// for ���� ���µ��� �ý����� ������� ������ 0 ~ 3 ���δ� ������ ������ �� ���� ���̹Ƿ� -1 ���.
		System.out.println(-1);
	}

}
