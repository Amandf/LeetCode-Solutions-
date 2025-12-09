
public class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[status.length];
        boolean[] hasKey = new boolean[status.length];
        boolean[] hasBox = new boolean[status.length];

        // Add all initial boxes to the pool
        for (int box : initialBoxes) {
            hasBox[box] = true;
        }

        // Try to process all accessible boxes
        int totalCandies = 0;
        boolean progress;

        do {
            progress = false;
            for (int i = 0; i < status.length; i++) {
                if (hasBox[i] && (status[i] == 1 || hasKey[i]) && !visited[i]) {
                    visited[i] = true;
                    totalCandies += candies[i];
                    progress = true;

                    
                    for (int key : keys[i]) {
                        hasKey[key] = true;
                    }

                    
                    for (int box : containedBoxes[i]) {
                        hasBox[box] = true;
                    }
                }
            }
        } while (progress);

        return totalCandies;
    }
}
