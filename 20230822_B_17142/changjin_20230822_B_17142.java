// 연구소 3 (골드 3)
/*
Logic
map을 입력 받을 때 미리 바이러스를 따로 List에 저장, map의 빈칸에 바이러스가 모두 퍼졌는지 확인하기 위해 빈칸의 개수도 미리 구해놓는다.
n개의 바이러스에서 m개를 뽑는 Combination 메서드를 구현한다. -> 바이러스의 개수와 같은 사이즈의 check배열을 만들어 이를 통해 구현. (DFS)
뽑은 바이러스를 Q에다 저장 후 bfs방식으로 바이러스를 퍼뜨림.
-> 여러번 BFS과정을 거쳐야 하므로 map과 같은 배열을 깊은 복사를 통해 매번 생성
-> 감염시킨 0의 개수를 세고 이 부분이 처음에 0의 개수와 같아야만 answer의 값에 새로운 값 넣어줌.
-> 비활성 바이러스는 Q에다 넣어주긴 해야 하지만 이는 빈칸을 감염 한 것이 아니므로 Q에 add만 하고 감염시킨 값을 추가하지는 않는다.
-> 처음 세놓은 0의 개수와 카운트 하던 감염시킨 빈칸의 개수가 같으면 바로 break를 해줘야 함. Q.isEmpty()까지 돌 경우. 0은 이미 모두 감염되었는데 3(비활성바이러스)가 남아있어 감염이 완료되었에도 Q가 계속 돌 수 있음. 
각 과정에서 감염시킨 0의 개수와 처음 세놓은 0의 개수가 일치할때만 answer값과 비교. 
*/

package Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 바이러스의 위치를 효과적으로 관리하기 위한 point 클래스 선언. 
class point_1{
	int x;
	int y;
	public point_1(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class _17142_연구소 {

	
	static int N,M;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[][] map;
	static int[] check;
	static int Empty=0;
	static int answer=Integer.MAX_VALUE;
	// 처음에 map을 입력 받을때 바이러스가 들어오면 저장할 리스트
 	static ArrayList<point_1> Virus=new ArrayList<>();
	// m개의 바이러스를 뽑고 이를 bfs를 통해 map을 감염시키는데 걸리는 시간을 구하는 메소드
	static void spread(int[] check) {
		// 본래의 map이 수정되면 안되기 때문에 map을 깊은복사를 통해 copymap을 만듬.
		int[][] copymap=new int[N][N];
		Queue<point_1> Q=new LinkedList<>();
		// 감염시킨 0의 칸 개수를 담을 변수
		int Em=0;
		// 칸을 모두 감염시키는데 걸리는 시간을 담을 변수
		int time=0;
		// map배열 깊은복사
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copymap[i][j]=map[i][j];
				
			}
		}
		// 앞서 check배열을 통해 고른 m개의 바이러스를 Q에 저장해주는 작업 여기서 비활성 바이러스는 3으로 따로 저장해둔다.
		for(int i=0;i<Virus.size();i++) {
			if(check[i]==1) {
				Q.add(Virus.get(i));
				copymap[Virus.get(i).x][Virus.get(i).y]=1;
			}
			else copymap[Virus.get(i).x][Virus.get(i).y]=3;
		}
		// BFS
		while(!Q.isEmpty()) {
			// 미리 Q의 사이즈를 구하는 이유는 현재 Q에 있는 모든 원소들이 모두 추출될 떄 즉, 한번의 싸이클이 돌때 1의 시간이 지난 것이므로 time을 계산하기 위해 Q 사이즈만큼 for문을 돈다.
			int size=Q.size();
			time++;
			for(int i=0;i<size;i++) {
				point_1 p=Q.poll();
				// 네방향 탐색.
				for(int j=0;j<4;j++) {
					int nx=p.x+dx[j];
					int ny=p.y+dy[j];
					// 주어진 2차원 배열의 범위안에 있고 벽이 아닐때, 즉 비활성바이러스나 빈 공간일떄만 if문 안으로 들어간다.
					if(nx>=0&&nx<N&&ny>=0&&ny<N&&copymap[nx][ny]!=1) {
						// 비활성 바이러스는 감염시킨 것이 아니기때문에 활성상태로만 바꾼다. 즉, Q에 넣기는 하나 Em을 증가시키지는 않는다. 이미 확인한 곳은 다시 오지 않기 위해 벽으로 바꿈
						if(copymap[nx][ny]==3) {
							Q.add(new point_1(nx,ny));
							copymap[nx][ny]=1;
						}
						// 빈 공간일 떄는 감염을 시켜야 하므로 Em을 증가, Q에 저장, 다시 방문하지 않기 위해 벽으로 바꾼다.
						else {
							Q.add(new point_1(nx,ny));
							copymap[nx][ny]=1;
							Em++;
						}
					}
				}
			}
			// 만약 처음에 세어둔 0의 개수와 감염시킨 0의 개수가 같으면 break; 바로 끝내지 않으면 time이 증가하고, 남은 map에 3이 많다면 Q는 계속 돌게됨 하지만 본 문제는 0만 없으면 성립하는 문제
			if (Em==Empty) break;
		}
		// Em이 처음에 세놓은 0의 개수와 같아야 모두 감염시킨 것이므로 같을때만 answer와 time값 비교
		if(Em==Empty) {
			answer=Math.min(answer, time);
		}
		
	}
	// 입력받은 바이러스들 중에 M개의 바이러스만 뽑는 메서드 DFS로 구현
	static void SelectVirus(int L, int S) {
		if(L==M) {
			spread(check);
			
		}else {
			for(int i=S;i<Virus.size();i++) {
				check[i]=1;
				SelectVirus(L+1,i+1);
				check[i]=0;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// 연구소의 크기가 최대 50이므로 map사이즈는 최대 50X50 입력양이 많기에 BufferedReader사용
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				// 입력시 0의 개수 미리 저장.
				if(map[i][j]==0) Empty++;
				// 바이러스 미리 List에 저장.
				if(map[i][j]==2) Virus.add(new point_1(i,j));
			}
		}
		// 빈공간이의 개수가 0이면 이미 모두 감염이므로 0 출력
		if(Empty==0) System.out.println(0);
		else {
			check=new int[Virus.size()];
			SelectVirus(0, 0);
			
			if(answer==Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(answer);
		}

	}

}
