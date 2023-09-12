package algo_study;

import java.util.Arrays;
import java.util.Scanner;


public class B2110 {
	static int N;
	static int[] homes;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int C = sc.nextInt();
		homes = new int[N];
		for (int i=0; i<N; i++)
			homes[i] = sc.nextInt();
		// 이분 탐색을 하려면 집의 위치가 정렬되어 있는 게 편할 텐데
		Arrays.sort(homes);
		int low = 1;
		// 가질 수 있는 가장 긴 거리... 인데 
		// 여기서 +1을 안 해주면 /2 하는 과정에서 소수점을 버리게 돼서 정답이 틀리나 보오...
		int high = homes[N-1] - homes[0] + 1; 
		
		
		// 
		while (low < high) {
			int mid = (high + low)/2;
			// 설치된 공유기의 수가 목표보다 작으면 설치 간격을 좁혀야 되니까 mid로
			if (binary(mid) < C) high = mid;
			// 설치된 공유기의 수가 목표와 같거나 크면 간격을 넓혀야 하므로 low를 mid+1로 설정
			else low = mid+1;
		}
		
		System.out.println(low-1);
		
	}

	// 이진 탐색을 이용하여 mid 거리로 공유기를 설치했을 때, 설치된 공유기의 수 반환
	public static int binary(int mid) {
		// 냅다 1번에 설치
		int cnt = 1;
		int last = homes[0];
		
		for (int i=1; i<N; i++) {
			// 길이를 확인 (마지막에 설치한 공유기를 바탕으로)
			if (homes[i] - last >= mid) {
				// 개수 올려주고, 만약에 거리를 만족하지 않는다면 설치 X
				cnt++;
				// 마지막에 설치한 공유기 초기화
				last = homes[i];
			}
		}
		
		return cnt;
	} 

}
