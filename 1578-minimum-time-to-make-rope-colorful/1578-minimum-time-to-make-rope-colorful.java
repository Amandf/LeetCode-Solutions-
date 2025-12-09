class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalTime = 0;
        int n = colors.length();
        
        // Traverse the string
        for (int i = 1; i < n; i++) {
            // If two adjacent balloons have the same color
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                // Add the smaller removal time to total
                totalTime += Math.min(neededTime[i], neededTime[i - 1]);
                
                // Keep the higher time balloon for future comparisons
                neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
            }
        }
        return totalTime;
    }
}
