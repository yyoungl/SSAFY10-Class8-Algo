import java.util.Arrays;
import java.util.Scanner;

public class B15684 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int H = sc.nextInt();
		
        // 행 0번: 시작 행 H+1번: 끝
		int[][] ladder = new int[H+2][N+1];
		for (int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
            // 옆구리 이동 방향으로 1, -1 (막대가 양쪽으로 연결되기 때문에 +-1 로 했어요)
			ladder[a][b] = 1;
			ladder[a][b+1] = -1;
		}
		
		int answer = -1;
		
		// dfs 했어요.
        // 0개를 그을 때~3개를 그을 때
		for (int i=0; i<=3; i++) {
            // 그었을 때 사다리가 조건에 맞으면 벗어나고 답을 초기화하기
			if (dfs(i, 0, ladder, N, H)) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
		
	}
	

    // 조합을 구하는 dfs
    // 그런데 이차원 배열에서 조합 구하는 것이 index로 하는 방법도 있다는데... (나중에)
	static boolean dfs(int depth, int cnt, int[][] ladder, int N, int H) {
        // 지금까지 그은 줄의 개수 == 내가 긋고 싶은 줄의 개수
		if (cnt == depth) {
            // ladderCheck 하기
			return ladderCheck(ladder, N, H);
		} else {
			for (int r=1; r<=H; r++) {
				for (int c=1; c<N; c++) {
					if (ladder[r][c] == 0 && ladder[r][c+1]==0) {
						ladder[r][c] = 1;
						ladder[r][c+1]=-1;
                        // 날 힘들게 한...
                        // 여기서 dfs의 결과가 true면 true를 보내준다
						boolean temp = dfs(depth, cnt+1, ladder, N, H);
						if (temp) return true;
						ladder[r][c]=0;
						ladder[r][c+1]=0;
					}
				}
			}
			
		}
		
		return false;
	}
	
	static boolean ladderCheck(int[][] ladder, int N, int H) {
		boolean result = true;
        // 시작 위치 1번부터 N번까지
		for (int i=1; i<=N; i++) {
            // 1번 행부터 시작하고
			int r=1;
            // i번 열부터 시작
			int c=i;
            // 현재 위치가 H를 벗어나면 while 문을 빠져나온다
			while (r<H+1) {
                // 현재 위치에서 값이 1이거나 -1이면 옆으로 움직인다
				if (ladder[r][c] == 1) c++;
				else if (ladder[r][c] == -1) c--;
                // 그리고 한칸 아래로 가기
				r++;
			}
			if (c!=i) {
                // 만약 다 내려온 후에 원래 시작점 i와 c가 다르다면 사다리가 말이 안 된다는 뜻이므로 결과에 false, break
				result = false;
				break;
			}
		}
		return result;
	}

}
