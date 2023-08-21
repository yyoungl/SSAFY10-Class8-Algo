// ������ 3 (��� 3)
/*
Logic
map�� �Է� ���� �� �̸� ���̷����� ���� List�� ����, map�� ��ĭ�� ���̷����� ��� �������� Ȯ���ϱ� ���� ��ĭ�� ������ �̸� ���س��´�.
n���� ���̷������� m���� �̴� Combination �޼��带 �����Ѵ�. -> ���̷����� ������ ���� �������� check�迭�� ����� �̸� ���� ����. (DFS)
���� ���̷����� Q���� ���� �� bfs������� ���̷����� �۶߸�.
-> ������ BFS������ ���ľ� �ϹǷ� map�� ���� �迭�� ���� ���縦 ���� �Ź� ����
-> ������Ų 0�� ������ ���� �� �κ��� ó���� 0�� ������ ���ƾ߸� answer�� ���� ���ο� �� �־���.
-> ��Ȱ�� ���̷����� Q���� �־��ֱ� �ؾ� ������ �̴� ��ĭ�� ���� �� ���� �ƴϹǷ� Q�� add�� �ϰ� ������Ų ���� �߰������� �ʴ´�.
-> ó�� ������ 0�� ������ ī��Ʈ �ϴ� ������Ų ��ĭ�� ������ ������ �ٷ� break�� ����� ��. Q.isEmpty()���� �� ���. 0�� �̹� ��� �����Ǿ��µ� 3(��Ȱ�����̷���)�� �����־� ������ �Ϸ�Ǿ����� Q�� ��� �� �� ����. 
�� �������� ������Ų 0�� ������ ó�� ������ 0�� ������ ��ġ�Ҷ��� answer���� ��. 
*/

package Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ���̷����� ��ġ�� ȿ�������� �����ϱ� ���� point Ŭ���� ����. 
class point_1{
	int x;
	int y;
	public point_1(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class _17142_������ {

	
	static int N,M;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[][] map;
	static int[] check;
	static int Empty=0;
	static int answer=Integer.MAX_VALUE;
	// ó���� map�� �Է� ������ ���̷����� ������ ������ ����Ʈ
 	static ArrayList<point_1> Virus=new ArrayList<>();
	// m���� ���̷����� �̰� �̸� bfs�� ���� map�� ������Ű�µ� �ɸ��� �ð��� ���ϴ� �޼ҵ�
	static void spread(int[] check) {
		// ������ map�� �����Ǹ� �ȵǱ� ������ map�� �������縦 ���� copymap�� ����.
		int[][] copymap=new int[N][N];
		Queue<point_1> Q=new LinkedList<>();
		// ������Ų 0�� ĭ ������ ���� ����
		int Em=0;
		// ĭ�� ��� ������Ű�µ� �ɸ��� �ð��� ���� ����
		int time=0;
		// map�迭 ��������
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copymap[i][j]=map[i][j];
				
			}
		}
		// �ռ� check�迭�� ���� �� m���� ���̷����� Q�� �������ִ� �۾� ���⼭ ��Ȱ�� ���̷����� 3���� ���� �����صд�.
		for(int i=0;i<Virus.size();i++) {
			if(check[i]==1) {
				Q.add(Virus.get(i));
				copymap[Virus.get(i).x][Virus.get(i).y]=1;
			}
			else copymap[Virus.get(i).x][Virus.get(i).y]=3;
		}
		// BFS
		while(!Q.isEmpty()) {
			// �̸� Q�� ����� ���ϴ� ������ ���� Q�� �ִ� ��� ���ҵ��� ��� ����� �� ��, �ѹ��� ����Ŭ�� ���� 1�� �ð��� ���� ���̹Ƿ� time�� ����ϱ� ���� Q �����ŭ for���� ����.
			int size=Q.size();
			time++;
			for(int i=0;i<size;i++) {
				point_1 p=Q.poll();
				// �׹��� Ž��.
				for(int j=0;j<4;j++) {
					int nx=p.x+dx[j];
					int ny=p.y+dy[j];
					// �־��� 2���� �迭�� �����ȿ� �ְ� ���� �ƴҶ�, �� ��Ȱ�����̷����� �� �����ϋ��� if�� ������ ����.
					if(nx>=0&&nx<N&&ny>=0&&ny<N&&copymap[nx][ny]!=1) {
						// ��Ȱ�� ���̷����� ������Ų ���� �ƴϱ⶧���� Ȱ�����·θ� �ٲ۴�. ��, Q�� �ֱ�� �ϳ� Em�� ������Ű���� �ʴ´�. �̹� Ȯ���� ���� �ٽ� ���� �ʱ� ���� ������ �ٲ�
						if(copymap[nx][ny]==3) {
							Q.add(new point_1(nx,ny));
							copymap[nx][ny]=1;
						}
						// �� ������ ���� ������ ���Ѿ� �ϹǷ� Em�� ����, Q�� ����, �ٽ� �湮���� �ʱ� ���� ������ �ٲ۴�.
						else {
							Q.add(new point_1(nx,ny));
							copymap[nx][ny]=1;
							Em++;
						}
					}
				}
			}
			// ���� ó���� ����� 0�� ������ ������Ų 0�� ������ ������ break; �ٷ� ������ ������ time�� �����ϰ�, ���� map�� 3�� ���ٸ� Q�� ��� ���Ե� ������ �� ������ 0�� ������ �����ϴ� ����
			if (Em==Empty) break;
		}
		// Em�� ó���� ������ 0�� ������ ���ƾ� ��� ������Ų ���̹Ƿ� �������� answer�� time�� ��
		if(Em==Empty) {
			answer=Math.min(answer, time);
		}
		
	}
	// �Է¹��� ���̷����� �߿� M���� ���̷����� �̴� �޼��� DFS�� ����
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
		// �������� ũ�Ⱑ �ִ� 50�̹Ƿ� map������� �ִ� 50X50 �Է¾��� ���⿡ BufferedReader���
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				// �Է½� 0�� ���� �̸� ����.
				if(map[i][j]==0) Empty++;
				// ���̷��� �̸� List�� ����.
				if(map[i][j]==2) Virus.add(new point_1(i,j));
			}
		}
		// ��������� ������ 0�̸� �̹� ��� �����̹Ƿ� 0 ���
		if(Empty==0) System.out.println(0);
		else {
			check=new int[Virus.size()];
			SelectVirus(0, 0);
			
			if(answer==Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(answer);
		}

	}

}
