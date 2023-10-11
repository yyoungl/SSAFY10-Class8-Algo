import java.util.*;

public class damongsanga_20231013_P_단어변환 {

    static class Word{
        int idx, len;
        Word(int idx, int len){
            this.idx = idx;
            this.len = len;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int wl = target.length();
        int N = words.length;

        // begin, target이 words 안에 있는지 확인
        int newN = N+1;
        int stratIdx = N;
        boolean flag = false;
        int targetIdx = -1;

        for (int i = 0; i < N; i++) {
            if (words[i].equals(begin)) {
                stratIdx = i;
                newN = N;
            }
            if (words[i].equals(target)) {
                flag = true;
                targetIdx = i;
            }
        }
        if (!flag) return 0; // 만약 target이 word 안에 없으면 바로 0 리턴

        boolean[][] table = new boolean[newN][newN];
        String[] newWords = new String[newN];
        System.arraycopy(words, 0, newWords, 0, N);
        if (newN == N+1) newWords[N] = begin;

        for (int i = 0; i < newN; i++) {
            for (int j = i+1; j < newN; j++) {
                String A = newWords[i];
                String B = newWords[j];
                int count = 0;
                for (int k = 0; k < wl; k++) {
                    if (A.charAt(k) != B.charAt(k)) count++;
                }
                if (count == 1) { // 1개 글자를 바꿔서 변할 수 있으면 양방향에 대해서 true;
                    table[i][j] = true;
                    table[j][i] = true;
                }
            }
        }

        // BFS
        Queue<Word> queue = new ArrayDeque<>();
        queue.add(new Word(stratIdx, 0));
        boolean[] visited = new boolean[newN];
        visited[stratIdx] = true;

        while(!queue.isEmpty()){
            Word now = queue.poll();
            // 타겟이면 변화 횟수 리턴
            if(now.idx == targetIdx) {
                answer = now.len;
                break;
            }
            for (int i = 0; i < newN; i++) {
                if (visited[i]) continue;
                if (!table[now.idx][i]) continue;
                visited[i] = true;
                queue.add(new Word(i, now.len+1));
            }
        }
        return answer;
    }
}