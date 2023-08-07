package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jwon_20230807_B_1966_printQueue {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		for(int testCase = 0 ; testCase < test ; testCase++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// index를 넣을 queue
			Queue<Integer> queue = new LinkedList<>();
			// 중요도 순서로 빼기 위한 priority queue
			// Collections.reverseOrder()를 이용하여 큰 수를 우선순위로 둔다.
			PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
			st = new StringTokenizer(br.readLine());
			
			// queue에서 나온 인덱스를 이용하여 실제 수에 접근하기 위한 arr
			int[] arr = new int[n];
			for(int i = 0 ; i < n ; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				// 몇 번째에 있는 숫자인 지를 arr에 넣는다.
				arr[i] = num;
				
				// 중요도 순서로 빼기위한 priority queue에는 중요도를 넣는다.
				pQueue.offer(num);
				
				// arr에 index에 접근하기 위해 index를 넣는다. ( 몇 번째 배열에 있는 수인 지 확인하고 arr에 접근하기 위함 )
				queue.offer(i);
			}
			
			// 몇 번째에 있는 수인 지 확인.
			int count = 0;
			while(!queue.isEmpty()) {
				// 여기서는 무조건 queue에 하나가 뽑히기 때문에 count++
				count++;
				// 현재 가장 중요도가 높은 숫자를 max에 넣고
				int max = pQueue.poll();
				// queue에 있는 index를 idx에 넣고
				int idx = queue.poll();
				
				// 현재 idx에 있는 숫자가 max와 같은 지 확인하고 다르다면 맨 마지막에 넣고 그 다음 수를 뽑아서 확인한다.
				while(arr[idx] != max) {
					queue.offer(idx);
					idx = queue.poll();
				}
				
				// 원래 찾고 싶던 index와 현재에 idx가 같은 지 확인하고 같다면 while문 끝.
				if(idx == m) {
					break;
				}
			}
			
			// 빠끄.
			System.out.println(count);
			
		}
	}
}
