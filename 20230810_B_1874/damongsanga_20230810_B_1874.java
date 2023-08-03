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
        int[] arr = new int[N];
        int[] reqarr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
            reqarr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;

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
