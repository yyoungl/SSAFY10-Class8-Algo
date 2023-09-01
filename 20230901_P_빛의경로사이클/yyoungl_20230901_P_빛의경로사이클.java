import java.util.ArrayList;
import java.util.Arrays;

public class P빛경사 {

	static String[] grid1 = { "SL", "LR" };
	static String[] grid2 = { "S" };
	static String[] grid3 = { "R", "R" };
	static ArrayList<Integer> length = new ArrayList<>();
	static char[][] map;
	static int R;
	static int C;
	static boolean[][][] chk;

	public static void main(String[] args) {
		solution(grid1);
		solution(grid2);
		solution(grid3);
	}

	public static int[] solution(String[] grid) {
		R = grid.length;
		C = grid[0].length();
		chk = new boolean[R][C][4];

		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		map = new char[R][C];
		for (int i = 0; i < grid.length; i++) {
			map[i] = grid[i].toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int d = 0; d < 4; d++) {
					int temp =cycle(dr, dc, d, i, j, i, j, 0);
					if (temp > 0) length.add(temp);
				}
			}
		}
		int[] answer = length.stream().mapToInt(i -> i).toArray();
		Arrays.sort(answer);
		return answer;
	}

	public static int cycle(int[] dr, int[] dc, int d, int i, int j, int startR, int startC, int answer) {
	    while (true) {
	        if (chk[i][j][d]) {
	            break;
	        }
	        chk[i][j][d] = true;
	        if (map[i][j] == 'L')
	            d = (d + 3) % 4;
	        else if (map[i][j] == 'R')
	            d = (d + 1) % 4;
	        i = (i + dr[d] + R) % R;
	        j = (j + dc[d] + C) % C;
	        answer++;
	    }
	    return answer;
	}

}
