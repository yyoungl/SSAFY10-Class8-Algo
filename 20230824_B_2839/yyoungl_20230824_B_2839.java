import java.util.Scanner;

public class yyoungl_20230824_B_2839 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		 
		// 동전 문제와는 다르게 무작정 나누고 나머지로 연산한다고 해서 해결되는 게 아니다!!

		while (N > 0) {
			// 만약 N이 5로 나누어 떨어진다면 베스트
			if (N%5==0) {
				answer += N/5;
				break;
				// N이 1이나 2면 3, 5로 표현할 수 없기 때문에 -1
			} else if (N==1 || N==2) {
				answer = -1;
				break;
			} else {
				// 그것도 아니라면 일단 3kg 봉투에 하나 담고, 반복문 연산을 계속한다
				N -= 3;
				answer++;
			}
		}
		System.out.println(answer);
	}

}
