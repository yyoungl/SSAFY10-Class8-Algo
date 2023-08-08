import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


// 중요도와 원래 순서(index)를 저장할 print 클래스 정의
class Print{
	int index;
	int value;
	
	public Print(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	
}

public class damongsanga_20230808_B_1966 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// print 요소들을 받을 queue 생성
			ArrayDeque<Print> queue = new ArrayDeque<>();
			// 각 요소들의 중요도를 저장할 Integer 배열 생성
			Integer[] arr = new Integer[N];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				queue.add(new Print(j, tmp));
				arr[j] = tmp;
			}
			// 중요도 배열 역순으로 정렬하여 중요도가 큰 순으로 탐색
			// 해당 배열의 중요도는 겹쳐도 상관없다
			Arrays.sort(arr, Collections.reverseOrder());
			

			// 출력을 완료한 수이자 중요도 배열 index인 count 수 선언
			int count = 0;
			// queue가 빌때까지 탐색
			while(!queue.isEmpty()) {
				Print p = queue.poll();
				// 중요도가 가장 높다면
				if (p.value == arr[count]) {
					// 우선 출력을 했음으로 카운트를 늘린다
					count++;
					// 만약 우리가 찾는 값이라면 stringbuilder에 더해주고 탐색을 종료한다
					if (p.index == M) {
						sb.append(count + "\n");
						break;
					} 
					
				// 중요도가 가장 높지 않다면 다시 queue 맨 뒤로 넣어준다
				} else {
					queue.add(p);
				}
			}
			
		}
		System.out.print(sb);
		
		
	}

}

