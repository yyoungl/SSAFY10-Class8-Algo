import java.util.Scanner;

public class yyoungl_20230824_B_2839 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		 
		// ���� �����ʹ� �ٸ��� ������ ������ �������� �����Ѵٰ� �ؼ� �ذ�Ǵ� �� �ƴϴ�!!

		while (N > 0) {
			// ���� N�� 5�� ������ �������ٸ� ����Ʈ
			if (N%5==0) {
				answer += N/5;
				break;
				// N�� 1�̳� 2�� 3, 5�� ǥ���� �� ���� ������ -1
			} else if (N==1 || N==2) {
				answer = -1;
				break;
			} else {
				// �װ͵� �ƴ϶�� �ϴ� 3kg ������ �ϳ� ���, �ݺ��� ������ ����Ѵ�
				N -= 3;
				answer++;
			}
		}
		System.out.println(answer);
	}

}
