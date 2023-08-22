// 설탕 배달
package Baekjune;

import java.util.Scanner;

public class _2839 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int N=sc.nextInt();
		int answer=0;
		// 만약 N이 5의 배수면 N/5 출력.
		if(N%5==0) System.out.println(N/5);
		else {
			// 5가 들어간 개수를 저장할 변수 a, N에 최대로 들어갈 수 있는 5의 개수+1로 초기화.
			int a=N/5+1;
			// 3이 들어간 개수를 저장할 변수 b, 0으로 초기화
			int b=0;
			while(true) {
				// N에 들어 갈 수 있는 5의 개수를 하나씩 감소시키며, 남은 자리에 3이 딱 맞게 들어갈 수 있는지 확인
				a--;
				// 만약 a가 음수이면 이 수는 5와 3으로 이뤄질 수 없으므로 -1을 저장 후 break
				if(a<0) {
					answer=-1;
					break;
				}
				// N에서 a개 만큼의 5가 빠지고 남은수가 3으로 나눠떨어지면 이 경우 쓰인 5와 3의 개수를 저장 후 break
				if((N-(5*a))%3==0) {
					answer=a+(N-(5*a))/3;
					break;
				}
			}
			System.out.println(answer);
		}
		
	}
}
