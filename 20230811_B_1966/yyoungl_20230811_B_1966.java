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
			// 처음 인덱스와 배열을 가져가기 위해 큐 두 개 만들기
			Queue<Integer> q = new LinkedList<>();
			Queue<Integer> idx = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				// 입력받은 값과 함께 인덱스 추가
				q.offer(sc.nextInt());
				idx.offer(i);
			}
			// 순서를 담을 변수 1로 초기화
			int order = 1;
			// 중요도가 높은 것부터 출력한다고 했으므로 거꾸로 도는 반복문을 만들었다
			for (int fast = 9; fast > 0; fast--) {
				// 불필요한 연산 (출력을 완료한 후 남은 중요도를 모두 살펴보는 것을 방지하기 위해 만든 boolean)
				boolean isComplete = false;
				// q에 중요도 fast의 할일이 있는가?
				while (q.contains(fast)) {
					// 하나씩 꺼내서 확인
					int check = q.poll();
					int cIdx = idx.poll();
					// 체크하고 싶은 중요도와 순서를 알고 싶은 task의 인덱스가 모두 일치한다면
					if (check == fast && cIdx == M) {
						// while문 break 하고 isComplete = true;
						System.out.println(order);
						isComplete = true;
						break;
					} else if (check == fast) {
						// check == fast 라면 작업을 하나 끝낸 것이므로 q에서 삭제한 상태로 순서 ++;
						order++;
					} else {
						// 둘 다 아니라면 우선순위에 해당되지 않기 때문에 뒤로 보내기
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
