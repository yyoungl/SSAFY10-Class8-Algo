import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Gubeomlee_20230823_B_1931 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int len = sc.nextInt();
		int[][] arr = new int[len][2];

		for (int i = 0; i < len; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			arr[i] = new int[] { start, end };
		}
		// 종료 시간을 기준으로 오름차순 정렬한다.
		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
		Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

		int count = 0;
		int end = 0;

		for (int i = 0; i < len; i++) {
			if (end <= arr[i][0]) { // 종료 시간 이후에 시작하는 경우
				end = arr[i][1]; // 현재 활동의 종료 시간으로 갱신한다.
				count++;
			}
		}

		System.out.println(count);
		sc.close();
	}
}