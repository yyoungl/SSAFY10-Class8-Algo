import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class damongsanga_20230804_B_9663 {
    static int answer = 0;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N]; // i 번째 세로줄에 있는 퀸의 높이 = arr[i]
        answer = 0;

        backtracking(0);

        System.out.println(answer);


    }

    public static void backtracking(int depth) {

        // 총 N번 들어갔다면 answer++ 후 return
        if (depth == N) {
            answer++;
            return;
        }

        // arr값을 for 문을 돌리면서 설정, queen을 놓을 수 있을 때만 추가 탐색
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (queenable(depth)){
                backtracking(depth + 1);
            }

        }
    }

    // queen을 놓을 수 있는지 판단
    public static boolean queenable (int depth){

        // 기본값 true로 설정
        boolean answer = true;

        // 현재 깊이까지 queen을 놓을 수 있는지 판단
        for (int i = 0; i < depth; i++) {
            int diff = Math.abs(arr[depth] - arr[i]); // 현재 queen과 검사할 queen의 높이 차이
            if (diff == depth - i || diff == 0){ // 같은 가로에 있거나 (높이가 같거나) 대각선에 있는 경우 제외
                answer = false;
                return answer;
            }
        }
        return answer;
    }

}
