// 옥상 정원 꾸미기 (stack 실행시간 950ms)

package _Baekjune;

import java.util.Scanner;
import java.util.Stack;

public class _6198_2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		//건물의 높이를 저장할 배열 선언.
		int[] arr=new int[n];
		
		Stack<Integer> stack=new Stack<Integer>();
		//배열에 건물 높이 값 저장.
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		//벤치마킹이 가능한 빌딩의 수를 저장할 변수 선언.
		//건물의 개수가 최대 80000이므로 80000개의 건물이 내림차순으로 있다면 대략  cnt 값은 80000*40000 32억으로 int자료형 범위인 21억을 초과하므로 long형 사용.
		long cnt=0;

		for(int i=0;i<n;i++) {
			// add 하는경우 -> 스택이 비어있거나, 스택의 peek가 현재 비교할 값보다 큰 경우.
			if(stack.isEmpty()||stack.peek()>arr[i]) {
				stack.add(arr[i]);
			}
			else {
				// 스택이 비어있지 않고, 스택의 peek가 현재 비교할 값보다 작으면 계속 pop해준다.
				while(!stack.isEmpty()&&stack.peek()<=arr[i]) {
					stack.pop();
				}
				// 현재 값 저장.
				stack.add(arr[i]);
			}
			//현재 저장한 값보다 아래에 있는 값들은 모두 현재값보다 크므로 현재값을 뺀 값들의 개수만큼 cnt에 더해준다. 
			cnt+=stack.size()-1;
		}
		System.out.println(cnt);
	}
}
