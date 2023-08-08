import java.util.Scanner;

//피보나치는 앞, 앞앞 인덱스를 더한 값을 구하는 건데, 보통 재귀로 하고 시간을 줄이기 위해서 DP 를 사용한다.
//여기서 fibo(0) 과 fibo(1)을 사용하는 회수 또한 앞, 앞앞 인덱스에서 사용한 횟수를 더하는 것과 같다 .
//따라서 DP로 구해보려 했는데, ArrayList를 사용해서 size를 통해 0부터 탐색하지 않아도 되게 구현하려 했지만... 인덱스 이슈로 오류가 나서 일단은 Array로 구현함........

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		fibo0[0] = 1;
		fibo0[1] = 0;
		fibo1[0] = 0;
		fibo1[1] = 1;
		// 0과 1에 해당하는 값을 일단 넣어두기

		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
//			System.out.println(fibo1.size());
			for (int j=2; j<=num; j++) {
				fibo0[j] = fibo0[j-1] + fibo0[j-2];
				fibo1[j] = fibo1[j-1] + fibo1[j-2];
			}
			System.out.println(fibo0[num] + " " + fibo1[num]);
		}
	}
	
	// n은 40이하라고 했기 때문에 41칸 만들어 두기 ㅠㅠ
	static int[] fibo0 = new int[41];
	static int[] fibo1 = new int[41];

}