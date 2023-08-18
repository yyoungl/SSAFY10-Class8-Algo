import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

        // int로 선언했다가 시간 초과가 아닌 `틀렸습니다.` 가 나와서 input 범위를 보고 long으로 수정했습니다.
		long answer = 0;
		long[] buildings = new long[N];
		for (int i=0; i<N; i++) {
			buildings[i] = sc.nextInt();
		}

		for (int i=0; i<N; i++) {
            long current = buildings[i];
            // 오른쪽부터 볼 수 있으니까 i 이후 인덱스 살펴보고
			for (int j=i+1; j<N; j++) {
                long next = buildings[j];
                // 높이가 같거나 크면 두 번째 반복문 break 하고 아니면 볼 수 있는 빌딩 개수 ++;
				if (current <= next) {
					break;
				} else answer++;
			}
		}
		System.out.println(answer);
	}

}