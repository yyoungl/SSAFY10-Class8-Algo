package Algorithm.algorithm.baekjoon;

import java.util.Scanner;

public class Jwon_20230825_B_11723_집합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 비트마스크를 사용하였다.
		int test = sc.nextInt();
		int bitMask = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < test; i++) {
			String str = sc.next();
			if(str.equals("all")) {
				bitMask = (int)Math.pow(2, 21) - 1;
				continue;
			}else if(str.equals("empty")) {
				bitMask = 0;
				continue;
			} 
			int num = sc.nextInt();
//			int subSet = (int)Math.pow(2, num) ;
			int subSet = 1 << num;
			if (str.equals("add")) {
				bitMask = bitMask | subSet;
			} else if (str.equals("check")) {
				if ((bitMask & subSet) > 0) {
					sb.append(1);
				} else {
					sb.append(0);
				}
				sb.append("\n");
			} else if (str.equals("remove")) {
				bitMask = bitMask & (~subSet);
			} else if(str.equals("toggle")) {
				bitMask = bitMask ^ subSet;
			}
		}
		System.out.println(sb.toString());
	}
}
