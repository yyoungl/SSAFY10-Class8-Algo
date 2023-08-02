
import java.util.HashSet;
import java.util.Set;

class damongsanga_20230802_P_소수찾기 {
        static Set<Integer> set;
        static char[] arr;
        public int solution(String numbers) {
            arr = numbers.toCharArray();
            set = new HashSet<>();
            boolean[] visited = new boolean[arr.length];

                // 백트랙킹으로 조합 가능한 숫자를 set에 담는다
            backtracking(0,"", visited);

            int answer = 0;
                // set의 원소가 소수인지 판단하여 소수 갯수당 answer++
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
                        // 방문처리 및 String 값 추가
                    visited[i] = true;
                    s += arr[i];
                    backtracking(n+1, s, visited);
                        // 방문 및 string 값 원복
                    s = s.substring(0, n);
                    visited[i] = false;
                }
            }
                // 중복 방지를 위해 set에 저장
            if (!s.equals(""))
                set.add(Integer.parseInt(s));
        }

        // 소수인지 판단하는 함수 생성
        public static boolean isDecimal(int x) {
                // 1이면 소수 X
            if (x <= 1) return false;
                // 
            for (int i = 2; i <= Math.sqrt(x); i++) {
                if (x % i == 0) return false;
            }
            return true;
        }
}
