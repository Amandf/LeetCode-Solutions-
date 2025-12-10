class Solution {
    private static final int[][] DIRS = {{-1,1},{1,1},{1,-1},{-1,-1}};
    
    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][][][][] mem = new Integer[m][n][2][2][4];
        int ans = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + DIRS[d][0];
                        int nj = j + DIRS[d][1];
                        ans = Math.max(ans, 1 + dfs(grid, ni, nj, false, 2, d, mem));
                    }
                }
            }
        }
        return ans;
    }
    
    private int dfs(int[][] grid, int i, int j, boolean turned, int num, int dir, Integer[][][][][] mem) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;
        if (grid[i][j] != num) return 0;
        
        int turnedIdx = turned ? 1 : 0;
        int hashNum = Math.max(0, num - 1);  // maps 2→1, 0→0
        if (mem[i][j][turnedIdx][hashNum][dir] != null) {
            return mem[i][j][turnedIdx][hashNum][dir];
        }
        
        int nextNum = (num == 2) ? 0 : 2;
        int[] d = DIRS[dir];
        int res = 1 + dfs(grid, i + d[0], j + d[1], turned, nextNum, dir, mem);
        
        if (!turned) {
            int nextDir = (dir + 1) % 4;
            int[] d2 = DIRS[nextDir];
            res = Math.max(res, 1 + dfs(grid, i + d2[0], j + d2[1], true, nextNum, nextDir, mem));
        }
        
        return mem[i][j][turnedIdx][hashNum][dir] = res;
    }
}
