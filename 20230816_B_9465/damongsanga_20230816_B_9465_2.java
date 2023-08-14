import java.util.*;

public class damongsanga_20230816_B_9465_2 {

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();

            // 2칸 비교할 것으로 2칸 여유롭게 arr 생성
            int[][] arr = new int[2][N+2];
            for (int i = 0; i < 2; i++) {
                for (int j = 2; j < N+2; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // dp배열을 만들지 않고 풀이
            for (int i = 2; i < N+2; i++) {
                arr[0][i] += Math.max(arr[1][i-2], arr[1][i-1]);
                arr[1][i] += Math.max(arr[0][i-2], arr[0][i-1]);
            }

            System.out.println(Math.max(arr[0][N+1], arr[1][N+1]));
        }

    }

}

