
import java.util.HashSet;
import java.util.Set;

class damongsanga_20230802_P_소수찾기 {
        static Set<Integer> set;
        static char[] arr;
        public int solution(String numbers) {
            arr = numbers.toCharArray();
            set = new HashSet<>();
            boolean[] visited = new boolean[arr.length];


            backtracking(0,"", visited);
            System.out.println(set.toString());

            int answer = 0;
            for (Integer i: set) {
                if (isDecimal(i)) {
                    System.out.println(i);
                    answer++;
                }
            }
            return answer;
        }

        public static void backtracking(int n, String s, boolean[] visited){
            for (int i = 0; i < arr.length; i++) {
                if (!visited[i]){
                    visited[i] = true;
                    s += arr[i];
                    backtracking(n+1, s, visited);
                    s = s.substring(0, n);
                    visited[i] = false;
                }
            }
            if (!s.equals(""))
                set.add(Integer.parseInt(s));
        }

        public static boolean isDecimal(int x) {

            if (x <= 1) return false;

            for (int i = 2; i <= Math.sqrt(x); i++) {
                if (x % i == 0) return false;
            }
            return true;
        }
}
