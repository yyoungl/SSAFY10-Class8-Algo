package Programmers;

public class 조이스틱 {
	static String name="JAN";
	public static void main(String[] args) {
		// name의 문자 중 A가 아닌 문자의 개수를 저장할 cnt변수 생성. name의 0번 인덱스는 A가 맞든 아니든 시작지점이므로 cnt=1로 초기화
		int cnt=1;
		int answer2=0;
		// name의 1번 인덱스부터 끝까지 각 알파뱃을 만들기 위해 조이스틱을 움직여야 하는 횟수 계산
		for(int i=1;i<name.length();i++) {
			if(name.charAt(i)!='A') {
				// A부터 시작해 정항향으로 움직이는 경우와 역방향으로 움직이는 경우를 계산해 작은값을 더해준다.
				answer2+=Math.min(name.charAt(i)-'A', 'Z'+1-name.charAt(i));
				cnt++;
			}
		}
		// name의 0번 인덱스의 경우도 더해준다. 
		answer2+=Math.min(name.charAt(0)-'A', 'Z'+1-name.charAt(0));
		// 0번 인덱스부터 순방향으로 갈 때 각 A가 아닌 문자까지 가는데 걸리는 거리를 저장할 arr배열과 역방향으로 돌때 각 A가 아닌 문자까지 가는데 걸리는 거리를 저장할 rarr배열 생성.
		int[] arr=new int[cnt];
		int[] rarr=new int[cnt];
		// 이미 0번에서 시작하므로 idx는 1에서 시작.
		int idx=1;
		// for 문을 돌며 arr배열에는 A가 아닌 문자가 나올때마다 순방향으로 인덱스를 저장하고
		// rarr에는 A가 아닌 문자가 나올 떄 역방향으로 인덱스를 저장한다. 이때 역방향으로 저장할 떄는 -를 붙여 저장한다.
		for(int i=1;i<name.length();i++) {
			if(name.charAt(i)!='A') {
				arr[idx]=i;
				rarr[arr.length-idx]=-name.length()+i;
				idx++;
			}
		}
		// A가아닌 각 문자를 최단 루트로 방문할 때 조이스틱을 움직여야 하는 횟수를 저장할 answer배열 선언. 
		int answer=Integer.MAX_VALUE;
		// 먼저 정방향으로 출발 할때 최소값 탐색
		for(int i=0;i<arr.length;i++) {
			if(i==arr.length-1) answer=Math.min(answer, arr[i]);
			else answer=Math.min(answer,arr[i]*2-rarr[arr.length-1-i]);
		}
		// 역방향으로 출발할때 최소값 탐색.
		for(int i=0;i<arr.length;i++) {
			if(i==arr.length-1) answer=Math.min(answer, -rarr[i]);
			else answer=Math.min(answer,-(rarr[i]*2-arr[arr.length-1-i]));
		}
		// 앞서 구한 각 A에서 각문자로 가장 빨리 이동하는 값과 최소탐색 거리를 더해준다.
		System.out.println(answer+answer2);
		
	}
}
