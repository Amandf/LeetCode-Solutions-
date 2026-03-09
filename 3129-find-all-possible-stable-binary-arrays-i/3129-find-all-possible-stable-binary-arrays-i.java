class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        long[] [] dp0 = new long[zero + 1] [one + 1];
        long[] [] dp1 = new long[zero + 1] [one + 1];

        for(int i = 1; i <= zero && i <= limit; i++) {
            dp0[i][0] = 1;
        }

        for(int j = 1; j <= one && j <= limit; j++) {
            dp1[0][j] = 1;
        }

        for(int i = 1; i <= zero; i++) {
            for(int j = 1; j <= one; j++) {
                long sum0 = 0;
                for(int k = 1; k <= limit && k <= i; k++) {
                    sum0 = (sum0 + dp1[i - k] [j]) % MOD;
                }
                dp0[i][j] = sum0;

                long sum1 = 0;
                for(int k = 1; k <= limit && k <= j; k++) {
                    sum1 = (sum1 + dp0[i] [j - k]) % MOD;
                }
                dp1[i][j] = sum1;
            }
        }
        return (int) ((dp0[zero] [one] + dp1[zero] [one]) % MOD);
    }
}