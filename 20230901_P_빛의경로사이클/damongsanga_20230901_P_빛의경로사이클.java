import java.util.*;

class damongsanga_20230901_P_빛의경로사이클 {
    static ArrayList<Integer> list;
    static int N;
    static int M;
    static int[] rr = { -1, 0, 1, 0 }; // 우 하 좌 상
    static int[] rc = { 0, 1, 0, -1 };

    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        char[][] arr = new char[N][grid[0].length()];

        for (int i = 0; i < N; i++) {
            char[] tmp = grid[i].toCharArray();
            for (int j = 0; j < M; j++) {
                arr[i][j] = tmp[j];
            }
        }

        // 각 격자의 4방향에 대한 visited 배열 생성
        boolean[][][] visited = new boolean[N][M][4];
        // 답을 저장할 list 생성
        list = new ArrayList<>();

        // 모든 격자의 4방향 모두 탐색하여 방문하지 않았을 때 findCycle
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    if (!visited[i][j][k]) {
                        findCycle(new int[] { i, j }, new int[] { i, j }, k, k, 1, arr, visited);
                    }
                }
            }
        }

        // 답 출력
        Collections.sort(list);
        int[] answer = new int[list.size()];
        int count = 0;
        for (Integer i : list) {
            answer[count++] = i;
        }

        return answer;
    }

    // 재귀를 쓰니까 런타임에러 발생해서 반복문으로 변경함
    static void findCycle(int[] srt, int[] now, int srt_dir, int dir, int length, char[][] arr, boolean[][][] visited) {

        int r = now[0]; // 지금의 row 값
        int c = now[1]; // 지금의 column 값
        visited[r][c][dir] = true; // 방문처리

        // 이동
        r = (r + rr[dir] + N) % N;
        c = (c + rc[dir] + M) % M;
        now = new int[] { r, c };
        // 방향 갱신
        dir = (dir + findDirection(arr[r][c]) + 4) % 4;

        // 사이클을 이룰 때까지 반복
        while (!(srt[0] == now[0] && srt[1] == now[1] && dir == srt_dir)) {
            r = now[0];
            c = now[1];
            visited[r][c][dir] = true;

            r = (r + rr[dir] + N) % N;
            c = (c + rc[dir] + M) % M;
            now = new int[] { r, c };
            dir = (dir + findDirection(arr[r][c]) + 4) % 4;
            length++;
        }

        list.add(length);
        return;

    }

    // 도착했을 때 방향 변환함수
    static int findDirection(char c) {
        switch (c) {
            case ('S'):
                return 0;
            case ('L'):
                return -1;
            case ('R'):
                return 1;
        }
        return -2;
    }
}

