package Algorithm.algorithm.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Jwon_20230913_B_2110_공유기설치 {

	// 이분탐색은 뭔가 답을 이분탐색으로 점점 찾아간다고 생각하면 될 것 같다.
	// 드가자
	
	// 가장 짧은 거리가 가능한 가장 길어야 하므로 가장 짧은 거리 즉, 정답을 이분탐색으로 하나하나 찾는다.
	// 어느 임의의 거리를 가지고 공유기가 설치가 가능한 지 확인해본다.
	// 만약 공유기 설치를 다 했는데도 설치해야할 공유기가 남아있다면 거리를 줄인다.
	// 만약 공유기 설치를 다 했을 때 설치해야할 공유기가 0보다 작거나 같다면 최대 거리를 찾아야하므로 거리를 늘려서 다시 찾는다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		// 인접한 집의 거리를 생각하기 위해서 정렬을 해준다.
		Arrays.sort(arr);
		// 시작 점과 끝 점, 최대 거리를 선언해준다.
		// 여기서 시작 점은 거리임을 기억하자.
		int max = 0;
		int start = 1;
		int end = arr[n - 1] - arr[0];
		
		// 시작 점이 끝 점보다 작거나 같을 때 계속 확인해준다.
		while (start <= end) {
			int mid = (start + end) / 2;
			int count = 1;

			int installIndex = 0;
			for (int i = 1; i < n; i++) {
				if (arr[i] - arr[installIndex] >= mid) {
					installIndex = i;
					count++;
				}
			}
			// 만약 공유기의 갯수가 부족하다면 거리가 너무 큰 것이므로 거리를 줄이고,
			if (count < c) {
				end = mid - 1;
			} 
			// 만약 공유기의 충분하다면 더 큰 거리가 가능한 지 확인하기 위해 거리를 늘인다.
			else {
				max = mid;
				start = mid + 1;
			}
		}
		// 빠끄!
		System.out.println(max);
	}

}
