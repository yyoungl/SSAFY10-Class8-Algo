import java.util.Scanner;

public class yyoungl_20230824_B_2839 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		while (N > 0) {
			if (N%5==0) {
				answer += N/5;
				break;
			} else if (N==1 || N==2) {
				answer = -1;
				break;
			} else {
				N -= 3;
				answer++;
			}
		}
		System.out.println(answer);
	}

}
