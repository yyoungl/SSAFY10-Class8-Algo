import java.io.IOException;
import java.util.Scanner;

public class Gubeomlee_20230920_B_1464_1 {
	// 특정 인덱스에서 뒤집은 문자열을 구한다.
	public static String getReverseString(String input, int idx) {
		StringBuilder sb = new StringBuilder();
		for (int i = idx - 1; i >= 0; i--) {
			sb.append(input.charAt(i));
		}
		sb.append(input.substring(idx));
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(0) >= input.charAt(i)) {
				input = getReverseString(input, i);
				input = getReverseString(input, i + 1);
			}
		}
		System.out.println(input);
		sc.close();
	}
}