
public class yyoungl_20230821_P_조이스틱 {
	static String str1 = "JEROEN";
	static String str2 = "JAN";
	static String str3 = "JAAVMAAAI";

	public static void main(String[] args) {
		System.out.println(solution(str1));
		solution(str2);
		solution(str3);

	}

	// A가 있는지 없는지 중요한 것 같다.
	// 모든 문자가 A인 상태에서 시작하기 때문에 A가 아닌 문자로는 무조건 이동해야 하기 때문에 A로 시작하거나 / A로 끝나서 A로 절대 이동하지 않는 방법

	static public int solution(String name) {
		int answer = 0;
		int len = name.length();
		char[] nameArr = name.toCharArray();

		// 문자 바꾸는 연산의 회수
		for (int i = 0; i < len; i++) {
			int diff = nameArr[i] - 'A';
			// ASCII 코드 차이값을 바탕으로 위아래 움직이는 회수 더해주기, A부터 뒤로 가는 게 횟수가 적은 문자의 경우는 26-diff로 적용될 수 있도록 한다
			answer += Math.min(diff, 26 - diff);
		}

		// 문자의 자리를 이동하는 연산의 회수
		int min = len - 1;
		
		for (int i = 0; i < len; i++) {
			int index = i + 1;
			// 오른쪽 문자들을 살펴본다.
			// 만약 오른쪽에 A가 있고, 배열 밖을 벗어나지 않는다면
			while (index < len && nameArr[index] == 'A') {
				// 연속된 A가 끝나는 인덱스가 어디인지 가지고 있기
				index++;
//				System.out.println("i: " + index);
			}
			
			// index와 i (현재 위치) 차이가 1보다 크면!! (연속된 A가 있다면) 
			// 모든 경우에 대해 연산을 해도 되지만 그냥 A가 연속된 경우에만 min을 초기화했다...  
			
//			세 가지 경우가 있다.
//			1. A를 무시하고 살펴보는 경우 len-1
//			2. 앞으로 돌아가서, 뒤부터 연속된 A의 마지막 직전까지 살펴보는 경우 2i+(len-index)
//			3. 처음부터 마지막 글자부터 살펴본 뒤 앞으로 돌아오는 경우 i+2(len-index)
			if (index - i > 1) {
				min = Math.min(min, Math.min(2 * (len - index) + i, 2 * i + len - index));
			}
		}
		return answer + min;

	}

}