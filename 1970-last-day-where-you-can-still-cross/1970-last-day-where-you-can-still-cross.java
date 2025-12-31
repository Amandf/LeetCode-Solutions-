import java.util.*;
// Time imports were only for debugging; removed for clarity

class Solution {

    // Directions for moving: down, right, up, left
    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};

    public int latestDayToCross(int row, int col, int[][] cells) {

       

        int left = col - 1;        // minimum possible day
        int right = cells.length; // maximum possible day

        while (left < right) {
            // Upper mid to avoid infinite loop
            int mid = right - (right - left) / 2;

            if (walkPossible(row, col, cells, mid)) {
                left = mid;        // crossing still possible → move right
            } else {
                right = mid - 1;   // crossing failed → move left
            }
        }

        return left;
    }

    boolean walkPossible(int rows, int cols, int[][] cells, int day) {

        /*
         Build grid for given day:
         0  → water (walkable)
         1  → flooded
         -1 → visited
        */
        int[][] grid = new int[rows][cols];

        // Flood cells for the given day
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
        }

        // BFS queue
        Queue<int[]> q = new LinkedList<>();

        // Start BFS from ALL non-flooded cells in first row
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) {
                q.offer(new int[]{0, j});
                grid[0][j] = -1; // mark visited
            }
        }

        // Standard BFS
        while (!q.isEmpty()) {
            int[] cell = q.poll();

            // If we reach the last row, crossing is possible
            if (cell[0] == rows - 1) {
                return true;
            }

            // Explore 4 directions
            for (int[] dir : dirs) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];

                if (newRow >= 0 && newCol >= 0 &&
                    newRow < rows && newCol < cols &&
                    grid[newRow][newCol] == 0) {

                    grid[newRow][newCol] = -1; // mark visited
                    q.offer(new int[]{newRow, newCol});
                }
            }
        }

        return false; // no path found
    }
}