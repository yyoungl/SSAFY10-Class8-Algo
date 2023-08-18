import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class B9935_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder originStr = new StringBuilder();
		String inputS = sc.next();

		String pung = sc.next();
		// 이전 풀이에서는 originStr을 먼저 만들고 뒤에서부터 봤지만
		// 한 글자씩 더해주면서 마지막 2글자 확인 -> 되면 펑 -> 안 되면 하나 추가 -> 또 되면 펑 하는 방식으로 했다
		for (int i = 0; i < inputS.length(); i++) {
			originStr.append(inputS.charAt(i));

            int len = originStr.length();
            if (len >= pung.length()) {
            	// pung 의 길이 이상으로 붙어있을 때 확인
                String check = originStr.substring(len - pung.length(), len);
//                System.out.println(originStr);
                if (check.equals(pung)) {
                    // pung의 길이를 뒤에서 제외한 나머지 잘라주기
                	originStr.setLength(len - pung.length());
//                	System.out.println("펑: "+originStr);
                }
            }
        }

        if (originStr.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(originStr.toString());
		}

	}

}
