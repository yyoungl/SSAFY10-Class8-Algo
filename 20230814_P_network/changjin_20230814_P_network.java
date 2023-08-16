//전력망을 둘로 나누기
package programmers;

import java.util.ArrayList;

public class _86971 {
	static int n = 9;
	static int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
	// N개의 노드와 각각 대응되는 인접 리스트 생성. 
	static ArrayList<Integer>[] list;
	// bfs를 통한 노드 순회 시 방문 여부를 확인 할 체크 배열 생성 
	static int[] check;
	// 방문한 노드의 개수를 저장할 변수 선언
	static int answer;
	static void BFS(int k) {
		// 리스트가 비어있으면 더이상 순회할 노드가 없으므로 리턴해준다.
		if(list[k].size()==0) return; 
		else {
			// 리스트의 사이즈만큼 순회하는데 이때 이미 방문한 노드는 체크 배열을 통해 확인 후 방문하지 않는다.
			for(int i=0;i<list[k].size();i++) {
				int r=list[k].get(i);
				if(check[r]==0) {
					//방문하지 않은 노드의 경우 answer++를 해주고 check배열에 방문 표시를 한 후 그 노드를 방문한다.
					answer++;					
					check[r]=1;
					BFS(r);
				}
			}
		}
	}
	public static void main(String[] args) {
		//1부터 n까지의 리스트가 필요하므로 n+1의 사이즈를 가지는 배열 생성.
		list=new ArrayList[n+1];
		// 리스트 생성 후 배열에 담는다.
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<Integer>();
		}
		// 양 방향으로 방문 가능하므로 양방향으로 리스트에 담아준다.
		for(int i=0;i<wires.length;i++) {
			int a1=wires[i][0];
			int a2=wires[i][1];
			list[a1].add(a2);
			list[a2].add(a1);
		}
		// 간선들을 하나 씩 제거해가며 두 노드들의 집단을 뺸 값의 최소값을 저장할 변수 min이므로 초기에는 최대값을 저장한다.
		int min=Integer.MAX_VALUE;
	    
		for(int i=0;i<wires.length;i++) {
			//간선을 하나씩 제거하고 BFS를 돌릴때마다 체크배열 초기화
			check=new int[n+1];
			// 두 개의 노드 집단 중 하나의 집단의 최소크기인 1 저장. 
			answer=1;
			
			int a1=wires[i][0];
			int a2=wires[i][1];
			// 간선 제거.
			list[a1].remove(Integer.valueOf(a2));
			list[a2].remove(Integer.valueOf(a1));
			check[1]=1;
			// 아무 노드를 시작점으로 하여 순회
			BFS(1);
			// 두 노드 집단간의 차와 기존 min값과 비교하여 최소값 저장.
			min=Math.min(Math.abs((n-answer)-answer), min);
			// 제거했던 간선 다시 추가
			list[a1].add(a2);
			list[a2].add(a1);
		}
		System.out.println(min);
		
	}

	

}
