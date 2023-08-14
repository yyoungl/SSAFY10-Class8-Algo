package Algorithm.algorithm.baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class Jwon_20230817_B_6198_옥상정원꾸미기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		// 거리를 인덱스의 차이로 구할 것이고 인덱스에 대한 건물의 높이를 구하기 위해 배열에 값 넣기.
		for(int i = 0 ; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		
		
		Stack<Integer> stack = new Stack<>();
		
		// 스택에 있는 값과 새로운 값을 기준으로 확인하기 때문에 첫번째 값은 넣어두기.
		stack.push(0);
		
		// 문제에 빌딩의 높이가 1,000,000,000이고 n이 80000이므로 int 범위를 넘을 수 있으므로 long으로 지정.
		long sum = 0;
		for(int i = 1 ; i < arr.length; i++) {
			
			// 만약 다음 빌딩이 stack에 있는 가장 오른쪽에 있는 빌딩보다 크다면 stack에 있는 가장 오른쪽에 있는 빌딩은 더 이상 볼 수 있는 옥상이 없다.
			while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				// 더 이상 stack에 있는 가장 오른쪽에 있는 빌딩이 확인할 옥상은 없으므로 stack에 있을 필요 없다.
				// 따라서 pop하고 볼 수 있는 옥상의 수를 확인한다.
				long temp = i - stack.pop() - 1;
				sum += temp;
			}
			// 새로운 빌딩이 볼 수 있는 옥상의 수를 확인하기 위해 스택에 넣는다.
			stack.push(i);
		}
		
		// 남아 있는 빌딩들이 확인할 수 있는 옥상 수를 찾아준다.
		while(!stack.isEmpty()) {
			long temp = n - stack.pop() - 1;
			sum += temp;
		}
		System.out.println(sum);
	}
}
