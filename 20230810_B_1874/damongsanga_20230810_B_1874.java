import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class damongsanga_20230810_B_1874 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        // 1 ~ N 까지 정렬되어있는 배열 선언
        int[] arr = new int[N];
        // 변경 요구되는 순서를 나타내는 배열 선언
        int[] reqarr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
            reqarr[i] = Integer.parseInt(br.readLine());
        }

        // reqarr 용 index 
        int idx = 0;
        // for문을 돌면서 arr 값을 순서대로 스택에 push한다
        // 스택을 peek해서 원하는 값이 나오면 stack을 pop하여 stringbuilder에 저장한다
        // 만약 for문을 모두 돌았는데 stack에 값이 남아있다면 불가능한 경우로 sb대신 "No" 출력
        for (int i = 0; i < N; i++) {
            stack.push(arr[i]);
            sb.append("+").append('\n');
            while (stack.peek() == reqarr[idx]){
                stack.pop();
                sb.append("-").append('\n');
                idx++;
                if (stack.empty()) break;
            }
        }

        System.out.print(stack.empty()? sb : "NO");

    }
}
