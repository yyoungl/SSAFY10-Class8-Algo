import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class yyoungl_20230811_B_1966 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			Queue<Integer> q = new LinkedList<>();
			Queue<Integer> idx = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				q.offer(sc.nextInt());
				idx.offer(i);
			}
			int order = 1;
			for (int fast = 9; fast > 0; fast--) {
				boolean isComplete = false;
				while (q.contains(fast)) {
					int check = q.poll();
					int cIdx = idx.poll();
					if (check == fast && cIdx == M) {
						System.out.println(order);
						isComplete = true;
						break;
					} else if (check == fast) {
						order++;
					} else {
						q.offer(check);
						idx.offer(cIdx);
					}
				}
				if (isComplete)
					break;
			}
		}
	}
}
