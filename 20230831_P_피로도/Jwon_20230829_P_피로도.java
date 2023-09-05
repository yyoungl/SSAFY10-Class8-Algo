package Algorithm.algorithm.programmers;

public class Jwon_20230829_P_피로도 {
	static int max = -1;
	static boolean[] chk;
	public static int solution(int k , int[][] dungeons) {
		chk = new boolean[dungeons.length];
		find(k,dungeons, 0);
		return max;
	}
	
	// 순열을 이용하여 가장 큰 수를 max로 갱신해주었다.
	private static void find(int k , int[][] dungeons, int count) {
		// 방문한 던전의 갯수는 모든 던전의 갯수보다 클 수 없으므로 크다면 return 한다.
		if(count > dungeons.length) {
			return ;
		}
		max = Math.max(count, max);
		for(int i = 0 ; i < dungeons.length; i++) {
			// 던전에 들어갈 수 있는 조건이 된다면 들어간다.
			if(k >= dungeons[i][0] && !chk[i]) {
				// 현재의 던전을 들어갔으므로 true로 변환
				chk[i] = true;
				// 피로도를 줄이고 방문한 던전의 갯수를 늘리고 다른 던전으로 간다.
				find(k - dungeons[i][1], dungeons, count+1);
				chk[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] dungeons = {{80,20},{50,40},{30,10}};
		int k = 70;
		System.out.println(solution(k,dungeons));
	}
}
