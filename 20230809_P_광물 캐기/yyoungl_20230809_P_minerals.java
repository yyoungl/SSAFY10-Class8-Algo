import java.util.Arrays;
import java.util.Collections;

public class yyoungl_20230809_P_minerals {
	static String[] minerals = { "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond" };
	static int[] picks = { 0, 1, 1 };
	// 여기서 input 바꿔가면서 풀어 보았습니다...

	public static void main(String[] args) {
		int tired = 0;
		// 쓸 수 있는 전체 곡괭이의 수를 구했어요
		int pickSum = 0;
		for (int pick : picks)
			pickSum += pick;

		// 그리고 곡괭이로 캘 수 있는 최대 광물 개수가 주어진 광물의 개수보다 더 작을 수도 있기 때문에 배열을 초기화해주기 위해 len을 초기화했습니다..
		int len;
		if (pickSum*5 < minerals.length) {
			len = pickSum*5;
		} else {
			len = minerals.length;
		}

		// 찐_최종_미네랄.java
		String[] mineralFinal = new String[len];
		for (int pick =0 ; pick <len; pick++) {
			mineralFinal[pick] = minerals[pick];
		}

		// 곡괭이 하나로 캘 수 있는 광물의 구간 (5씩 끊음)
		Integer[] value = new Integer[pickSum];
		Arrays.fill(value, 0);
		
		// 한 곡괭이로 5개를 캘 수 있고 어떤 조합이 나와도 다이아몬드, 철, 돌의 개수를 알 수 있기 때문에 거스름돈 문제와 같은 방법으로 100 / 10 / 1 의 가치를 두었고

		int i = 0;
		int idx = 0;
		while (i < len) {
			switch (mineralFinal[i]) {
			case "diamond":
				value[idx] += 100;
				break;
			case "iron":
				value[idx] += 10;
				break;
			case "stone":
				value[idx] += 1;
			}
			if (i % 5 == 4)
				idx++;
			i++;
		}
		// 가장 많은 가치 - 피로도 가 있는 광물을 가장 좋은 곡괭이로 캐내야 하기 때문에 내림차순 정렬
		// ㅠㅠ 여기서 곡괭이로 모든 광물을 캘 수 없는 경우가 발생했다는 걸 알고 처음으로 다시 돌아가 minerals 배열을 잘라 주었음
		Arrays.sort(value, Collections.reverseOrder());

		int index = 0;
		for (int j = 0; j < 3; j++) {
			while (picks[j] > 0) {
				// 지금 사용하는 곡괭이가 무엇인지에 따라서 피로도 누적 정도가 다름
				if (j == 0) {
					// 다이아몬드
					tired += value[index] / 100;
					value[index] %= 100;
					// 철
					tired += value[index] / 10;
					value[index] %= 10;
					// 돌
					tired += value[index];
					value[index] %= 1;

				} else if (j == 1) {
					tired += (value[index] / 100) * 5;
					value[index] %= 100;
					tired += value[index] / 10;
					value[index] %= 10;
					tired += value[index];
					value[index] %= 1;

				} else if (j == 2) {
					tired += (value[index] / 100) * 25;
					value[index] %= 100;
					tired += (value[index] / 10) * 5;
					value[index] %= 10;
					tired += value[index];
					value[index] %= 1;

				}
				// 광물을 모두 다 캔 후 사용한 곡괭이 -1
				picks[j] -= 1;
				
				// 광물을 다 캤다면 다음 5개를 캐러 ㄱㄱ~ 이것 때문에 마지막에 %=1 연산을 해줌
				if (value[index] == 0)
					index++;
			}
		}
		System.out.println(tired);

	}

}
