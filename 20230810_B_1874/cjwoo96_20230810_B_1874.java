package practice_Git02;

import java.util.Scanner;

public class class03 {

	static int[][] arr;
	static int[] dx= {0,0,-1};
	static int[] dy= {1,-1,0};
	static int DFS(int sx,int sy) {
		
		int stx=sx-1;
		int sty=sy;
		int answer=0;
		while(true) {
			for(int i=0;i<3;i++) {
				
				int nx=stx+dx[i];
				int ny=sty+dy[i];
				if(nx>=0&&nx<100&&ny>=0&&ny<100&&arr[nx][ny]==1) {
					stx=nx;
					sty=ny;
					arr[nx][ny]=0;
					break;
				}
			}
			if(stx==0) {
				answer=sty;
				
				break;
				
			}
			
		}
		
		return answer;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] answer=new int[11];
		for(int t=1;t<=10;t++) {
			int sx=0;
			int sy=0;
			int n=sc.nextInt();
			arr=new int[100][100];
			
			for(int i=0;i<100;i++) {
				for(int j=0;j<100;j++) {
					arr[i][j]=sc.nextInt();
					if(arr[i][j]==2) {
						sx=i;
						sy=j;
					}
				}
			}
			
			answer[n]=DFS(sx,sy);
			
		}
		for(int i=1;i<=10;i++) {
			System.out.println("#"+i+" "+answer[i]);
		}
	}
}
