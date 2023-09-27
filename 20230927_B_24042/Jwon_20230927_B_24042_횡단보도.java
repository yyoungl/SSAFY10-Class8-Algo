package Algorithm.algorithm.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Jwon_20230927_B_24042_횡단보도 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ArrayList<Edge>[] arr = new ArrayList[n + 1];
		// 결과 값을 저장해놓을 array
		long[] timeArr = new long[n + 1];
		for (int time = 1; time < m + 1; time++) {
			// 무방향으로 간선을 저장한다.
			int from = sc.nextInt();
			int to = sc.nextInt();
			if (arr[from] == null) {
				arr[from] = new ArrayList<>();
			}
			arr[from].add(new Edge(to, time));
			if (arr[to] == null) {
				arr[to] = new ArrayList<>();
			}
			arr[to].add(new Edge(from, time));
		}

		// 다익스트라이기 때문에 처음 값들을 무한으로 해놓는다.
		Arrays.fill(timeArr, Long.MAX_VALUE);
		boolean[] chk = new boolean[n + 1];

		// 값이 작은 것부터 넣기 위해 pque
		PriorityQueue<Edge> pque = new PriorityQueue<>();

		// 처음엔 나 자신을 가르키는 (시작점) 간선 만들기
		pque.offer(new Edge(1, 0));
		timeArr[1] = 0;
		// 방문 확인 배열에 true를 해준다.
		chk[1] = true;
		while (!pque.isEmpty()) {
			Edge now = pque.poll();
			chk[now.to] = true;
			if (arr[now.to] != null) {
				for (Edge next : arr[now.to]) {
					// 만약 방문한 곳이라면 굳이 할 필요 없는 작업을 하지 않는다.
					if (chk[next.to])
						continue;
					// 정덕주 짱짱맨
					if (timeArr[now.to] > next.time) {
						next.time += ((timeArr[now.to] - next.time) / m + 1) * m;
					}
					// 여기서 값을 넣을 때 true를 하지 않는 이유 :
					// 혹여나 더 작은 값이 들어올 수가 없다.
					// 현재 노드는 가장 짧은 (priorityQueue 때문에 짧은 간선들이 들어온다.) 가중치를 갖는 간선으로 온 것을 알 수 있다.
					// next노드가 같을 때 다른 간선들이 있다면 만약 넣을 때 true를 넣으면 더 작은 간선은 continue 때문에 넘어간다.
					// 따라서 가장 작은 값을 넣기 위해 true는 현재 노드를 뽑을 때만 해준다.
					if (timeArr[next.to] > next.time) {
						timeArr[next.to] = next.time;
						pque.offer(next);
					}
				}
			}
		}

		System.out.println(timeArr[n]);
	}

	static class Edge implements Comparable<Edge> {
		int to;
		long time;

		public Edge(int to, long time) {
			super();
			this.to = to;
			this.time = time;
		}

		// priorityQueue 비교 대상
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return (int) (this.time - o.time);
		}

	}
}
