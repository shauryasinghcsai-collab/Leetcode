class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 1. Flood all 0s connected to the top and bottom borders
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) {
                dfs(grid, 0, c);
            }
            if (grid[rows - 1][c] == 0) {
                dfs(grid, rows - 1, c);
            }
        }
        
        // 2. Flood all 0s connected to the left and right borders
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) {
                dfs(grid, r, 0);
            }
            if (grid[r][cols - 1] == 0) {
                dfs(grid, r, cols - 1);
            }
        }
        
        int closedIslandsCount = 0;
        
        // 3. Count remaining enclosed islands in the inner grid
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c);
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark current land as visited by turning it into water (1)
        grid[r][c] = 1;
        
        // Visit all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}