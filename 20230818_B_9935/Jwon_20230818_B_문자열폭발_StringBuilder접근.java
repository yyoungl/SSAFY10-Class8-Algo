package Algorithm.algorithm.baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jwon_20230818_B_문자열폭발_StringBuilder접근 {

	// 8개월전에 열었던 판도라
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String explosion = br.readLine();
		
		// StringBuilder에 바로 넣어서 확인과 값까지 한꺼번에 만들었다.
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < str.length(); i++) {
			// 우선 스택처럼 맨 마지막에 문자를 넣어준다.
			sb.append(str.charAt(i));
			// 만약 Stringbuilder가 폭발할 문자열의 크기보다 같거나 크다면
			if(sb.length() >= explosion.length()) {
				// 폭발할 문자열이 있는 지 확인하기 위한 boolean
				boolean tf = true;

				for(int j = 0 ; j < explosion.length() ; j++) {
					// 스택처럼 가장 최근에 넣은 값들이 문자열 폭발인 지 확인한다.
					if(sb.charAt(sb.length()-explosion.length()+j) != explosion.charAt(j)) {
						tf = false;
						break ;
					}
				}
				// 만약 문자열 폭발이라면 delete 해준다.
				if(tf) sb.delete(sb.length()-explosion.length(), sb.length());
			}
		}
		
		// 메모리도 그렇고 시간도 그렇고 StringBuilder를 사용하는 것이 훨씬 효율적인 방법인 것 같다.
		// 스택을 이용할 땐 이미 뺀 것들을 다시 넣어주느라 시간이 좀 걸렸는데 StringBuilder는 그럴 필요가 없기 때문인 것 같다.
		if(sb.length() == 0) System.out.println("FRULA");
		else System.out.println(sb);
		
	}
}


