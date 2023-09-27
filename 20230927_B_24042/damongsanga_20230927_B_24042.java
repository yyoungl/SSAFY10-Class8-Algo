import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int from, to; 
	long dist;
	public Edge(int from, int to, long dist) {
		this.from = from;
		this.to = to;
		this.dist = dist;
	}

	public int compareTo(Edge o) {
		return (int) (this.dist - o.dist);
	}
	
}

public class damongsanga_20230927_B_24042 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		boolean[] visited = new boolean[N+1];
		
		ArrayList<Edge>[] arr = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 양방향 간선
			arr[from].add(new Edge(from, to, i));
			arr[to].add(new Edge(to, from, i));
		}
		
		// 다익스트라
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 초기값 설정
		long time = 0;
		pq.add(new Edge(1,1,0)); // 시작점
		dist[1] = 0; // 거리배열 초기화
		
		// 지금 시간보다 짧은 길이가 들어오면 M 추가하기
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			// 우선순위에서 뺄 때 방문처리
			visited[now.to] = true;
			// 시간을 현재 위치에 도착하는 시간 (=거리) 로 설정
			time = dist[now.to];
			
			ArrayList<Edge> tmp = arr[now.to];
			for (int i = 0; i < tmp.size(); i++) {
				Edge next = tmp.get(i);
				// 방문한 노드는 더이상 방문하지 않음(그리디)
				if (visited[next.to]) continue;
				// 만약 현재 간선의 거리가 지금 시간보다 짧으면 x번의 주기를 더해서 현재 시간보다 크게 바꿔줘야 한다
				// 현재 시간과 같을 수는 없다 (정확히 주기만큼의 간선이 있기 때문! 왜인지 생각해보자)
				if (time > next.dist) next.dist += ((time - next.dist) / M + 1) * M;

				// 만약 갱신된다면 pq에 넣는다
				// 만약 다음 위치가 도착위치라면 바로 break;
				if (next.dist < dist[next.to]) {
					dist[next.to] = next.dist;
					if (next.to == N) break;
					pq.add(next);
				}
			}

		}
		
		System.out.println(dist[N]+1);

	}
}