// 옥상정원꾸미기 (이중 for문 - 실행시간 1650ms) 
package _Baekjune;

import java.util.Scanner;

public class _6198 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		// 옥상의 높이 값 저장
		int[] arr=new int[n];
		// 빌딩의 개수가 최대 80000개이므로 int가 아닌 long형으로 벤치마킹이 가능한 빌딩의 수의 합을 담을 변수 cnt 선언
		long cnt=0;
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		// 오른쪽으로만 탐색하면 되므로 오른쪽으로 탐색하다 내가 더높으면 cnt를 증가 낮으면 break 해준다
		for(int i=0;i<n-1;i++) {
			for(int j=i+1;j<n;j++) {
				if(arr[i]>arr[j]) cnt++;
				else break;
			}
		}
		System.out.println(cnt);
	}
}
