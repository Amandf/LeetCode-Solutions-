class Solution {
    public int maximumLength(int[] nums) {
        int [][] dp = new int[2][2];
        int ans = 0;
        for (int val : nums) {
            int x = val % 2;
            for (int y = 0; y < 2; y++) {
                dp[x][y] = dp[y][x] + 1;
                ans = Math.max(ans, dp[x][y]);
            }
        }
        return ans;
    }
}