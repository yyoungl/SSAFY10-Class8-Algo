package Algorithm.algorithm.programmers;

import java.util.ArrayList;

public class Jwon_20230821_P_조이스틱 {
	public static int solution(String name) {
		// A가 아닌 문자로 바꾸기 위해서 움직여야할 조이스틱 움직임 횟수.
		int change = 0;
		for(int i = 0 ; i < name.length(); i++) {
			// 문자는 A~Z만 있기 때문에 ascii code값을 이용하였다.
			change += Math.min(name.charAt(i) - 65, 91 - name.charAt(i));
		}
		
		// name을 뒤집었을 때 문자 A를 넣어줄 것이다. 이 때 원래의 길이가 변경되기 때문에 저장해준다.
		int len = name.length();
		System.out.println("name : " + name);
		
		// 뒤집기 전 문자열의 좌우 거리 이동 조이스틱 움직임 횟수를 구해준다.
		int distance = find(name,len);
		
		// 뒤집었을 때 첫번째의 이동거리는 1이다. 이는 첫 번째에 A가 있는 것과 같으므로 A를 더해준 후 뒤집어준다.
		StringBuilder sb = new StringBuilder(name);
		sb.append("A");
		sb.reverse();
		name = sb.toString();
		
		// 뒤집었을 때의 좌우 거리 이동 조이스틱 움직임 횟수를 구한 후 더 작은 값을 구해준다.
		distance = Math.min(distance, find(name,len));
		System.out.println("change : " + change);
		System.out.println("distance : " + distance );
		
		// 문자를 바꾼 횟수와 그 문자로 이동한 횟수를 더해준다.
		return change + distance;
	}
	
	private static int find(String name, int len) {
		
		// A가 아닌 문자의 index를 넣어줄 list.
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < name.length(); i++) {
			if(name.charAt(i) != 'A') {
				list.add(i);
			}
		}
		
		// 원래 문자열의 길이를 최솟값으로 정해놓는다. 
		// (name.length()를 하면 뒤집었을 때 하나가 더 많아지므로 solution 메서드에서 구한 len을 이용한다.)
		int min = len;
		// 만약 모두 A라면 (모두 A면 A가 아닌 문자의 index를 넣어줄 list의 사이즈는 0일 수밖에 없다.) 움직일 필요가 없으므로 0으로 초기화한다.
		if(list.size() == 0) {
			min = 0;
		}
		for(int i = 0 ; i < list.size(); i++) {
			int temp = 0;
			// 왼쪽에서 i번째 A가 아닌 문자의 index를 구한다. 이는 거리가 될 것이다.
			// 만약 i가 A가 아닌 문자의 index list의 마지막 index라면 리턴할 필요가 없으므로 
			// temp는 곧 list.get(i)가 된다.
			if(i == list.size()-1) {
				temp += list.get(i);
			}
			// i가 index list의 마지막 index가 아니라면 리턴을 해준다.
			else {
				// 리턴을 하게 되면 이 거리는 두배가 된다.
				temp += list.get(i) * 2;
				// 리턴 후 뒤로 거리를 확인한다.
				// 이는 i보다 하나 더 많은 index에 위치한 A가 아닌 문자까지 가야한다.
				temp += len - list.get(i+1);
			}
			// 최솟값을 구해준다.
			min = Math.min(min, temp);
		}
		
		return min;
	}
	
	public static void main(String[] args) {
		// test case
		
		System.out.println(solution("JEROEN"));
		System.out.println(solution("JAN"));
		System.out.println(solution("AAA"));
		System.out.println(solution("BAA"));
		System.out.println(solution("ABA"));
		System.out.println(solution("AAB"));
		System.out.println(solution("BBB"));
		System.out.println(solution("ABB"));
		System.out.println(solution("BAB"));
		System.out.println(solution("BBA"));
		System.out.println(solution("ZZZ"));
		System.out.println(solution("AZZ"));
		System.out.println(solution("ZAZ"));
		System.out.println(solution("ZZA"));
		System.out.println(solution("ZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ"));
		System.out.println(solution("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ"));
		System.out.println(solution("AAAAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ"));
		System.out.println(solution("AAAAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZA"));
	}
}
