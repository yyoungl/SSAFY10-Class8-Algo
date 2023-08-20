import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class damongsanga_20230823_B_1931 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작 기준으로 정렬, 시작 시간이 같을 때 끝 기준으로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int end = -1;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            // 새로운 회의의 시작 시간이 기존 마지막 회의 끝나는 시간보다 늦으면 answer++
            // 마지막 회의 시간 갱신
            if (arr[i][0] >= end) {
                answer++;
                end = arr[i][1];
            }
            // 만약 마지막 회의 시간이 더 짧은 경우가 있다면 마지막 회의 시간 갱신
            // 시작 시간 기준으로 정렬했음으로 무조건 그 회의를 할 수 있음
            // 예시로 3 ~ 11 회의가 있다면 5 ~ 8 회의는 그 이후로 정렬되어 있음으로 무조건 할 수 있다.
            else if (arr[i][1] < end) {
                end = arr[i][1];
            }
        }
        System.out.println(answer);

    }
}