import java.util.*;

class Building {
    int h;
    int count;

    Building(int h, int count) {
        this.h = h;
        this.count = count;
    }
}

public class damongsanga_20230817_B_6198 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 입력받을 stack
        Stack<Building> stackOrigin = new Stack<>();
        // 알고리즘 풀이를 위한 stack
        Stack<Building> stack = new Stack<>();

        // 빌딩의 높이와 그 빌딩이 보고 있는 빌딩수를 나타내는 카운트 값을 가진 빌딩 객체를 입력 stack에 순차적으로 넣는다.
        for (int i = 0; i < N; i++) {
            stackOrigin.push(new Building(sc.nextInt(), 0));
        }

        // 답이 int 범위를 넘어갈 수 있음
        long answer = 0;


        while (!stackOrigin.isEmpty()) {
            Building b = stackOrigin.pop();
            int count = b.count;
            if (!stack.isEmpty()) {
                // 스택이 비거나 스택에서 바라본 뒷 빌딩의 높이가 자신보다 작을 때까지
                while (!stack.isEmpty() && b.h > stack.peek().h) {
                    int counttmp = stack.pop().count;
                    count += 1 + counttmp; // 자기가 본 빌딩 + 자기가 본 빌딩이 본 빌딩 수
                    answer += counttmp; // 스택에서 제거한 빌딩의 카운트를 답에 더한다.
                }
                stack.push(new Building(b.h, count));
            } else {
                stack.push(b);
            }
        }

        // 스택의 남은 모든 빌딩의 카운트를 더한다
        while (!stack.isEmpty()) {
            answer += stack.pop().count;
        }

        System.out.println(answer);

    }

}

