package _Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 횡단보도 {

	static class line implements Comparable<line> {
		int NextNode;
		long Time;

		public line(int NextNode, long Time) {
			this.NextNode = NextNode;
			this.Time = Time;
		}

		@Override
		public int compareTo(line o) {
			// TODO Auto-generated method stub
			return (int) (this.Time - o.Time);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] value = new long[N + 1];

		Arrays.fill(value, Long.MAX_VALUE);

		value[1] = 0;

		ArrayList<line>[] arr = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<line>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(new line(b, i));
			arr[b].add(new line(a, i));
		}

		PriorityQueue<line> Q = new PriorityQueue<line>();

		for (int i = 0; i < arr[1].size(); i++) {
			Q.add(arr[1].get(i));
		}

		ArrayList<line> tempLine = new ArrayList<>();

		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		while (!Q.isEmpty()) {

			line k = Q.poll();
			// 큐에서 다음 노드를 뽑았을때 이미 방문한 노드이면 continue;
			if (visited[k.NextNode])
				continue;
			// 방문한 적이 없는 노드라면 그 노드에 방문한 시간을 기록 및 방문처리 해준다.
			value[k.NextNode] = k.Time + 1;
			visited[k.NextNode] = true;

			int NowNode = k.NextNode;

			// 현재 노드에서 갈 수 있는 간선들 확인
			for (int i = 0; i < arr[NowNode].size(); i++) {
				// 현재 노드에 간선으로 갈 수 있는 노드가 방문했던 노드인지 확인.
				// 방문 했다면 continue;
				if (visited[arr[NowNode].get(i).NextNode])
					continue;
				// 뽑은 간선으로 가는 노드가 방문하지 않았던 노드라면 그 간선을 뽑는다.
				line temp = arr[NowNode].get(i);

				// 현재 시각보다 간선에 저장 될 시간이 좀 더 늦을 경우 바로 큐에 넣음
				if (value[NowNode] <= temp.Time) {
					Q.add(temp);

				} else {
					// 주기를 고려하여 새로운 간선을 큐에 넣어준다.
					if ((value[NowNode] / M) * M + temp.Time >= value[NowNode])
						Q.add(new line(temp.NextNode, (value[NowNode] / M) * M + temp.Time));
					else
						Q.add(new line(temp.NextNode, ((value[NowNode] / M) + 1) * M + temp.Time));

				}
			}

		}
		System.out.println(value[N]);
	}
}
