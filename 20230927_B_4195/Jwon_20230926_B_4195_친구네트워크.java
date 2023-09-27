package Algorithm.algorithm.baekjoon;

import java.util.HashMap;
import java.util.Scanner;

public class Jwon_20230926_B_4195_친구네트워크 {
	static HashMap<String, String> map;
	static HashMap<String, Integer> countMap;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		for (int testCase = 0; testCase < test; testCase++) {
			// union을 하기 위한 map
			map = new HashMap<>();
			// count를 세기 위한 map
			countMap = new HashMap<>();
			int f = sc.nextInt();
			for (int i = 0; i < f; i++) {
				String friend1 = sc.next();
				String friend2 = sc.next();
				// 친구 1과 친구 2를 합친다.
				makeUnion(friend1, friend2);
				System.out.println(countMap.get(find(map.get(friend1))));
			}
		}
	}

	private static void makeUnion(String friend1, String friend2) {
//		for(int i = 1 ; i <= n ; i++){
//			arr[i] = i;
//		}와 같이 현재 노드를 가장 최상단 노드로 해준다.
		if (!map.containsKey(friend1)) {
			map.put(friend1, friend1);
		}
		if (!map.containsKey(friend2)) {
			map.put(friend2, friend2);
		}

		String from = find(friend1);
		String to = find(friend2);

		// 현재 friend1과 friend2가 이미 같은 집합에 있는 지 확인.
		// 이 때 새로들어온 것들은 getordefault(1)로 해주었기 때문에 1이 들어간다.
		if (!from.equals(to)) {
			countMap.put(to, countMap.getOrDefault(from, 1) + countMap.getOrDefault(to, 1));
			// 합쳐주기
			map.put(from, to);
		}

	}

	private static String find(String friend) {
		if (map.get(friend).equals(friend)) {
			return friend;
		}
		map.put(friend, find(map.get(friend)));
		return map.get(friend);
	}
}
