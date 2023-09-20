package Algorithm.algorithm.baekjoon;

import java.util.Scanner;

public class Jwon_20230920_B_1464_뒤집기3 {
	public static void main(String[] args) {
		String str = (new Scanner(System.in)).next();
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		// 맨 처음에 있는 놈이 제일 작아야 한다.
		// 새로 들어오는 놈이 맨 처음에 있는 놈보다 작거나 같으면
		// 뒤집고 뒤집는다.
		// 끝
		for (int i = 1; i < str.length(); i++) {
			if(sb.toString().charAt(0) >= str.charAt(i)) {
				sb.reverse();
				sb.append(str.charAt(i));
				sb.reverse();
			}else {
				sb.append(str.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}
}
