import java.io.*;
import java.util.*;

public class damongsanga_20231110_B_2263_트리의순회 {

    static int[] inOrder;
    static int[] postOrder;
    static int[] preOrder;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        preOrder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        recur(0,N,0,N, 0,N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(preOrder[i] + " ");
        }

        System.out.println(sb.toString().trim());

    }
    static void recur(int psrt, int pend, int isrt, int iend, int srt, int end){
        // 후위 순회의 마지막 값이 그 트리의 루트노드
        int root = postOrder[pend-1];
        // 전위 순회의 첫 값이 그 크리의 루트 노드
        preOrder[srt] = root;
        for (int i = isrt; i < iend; i++) {
            if (inOrder[i] == root){
                int l = i - isrt;
                // 왼쪽 트리 탐색
                if (psrt + l >= 1 && srt+1 < N)
                    recur(psrt, psrt + l, isrt, i, srt+1, srt+1+l); // L
                // 오른쪽 트리 탐색
                if (pend-1 >= 1 && srt + 1 + l < N)
                    recur(psrt + l, pend-1,i+1, iend, srt+1+l, end); // R
                break;
            }
        }
    }
}