
public class yyoungl_20230821_P_���̽�ƽ {
	static String str1 = "JEROEN";
	static String str2 = "JAN";
	static String str3 = "JAAVMAAAI";

	public static void main(String[] args) {
		System.out.println(solution(str1));
		solution(str2);
		solution(str3);

	}

	// A�� �ִ��� ������ �߿��� �� ����.
	// ��� ���ڰ� A�� ���¿��� �����ϱ� ������ A�� �ƴ� ���ڷδ� ������ �̵��ؾ� �ϱ� ������ A�� �����ϰų� / A�� ������ A�� ���� �̵����� �ʴ� ���

	static public int solution(String name) {
		int answer = 0;
		int len = name.length();
		char[] nameArr = name.toCharArray();

		// ���� �ٲٴ� ������ ȸ��
		for (int i = 0; i < len; i++) {
			int diff = nameArr[i] - 'A';
			// ASCII �ڵ� ���̰��� �������� ���Ʒ� �����̴� ȸ�� �����ֱ�, A���� �ڷ� ���� �� Ƚ���� ���� ������ ���� 26-diff�� ����� �� �ֵ��� �Ѵ�
			answer += Math.min(diff, 26 - diff);
		}

		// ������ �ڸ��� �̵��ϴ� ������ ȸ��
		int min = len - 1;
		
		for (int i = 0; i < len; i++) {
			int index = i + 1;
			// ������ ���ڵ��� ���캻��.
			// ���� �����ʿ� A�� �ְ�, �迭 ���� ����� �ʴ´ٸ�
			while (index < len && nameArr[index] == 'A') {
				// ���ӵ� A�� ������ �ε����� ������� ������ �ֱ�
				index++;
//				System.out.println("i: " + index);
			}
			
			// index�� i (���� ��ġ) ���̰� 1���� ũ��!! (���ӵ� A�� �ִٸ�) 
			// ��� ��쿡 ���� ������ �ص� ������ �׳� A�� ���ӵ� ��쿡�� min�� �ʱ�ȭ�ߴ�...  
			
//			�� ���� ��찡 �ִ�.
//			1. A�� �����ϰ� ���캸�� ��� len-1
//			2. ������ ���ư���, �ں��� ���ӵ� A�� ������ �������� ���캸�� ��� 2i+(len-index)
//			3. ó������ ������ ���ں��� ���캻 �� ������ ���ƿ��� ��� i+2(len-index)
			if (index - i > 1) {
				min = Math.min(min, Math.min(2 * (len - index) + i, 2 * i + len - index));
			}
		}
		return answer + min;

	}

}