class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        int[][][] go = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               
                int cr = i, cc = j + 1;
                int dir = 0;  
                go[i][j][0] = simulate(grid, i, j, 0);
                go[i][j][1] = simulate(grid, i, j, 1);
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Try moving Right
                int[] rightTarget = decode(go[i][j][0], n);
                if (rightTarget != null) {
                    dp[rightTarget[0]][rightTarget[1]] = (dp[rightTarget[0]][rightTarget[1]] + dp[i][j]) % MOD;
                }

                // Try moving Down
                int[] downTarget = decode(go[i][j][1], n);
                if (downTarget != null) {
                    dp[downTarget[0]][downTarget[1]] = (dp[downTarget[0]][downTarget[1]] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int simulate(int[][] grid, int r, int c, int initialMoveDir) {
        int m = grid.length;
        int n = grid[0].length;
        
        int currR = r;
        int currC = c;
        int dir = initialMoveDir; 

        while (true) {
            int nextR = currR + (dir == 1 ? 1 : 0);
            int nextC = currC + (dir == 0 ? 1 : 0);

            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) {
                return -1; 
            }

            if (grid[nextR][nextC] == 0) {
                return nextR * n + nextC;
            } else {
              
                currR = nextR;
                currC = nextC;
                dir = 1 - dir;
            }
        }
    }

    private int[] decode(int encoded, int n) {
        if (encoded == -1) return null;
        return new int[] { encoded / n, encoded % n };
    }
}