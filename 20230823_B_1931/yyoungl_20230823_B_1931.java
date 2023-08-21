import java.util.Arrays;
import java.util.Scanner;

public class yyoungl_20230823_B_1931 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[][] times = new int[N][2];
		int answer = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<2; j++) {
				times[i][j] = sc.nextInt();
				
			}
		}
		
		// 이차원 배열을 정렬할 때, 끝나는 시간이 빠른 것을 우선으로, 그리고 시작 시간이 빠른 것을 우선으로 정렬했다.
		Arrays.sort(times, (o1, o2) -> {
			if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
			else return Integer.compare(o1[1], o2[1]);
		});

		// 가장 마지막 회의가 끝난 시간
		int end = 0;
		
		// 시작 시간이 이전 회의가 끝난 시간보다 크거나 같으면 회의를 진행한다.
		for (int[] time: times) {
			if (time[0] >= end) {
				// 회의 개수 ++ 
				answer++;
				// 시간 초기화 해주기
				end = time[1];
			}
		}
		
		System.out.println(answer);
		
		
	}

}