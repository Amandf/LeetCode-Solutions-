class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;
        long share = 0;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; ++i) {
            if (i - delay >= 0) {
                share = (share + dp[i - delay]) % MOD;
            }
            if ( i - forget >= 0) {
                share = (share - dp[i - forget] + MOD) % MOD;  
            }
            dp[i] = (int) share;
        }

        long result = 0;

        for (int i = n - forget; i < n; ++i) {
            result = (result + dp[i]) % MOD;
        }
        return (int) result;
    }
}