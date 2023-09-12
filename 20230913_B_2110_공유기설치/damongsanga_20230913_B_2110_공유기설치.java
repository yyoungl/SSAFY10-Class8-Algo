import java.util.Arrays;
import java.util.Scanner;

public class damongsanga_20230913_B_2110_공유기설치 {
    static int[] arr;
    static int N;
    static int M;

    // 주요 아이디어 : 집의 좌표 C가 10억으로 O(logC), 집의 개수 N은 O(N)까지 가능하다
    // 따라서 인접한 두 공유기의 최대값을 미리 설정하고 (이분탐색 > O(logC)) 이 조건이 성립하는지 역으로 찾아보면 (슬라이딩 윈도우 > O(N)) 되지 않을까?

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        // 가장 인접한 공유기의 최대 거리 범위
        int srt = 0;
        int end = arr[N-1] - arr[0];

        // 가장 인접한 공유기 이분탐색
        int answer = 0;
        while(srt <= end){
            int mid = (srt + end) / 2;
            if (possible(mid)) {
                answer = mid;
                srt = mid + 1;
            }
            else end = mid-1;
        }
        System.out.println(answer);

    }

    // 슬라이딩 윈도우
    // 맨 왼쪽 집에는 공유기를 설치하고 mid 값보다 멀거나 같은 곳에 공유기를 차례로 설치하여 설치 가능한 공유기 수가 M보다 크거나 같은지 확인
    static boolean possible(int mid){
        int idx = 0;
        int count = 1; // 맨 처음 집에는 무조건 공유기를 설치하는 것이 유리하다.
        int tmp = 1;
        while (idx + tmp < N){
            if (arr[idx+tmp] - arr[idx] >= mid) {
                count++;
                idx += tmp;
                tmp = 1;
            } else {
                tmp++;
            }
        }

        if (count >= M) return true;
        return false;
    }

}