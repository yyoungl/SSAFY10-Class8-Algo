package Solution;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	static ArrayList<Integer> arr = new ArrayList<>();
	// numbers 인풋이 1 이상 7 이하 숫자이기 때문에 7칸으로 초기화
	static boolean check[] = new boolean[7];
	public int solution(String numbers) {
		// TODO Auto-generated method stub
		int answer = 0;
		
		for (int i=0; i<numbers.length(); i++) {
			dfs(numbers,"", i+1);
		}
		
		for (int i=0; i<arr.size(); i++) {
			if (prime(arr.get(i))) answer++;
		}
		
		return answer;
	}
	// numList에서 나올 수 있는 
	static void dfs(String str, String temp, int m) {
		if (temp.length() == m) {
			int num = Integer.parseInt(temp);
			if(!arr.contains(num)) arr.add(num);
		}
		
		for (int i=0; i<str.length(); i++) {
			if (!check[i]) {
				check[i] = true;
				temp += str.charAt(i);
				dfs(str, temp, m);
				check[i] = false;
				temp = temp.substring(0, temp.length()-1);				
			}
		}
	}

	static boolean prime(int n) {
		if (n < 2) {
			return false;
		} else {
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0)
					return false;
			}
			return true;
		}
	}
}