import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    
    public int solution(String begin, String target, String[] words) {
        // 인접리스트를 저장할 배열 선언
        ArrayList<Integer>[] arr = new ArrayList[words.length + 1];
		int wordlen = begin.length();
        // 인접리스트 생성.
		for (int i = 0; i <= words.length; i++) {
			arr[i] = new ArrayList<Integer>();
		}
        // 타겟 문자가 저장되어 있는 위치를 저장할 변수 선언
		int answerIndex = 0;
        // begin문자와 words배열의 각 원소들을 비교해 한자리만 다르면 인접리스트 생성. 
		for (int i = 0; i < words.length; i++) {
			int cnt = 0;
			if (target.equals(words[i]))
				answerIndex = i + 1;
			for (int j = 0; j < wordlen; j++) {
				if (words[i].charAt(j) == begin.charAt(j))
					cnt++;
			}
			if (cnt == wordlen - 1) {
				arr[0].add(i + 1);
			}
		}
        // words의 나머지 원소들 끼리도 같은 작업 수행.
		for (int i = 0; i < words.length; i++) {

			for (int j = i + 1; j < words.length; j++) {
				int cnt = 0;
				for (int k = 0; k < wordlen; k++) {
					if (words[i].charAt(k) == words[j].charAt(k)) {
						cnt++;
					}
				}
				if (cnt == wordlen-1) {
					arr[i + 1].add(j + 1);
					arr[j + 1].add(i + 1);
				}
			}
		}
        // 원소들로 가는데 필요한 거리 저장할 배열 선언
		int[] Count = new int[words.length + 1];
		Queue<Integer> Q = new LinkedList<>();
        // 0번 인덱스(begin)에서 시작
		Q.add(0);
		L: while (!Q.isEmpty()) {
			int k = Q.poll();
			for (int i = 0; i < arr[k].size(); i++) {
				int temp = arr[k].get(i);
				if (Count[temp] != 0)
					continue;
				Count[temp] += Count[k] + 1;
				if (temp == answerIndex)
					break L;
				Q.add(temp);
			}

		}
	     return Count[answerIndex];
    }
}