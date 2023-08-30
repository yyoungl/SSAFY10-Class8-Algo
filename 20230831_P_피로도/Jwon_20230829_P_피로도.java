package Algorithm.algorithm.programmers;

public class Jwon_20230829_P_피로도 {
	static int max = -1;
	static boolean[] chk;
	public static int solution(int k , int[][] dungeons) {
		chk = new boolean[dungeons.length];
		find(k,dungeons, 0);
		return max;
	}
	
	private static void find(int k , int[][] dungeons, int count) {
		if(count > dungeons.length) {
			return ;
		}
		max = Math.max(count, max);
		for(int i = 0 ; i < dungeons.length; i++) {
			if(k >= dungeons[i][0] && !chk[i]) {
				chk[i] = true;
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
