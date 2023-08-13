package programmers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class Jwon_20230814_P_전력망 {

	public static int solution(int n, int[][] wires) {
		int answer = n;

		// 각 노드를 연결하기 위해 list배열을 만든다.
		// 각 list배열을 만든다.
		// 배열의 인덱스는 하나의 노드를 의미하고,
		// 그 인덱스의 접근했을 때 나오는 list는 배열의 인덱스에 인접한 노드들을 의미한다.
		ArrayList<Integer>[] arr = new ArrayList[n+1];

		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = new ArrayList<>();
		}
		
		
		for(int i = 0 ; i < wires.length ; i++) {
			int start = wires[i][0];
			int end = wires[i][1];
			
			// 양방향이므로 start노드와 인접 노드인 end 노드를 바꿔서 저장해준다.
			ArrayList<Integer> list = arr[start];
			list.add(end);
			arr[start] = list;
			
			list = arr[end];
			list.add(start);
			arr[end] = list;
		}
		
		// 각 노드에 인접한 노드들이 들어있는 리스트를 정렬해준다.
		for(int i = 0 ; i < arr.length; i++) {
			Collections.sort(arr[i]);
		}
		
		// 1번 노드부터 확인하자.
		for(int start = 1 ; start < arr.length ; start++) {
			// 그 노드의 있는 모든 연결 노드를 하나씩 끊어가면서 count를 세고,
			// 작은 값을 넣자.
			for(int end = 0 ; end < arr[start].size() ; end++) {
				int count = cut(start,end,arr);
				answer = Math.min(answer, count);
			}
		}
		
		
		return answer;
	}
	
	public static int cut(int start, int end, ArrayList<Integer>[] arr) {
		// 시작점의 연결된 노드를 끊기.
		ArrayList<Integer> list1 = arr[start];
		int removeNum = list1.remove(end);
		arr[start] = list1;
		
		// 시작점의 연결된 노드를 시작점으로 생각하고 연결된 노드에서 시작점 노드를 끊기.
		ArrayList<Integer> list2 = arr[removeNum];
		list2.remove(list2.indexOf(start));
		arr[removeNum] = list2;
		
		// 갯수 찾기.
		int result = find(arr);
		
		// 다시 시작점에서 제거된 노드 연결 시키기.
		list1.add(removeNum);
		arr[start] = list1;
		
		// 다시 제거된 노드에서 시작점 연결 시키기.
		list2.add(start);
		arr[removeNum] = list2;
		
		// 순서가 중요함으로 정렬을 해준다.
		Collections.sort(arr[start]);
		Collections.sort(arr[removeNum]);
		
		return result;
	}
	
	public static int find(ArrayList<Integer>[] arr) {
		
		// 현재 노드를 카운트 했는 지 확인하기 위한 chk배열
		boolean[] chk = new boolean[arr.length];
		Queue<Integer> que = new LinkedList<>();
		int start  = 1;
		// 스타트 인덱스 찾기
		// 처음에는 무조건 1로 시작했지만 1이 없을 경우를 대비해서 찾아준다.
		for(int i = 0; i < arr.length ; i++) {
			if(arr[i].size() > 0) {
				start = i;
				break;
			}
		}
		que.offer(start);
		// 각 인덱스마다 연결된 노드 계속 찾아서 방문 체크.
		while(!que.isEmpty()) {
			int tempStart = que.poll();
			chk[tempStart] = true;
			for(int end : arr[tempStart]) {
				if(!chk[end])
					que.offer(end);
			}
		}
		
		int trueNum = 0;
		int falseNum = 0;
		// 방문한 곳과 방문을 하지 않은 곳을 찾아서 그 둘의 차를 구한다.
		for(int i = 1 ; i < chk.length; i++) {
			if(chk[i]) {
				trueNum++;
			}else {
				falseNum++;
			}
		}
		
		
		return Math.abs(trueNum - falseNum);
	}
}

