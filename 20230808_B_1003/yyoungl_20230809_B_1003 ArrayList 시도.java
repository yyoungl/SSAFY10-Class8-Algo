import java.util.Arrays;
import java.util.Collections;

public class yyoungl_20230809_P_minerals {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		fibo0.add(1);
		fibo0.add(0);
		fibo1.add(0);
		fibo1.add(1);

		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			int answer0 = 0;
			int answer1 = 1;
			if (num >=2) {
//			System.out.println(fibo1.size());
				if (fibo1.size() < num) {
					int size = fibo1.size();
					for (int idx = size; idx <= num; idx++) {
	//					System.out.println(fibo1);
						fibo1.add(fibo1.get(idx - 2) + fibo1.get(idx - 1));
	//					System.out.println(fibo1.toString());
					}
	
					for (int idx = size; idx <= num; idx++) {
						fibo0.add(fibo0.get(idx - 2) + fibo0.get( idx - 1));
					}
				}
			}
			answer0 = fibo0.get(num);
			answer1 = fibo1.get(num);
			System.out.println(answer0 + " " + answer1);
		}
	}

	static ArrayList<Integer> fibo0 = new ArrayList<>();
	static ArrayList<Integer> fibo1 = new ArrayList<>();
}


