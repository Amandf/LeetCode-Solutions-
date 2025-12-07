
class Solution {
    private int m, n;
    private int[][] heights;
    private boolean[][] pacific, atlantic;
    private static final int[][] DIRS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return new ArrayList<>();

        this.heights = heights;
        m = heights.length;
        n = heights[0].length;

        pacific = new boolean[m][n];
        atlantic = new boolean[m][n];

        
        for (int i = 0; i < m; i++) dfs(i, 0, pacific);
        for (int j = 0; j < n; j++) dfs(0, j, pacific);

        // Atlantic: bottom row & right column
        for (int i = 0; i < m; i++) dfs(i, n - 1, atlantic);
        for (int j = 0; j < n; j++) dfs(m - 1, j, atlantic);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void dfs(int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        for (int[] d : DIRS) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || y < 0 || x >= m || y >= n) continue;
            if (visited[x][y]) continue;
            if (heights[x][y] < heights[i][j]) continue;
            dfs(x, y, visited);
        }
    }
}
