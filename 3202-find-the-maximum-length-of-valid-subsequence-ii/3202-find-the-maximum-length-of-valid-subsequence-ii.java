class Solution {
    public int maximumLength(int[] nums, int k) {
     int[][] dp = new int [k][k];
     int ans = 0;

     for (int num : nums) {
        int rem = num % k;

        int[] old = dp[rem].clone();

        for (int val = 0; val < k; val++) {
            int prev = (val - rem + k) % k;
            dp[rem][val] = Math.max(dp[rem][val], dp[prev][val] + 1);
            ans = Math.max(ans, dp[rem][val]);
        }
        for (int val = 0; val < k; val++) {
            dp[rem][val] = Math.max(dp[rem][val], 1);
            ans = Math.max(ans, dp[rem][val]);
        }
     }   
     return ans;
    }
}