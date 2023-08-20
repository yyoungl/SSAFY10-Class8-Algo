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
		
		// ������ �迭�� ������ ��, ������ �ð��� ���� ���� �켱����, �׸��� ���� �ð��� ���� ���� �켱���� �����ߴ�.
		Arrays.sort(times, (o1, o2) -> {
			if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
			else return Integer.compare(o1[1], o2[1]);
		});

		// ���� ������ ȸ�ǰ� ���� �ð�
		int end = 0;
		
		// ���� �ð��� ���� ȸ�ǰ� ���� �ð����� ũ�ų� ������ ȸ�Ǹ� �����Ѵ�.
		for (int[] time: times) {
			if (time[0] >= end) {
				// ȸ�� ���� ++ 
				answer++;
				// �ð� �ʱ�ȭ ���ֱ�
				end = time[1];
			}
		}
		
		System.out.println(answer);
		
		
	}

}