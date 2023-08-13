import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class yyoungl_2023_0814_P_network {
	/*
	오랜만에 전력망을 봐서 반가웠다...
	이전에 파이썬으로 구현한 것을 자바로 옮기는 데만 꽤나 걸렸다.
	https://letusgrow.tistory.com/19 이전에 쓴 것을 가져와 보았어요~ (파이썬임..)
	
 	- wires에 담긴 연결 정보 (a, b) 를 바탕으로 graph에 노드별로 연결된 노드를 넣고, 하나씩 끊으면서 구현했다.
 	  노드마다 연결된 노드의 수가 다르기에 몇 개가 들어갈지 정할 수 없고, 삭제와 추가가 자유로워야 했기 때문에 graph는 ArrayList를 담은 Array를 만들었다
  	
   	- wires처럼 연결 정보를 담은 배열의 경우, 문제마다 다양한 형태로 제공되는데,  a, b 가 연결되어 있을 때 1한쪽만 제공하는 경우가 있고, 
          2a, b 모두 b, a와 연결되어 있다는 것을 알려주는 경우 모두가 있기 때문에 graph를 만들 때 중복에 유의해야 한다는 생각을 했다.
	*/ 
	static int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
	static int n = 9;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Solution(n, wires));
	}
	
	static int Solution (int n, int[][] wires)
	{
		int answer = n-2; // 최악의 경우 n개의 노드가 n-1, 1로 나눠질 수 있기 때문에 차이의 최대값을 n-2로 초기화했다.
		ArrayList<Integer>[] graph = new ArrayList[n+1];
		
		for (int i=0; i<n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<wires.length; i++) {
			// wires에 담긴 (a, b)에 대하여
			// graph[a] = <b>
			// graph[b] = <a> 추가해 주기
			graph[wires[i][0]].add(wires[i][1]);
			graph[wires[i][1]].add(wires[i][0]);
		}
		
		for (int i=0; i<wires.length; i++) {
			// bfs 를 구현하는 부분이다. wires에 담긴 (a, b)를 하나씩 끊어준다.
			int a = wires[i][0];
			int b = wires[i][1];
			graph[a].remove(graph[a].indexOf(b));
			graph[b].remove(graph[b].indexOf(a));

			// a, b를 끊었기 때문에 시작점을 a로 할 때, b로 할 때 각각 숫자를 구해주고 기존의 값과 차이를 비교해 준다
			answer = Math.min(Math.abs(bfs(a, graph)-bfs(b, graph)), answer);
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		return answer;
		
	}
	// start와 연결된 노드의 개수를 확인하는 Array
	static int bfs(int start, ArrayList<Integer>[] graph) {
		// 방문 여부를 확인하는 Array
		boolean[] visited = new boolean[n+1];
		Queue<Integer> q = new LinkedList<>();
		// q에 시작 노드 넣고
		q.offer(start);
		visited[start] = true;
		// 전력망에 연결된 노드 개수를 1로 초기화합니다
		int cnt=1;	
		while (!q.isEmpty()) {
			// q 첫 번째 값을 꺼내서
			int node = q.poll();
			// graph에 담긴 node의 연결 정보를 하나씩 탐색 
			for (int point: graph[node]) {
				// 만약 그 노드에 방문하지 않았다면
				if (!visited[point]) {
					// 이후에 확인하기 위해 q에 넣고
					q.offer(point);
					// 방문값 바꿔주기
					visited[point] = true;
					cnt++;
				}
			}
		}
//		System.out.println("start: "+start+" cnt: "+cnt);
		return cnt;
		
	}
}
