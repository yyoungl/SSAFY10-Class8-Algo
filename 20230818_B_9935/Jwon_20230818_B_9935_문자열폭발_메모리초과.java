package Algorithm.algorithm.baekjoon;

import java.util.Scanner;

public class Jwon_20230818_B_9935_문자열폭발_메모리초과 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		String bumbStr = sc.next();
		char[] strArr = str.toCharArray();
		char[] bumbArr = bumbStr.toCharArray();
		while(str.contains(bumbStr)) {
			boolean tf = true;
			int tempIdx = 0;
			for(int i = 0 ; i + bumbArr.length <= strArr.length; i++) {
				tf = true;
				for(int j = 0 ; j < bumbArr.length ; j++) {
					if(strArr[i+j] != bumbArr[j]) {
						tf = false;
						break;
					}
				}
				if(tf) {
					tempIdx = i;
					break;
				}
			}
			if(tf) {
				StringBuilder sb = new StringBuilder();
//				System.out.println("origin : " + str);
				sb.append(str.substring(0, tempIdx));
//				System.out.println("first substring : " + str.substring(0, tempIdx));
				sb.append(str.substring(tempIdx+bumbArr.length));
//				System.out.println("second substring : " + str.substring(tempIdx+bumbArr.length));
				str = sb.toString();
				strArr = str.toCharArray();
//				System.out.println("str : " + str);
//				System.out.println("bumbStr : " + bumbStr);
//				System.out.println(str.contains(bumbStr));
			}
		}
		if(str.length() == 0) {
			str = "FRULA";
		}
		System.out.println(str);
	}
}
