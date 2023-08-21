package Algorithm.algorithm.baekjoon;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Jwon_20230822_B_17142_연구소3 {
	
	// 연구실 지도.
	static int[][] arr ;
	
	// 조합을 만들기 위한 모든 비활성 바이러스의 배열.
	static Virus[] virusArr = new Virus[10];
	
	// 조합을 만들기 위한 활성 바이러스의 조합이 들어간 배열.
	static boolean[] chk = new boolean[10];
	
	// 비활성 바이러스의 갯수
	static int idx = 0;
	
	// 연구실 크기.
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				// 만약 바이러스가 있는 곳이라면 조합을 하기 위해 virusArr에 담아놓는다.
				if(arr[i][j] == 2) {
					Virus virus = new Virus(j, i);
					virusArr[idx++] = virus;
				}
			}
		}
		// find 함수를 통해 값을 도출한다.
		System.out.println(find(0,m,0));
		
	}
	
	static class Virus{
		int x;
		int y;

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int find(int start, int end, int cnt) {
		if(cnt == end) {
			return bfs();
		}

		// 바이러스가 빈 공간을 채워넣을 시간의 최대는 n^2이므로 초기화 해준다.
		int result = (int) Math.pow(n, 2);
		// 바이러스의 조합을 찾아준다.
		// 순열이 아니라 조합이므로 순서를 생각할 필요가 없다.
		// 즉 1번 인덱스를 한 번 사용한 모든 조합을 만들었다면 더 이상 1번이 들어간 수열은 만들어질 필요가 없다.
		for(int i = start; i < idx; i++) {
			// chk 배열은 조합에 들어간 수들의 모임이다.
			chk[i] = true;
			// 따라서 현재 인덱스에 하나 더 높은 인덱스를 start로 하고 찾아준다.
			int temp = find(i+1,end,cnt+1);
			chk[i] = false;
			
			// -1은 모든 빈 공간을 바이러스로 채울 수 없다는 뜻이다.
			// 어처피 모든 빈 공간을 채울 수 없을 때 결과 값이 -1이라 result에 담아도 된다고 생각할 수 있지만,
			// 최소값을 구하는 Math.min 때문에 모든 빈 공간을 채울 수 있는 상황이 와도 -1이 계속 남게된다.
			// 따라서 -1이 나오면 continue를 해준다.
			if(temp == -1) {
				continue;
			}
			result = Math.min(temp, result);
		}
		
		// result가 최초의 선언한 값과 같다면 모두 -1이 나왔다는 의미이므로 
		// 비로소 -1을 리턴한다.
		if(result == (int) Math.pow(n, 2)) {
			return -1;
		}
		return result;
	}
	
	
	private static int bfs() {
		
		Queue<Virus> queue = new LinkedList<>();
		// 바이러스의 감염 체크 혹은 벽 체크.
		boolean[][] virusChk = new boolean[n][n];

		for(int i = 0 ; i < idx ; i++) {
			// 조합으로 완성된 활성 바이러스들을 queue에 넣고, 바이러스 감염 체크 배열에 감염 표시를 해준다.
			if(chk[i]) {
				queue.offer(virusArr[i]);
				virusChk[virusArr[i].y][virusArr[i].x] = true;
			}
		}
		
		// 벽은 갈 수 없으므로 체크 해준다.
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(arr[i][j] == 1) {
					virusChk[i][j] = true;
				}
			}
		}

		int[] dirX = {1,-1,0,0};
		int[] dirY = {0,0,1,-1};
		// 연구소 위치에 따른 시간 저장 배열.
		int[][] virusTime = new int[n][n];
		while(!queue.isEmpty()) {
			Virus virus = queue.poll();
			// 상 하 좌 우로 돌면서 
			for(int i = 0 ; i < 4 ; i++) {
				int tempX = virus.x + dirX[i];
				int tempY = virus.y + dirY[i];
				// 연구소 범위 내에 있는 이미 감염되어 있거나 벽이 아닌 빈 공간을 감염시킨다.
				if(tempX >= 0 && tempX < n && tempY >= 0 && tempY < n && !virusChk[tempY][tempX]) {
					// 감염 표시를 하고,
					virusChk[tempY][tempX] = true;
					// 시간을 전 연구소 위치의 시간 +1 해준다.
					virusTime[tempY][tempX] = virusTime[virus.y][virus.x] + 1;
					queue.offer(new Virus(tempX, tempY));
				}
			}
		}
		
		int max = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(arr[i][j] == 0) {
					// 혹시 빈공간이었던 곳이 감염표시가 되어 있지않다면 -1을 반환한다.
					if(!virusChk[i][j]) {
						return -1;
					}
					// 그렇지 않다면 가장 긴 시간이 걸린 빈 공간이었던 곳을 찾아준다.
					max = Math.max(max, virusTime[i][j]);
				}
				
			}
		}
		return max;
	}
}
