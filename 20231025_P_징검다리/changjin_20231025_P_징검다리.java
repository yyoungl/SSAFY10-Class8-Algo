import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
		int rockSize = rocks.length;
		int[] Nrocks = new int[rockSize + 2];
		Nrocks[0] = 0;
		Nrocks[rockSize + 1] = distance;

		for (int i = 1; i <= rockSize; i++) {
			Nrocks[i] = rocks[i - 1];
		}

		int answer = 0;
		int min = 1;
		int max = distance / (rockSize - n + 1);
		while (min <= max) {
			int cnt = 0;
			int mid = (max + min) / 2;
			for (int i = 0; i < rockSize + 1; i++) {
				if (Nrocks[i + 1] - Nrocks[i] >= mid)
					continue;
				else {
					int increase = 1;
					while (true) {
						if (i + 1 + increase == rockSize + 2) {
							cnt += increase;
							i+=increase-1;
							break;
						}
						if (Nrocks[i + 1 + increase] - Nrocks[i] >= mid) {
							i += increase;
							cnt += increase;
							break;
						} else {
							increase++;
						}
					}
				}
			}
		
			if (cnt > n) {
				max = mid - 1;
			} else {
				answer = mid;
				min = mid + 1;
			}
		}
		return answer;
    }
}