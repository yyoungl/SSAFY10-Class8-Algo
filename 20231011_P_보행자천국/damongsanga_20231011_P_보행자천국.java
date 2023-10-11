import java.util.Arrays;

public class damongsanga_20231011_P_보행자천국 {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];
        // 전 구간 미방문처리
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        return dfs(0,0,dp,m,n,cityMap,MOD);

    }
    static int dfs(int r, int c, int[][] dp, int m, int n, int[][] cityMap, int MOD){
        
        if (r == m-1 && c == n-1) return 1; // 탈출조건
        if(dp[r][c] != -1) return dp[r][c]; // Memoization 활용

        // 방문처리
        dp[r][c] = 0;
        
        // 2가 아닌 지점을 찾을때까지 오른쪽으로 이동
        int idx_x = 1;
        while(r + idx_x < m && cityMap[r+idx_x][c] == 2){
            idx_x++;
        }
        
        // 2가 아닌 지점을 찾을 때까지 아래로 이동
        int idx_y = 1;
        while(c + idx_y < n && cityMap[r][c+idx_y] == 2){
            idx_y++;
        }
        // 범위를 벗어나지 않고 벽이 아니라면 dp 배열에 누적
        // 여기서 MOD로 나눠 주어야함 (범위 넘어가는듯)
        if (r + idx_x < m && cityMap[r+idx_x][c] != 1) dp[r][c] += dfs(r+idx_x, c, dp, m,n, cityMap, MOD) % MOD;
        if (c + idx_y < n && cityMap[r][c+idx_y] != 1) dp[r][c] += dfs(r, c+idx_y, dp, m,n, cityMap, MOD) % MOD;
        return dp[r][c] % MOD;

    }
}