import java.util.*;

class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // Mark guards
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 1;
        }

        // Mark walls
        for (int[] w : walls) {
            grid[w[0]][w[1]] = 2;
        }

        // Directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        // Mark guarded cells
        for (int[] g : guards) {
            for (int[] d : dirs) {
                int x = g[0] + d[0];
                int y = g[1] + d[1];

                // Move until wall or guard
                while (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] != 2 && grid[x][y] != 1) {
                    // mark as guarded if it's empty
                    if (grid[x][y] == 0) grid[x][y] = 3;
                    x += d[0];
                    y += d[1];
                }
            }
        }

        // Count unguarded cells
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) count++;
            }
        }

        return count;
    }
}
