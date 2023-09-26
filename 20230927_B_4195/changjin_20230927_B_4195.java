package _Baekjune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 친구네트워크 {

	static String s1, s2;
	static int N, max;
	static int[] arr, value;

	// find 메서드 정의
	static int find(int x) {
		if (arr[x] == x)
			return x;
		return arr[x] = find(arr[x]);
	}

	// union 메서드 정의 인덱스가 작은 것을 대표로 한다.
	static void union(int a, int b) {
		int a1 = find(a);
		int b1 = find(b);
		if (a1 > b1)
			arr[a1] = b1;
		else
			arr[b1] = a1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			// 해시맵 선언.
			HashMap<String, Integer> hMap = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// 최대 10만개의 쌍이 들어오므로 최대 데이터 개수인 20만까지의 값을 수용할 배열 선언
			arr = new int[200001];
			value = new int[200001];
			// value 배열에 친구관계 수를 저장할 것이므로 처음에는 1로 초기화.
			Arrays.fill(value, 1);

			max = 1;
			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				// 두 명의 사람 이름을 저장.
				s1 = st.nextToken();
				s2 = st.nextToken();
				// 둘 모두 hmap에 저장되어 있지 않은 경우.
				if (!hMap.containsKey(s1) && !hMap.containsKey(s2)) {

					// 두 이름 모두 hmap에 저장. Value의 값으로 각 이름을 구별하기 위해 hMap size를 value 값으로 함
					hMap.put(s1, hMap.size());
					hMap.put(s2, hMap.size());

					// 유니온 파인드를 위해 배열의 값을 자기 자신으로 채워준다.
					arr[hMap.get(s1)] = hMap.get(s1);
					arr[hMap.get(s2)] = hMap.get(s2);
					// 두 값을 유니온
					union(hMap.get(s1), hMap.get(s2));
					// 두 값을 유니온 한 후 대표값의 value를 1 증가시킴
					value[find(hMap.get(s1))]++;

					max = value[find(hMap.get(s1))];

				} else if (hMap.containsKey(s1) && !hMap.containsKey(s2)) {
					hMap.put(s2, hMap.size());

					arr[hMap.get(s2)] = hMap.get(s2);

					union(hMap.get(s1), hMap.get(s2));

					value[find(hMap.get(s1))]++;

					max = value[find(hMap.get(s1))];
				} else if (!hMap.containsKey(s1) && hMap.containsKey(s2)) {
					hMap.put(s1, hMap.size());
					arr[hMap.get(s1)] = hMap.get(s1);
					union(hMap.get(s1), hMap.get(s2));
					value[find(hMap.get(s1))]++;

					max = value[find(hMap.get(s1))];
				} else {
					if (find(hMap.get(s1)) != find(hMap.get(s2))) {
						int sum = value[find(hMap.get(s1))] + value[find(hMap.get(s2))];
						union(hMap.get(s1), hMap.get(s2));
						value[find(hMap.get(s1))] = sum;
						max = value[find(hMap.get(s1))];
					} else
						max = value[find(hMap.get(s1))];
				}
				sb.append(max).append("\n");
			}
		}
		System.out.println(sb);
	}
}
