// ���� ���
package Baekjune;

import java.util.Scanner;

public class _2839 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int N=sc.nextInt();
		int answer=0;
		// ���� N�� 5�� ����� N/5 ���.
		if(N%5==0) System.out.println(N/5);
		else {
			// 5�� �� ������ ������ ���� a, N�� �ִ�� �� �� �ִ� 5�� ����+1�� �ʱ�ȭ.
			int a=N/5+1;
			// 3�� �� ������ ������ ���� b, 0���� �ʱ�ȭ
			int b=0;
			while(true) {
				// N�� ��� �� �� �ִ� 5�� ������ �ϳ��� ���ҽ�Ű��, ���� �ڸ��� 3�� �� �°� �� �� �ִ��� Ȯ��
				a--;
				// ���� a�� �����̸� �� ���� 5�� 3���� �̷��� �� �����Ƿ� -1�� ���� �� break
				if(a<0) {
					answer=-1;
					break;
				}
				// N���� a�� ��ŭ�� 5�� ������ �������� 3���� ������������ �� ��� ���� 5�� 3�� ������ ���� �� break
				if((N-(5*a))%3==0) {
					answer=a+(N-(5*a))/3;
					break;
				}
			}
			System.out.println(answer);
		}
		
	}
}
