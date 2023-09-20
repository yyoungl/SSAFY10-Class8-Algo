import java.io.*;
import java.util.*;

public class damongsanga_20230921_B_2812_시간초과 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String input = br.readLine();


        // 매번 K+1개만큼 탐색하여 그 중 가장 큰 값이 있는 지점을 찾아 그 직전까지의 값을 빼주고 그만큼 K를 갱신
        // 예시로 K가 4이면 앞 5개 수를 봄, 그 5개 수가 52381 이라면 8이 MAX임으로 5,2,3를 제거, K도 3만큼 빼준 1로 갱신
        // 해당 MAX 값은 덱에 넣고 그 뒤부터 동일한 방식으로 탐색
        // K가 0이 되거나 탐색 범위가 넘어가 버리면 반복 종료
        // 맨 앞자리가 클 수록 큰 수가 나오기 때문에 그리디하게 접근할 수 있음

        // 덱으로 한 이유, 로직은 스택으로 구현해야하나 뽑을 때 순서가 뒤집힘으로 다시 뒤집는 연산을 하지 않기 위해
        Deque<Integer> deque = new ArrayDeque<>();
        int srt = 0;
        while(K > 0 && srt+K < N){
            int max = 0;
            int maxIdx = 0;
            for (int i = 0; i < K+1; i++) {
                int tmp = input.charAt(srt+i) - '0';
                if (max < tmp){
                    max = tmp;
                    maxIdx = i;
                }
            }
            deque.addLast(max);
            K -= maxIdx;
            srt += maxIdx+1;
        }

        // K가 뒤에 남아있을 수도 있음으로 i < N-K로 제한
        for (int i = srt; i < N-K; i++) {
            deque.addLast(input.charAt(i)-'0');
        }


        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.pollFirst());
        }
        System.out.println(sb);



    }
}