class Solution {
    private static final int[][] DIRS= {{0,1},{1,0},{0,-1},{-1,0}};


    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int islands = 0;

        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }
        return islands;
        
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] !='1')
        return;

        grid[i][j] = '0';
        for (int[] d : DIRS) {
            dfs(grid, i+ d[0], j + d[1]);
        }
    }
}