class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] productQueries(int n, int[][] queries) {
        // collect powers of two present in n (ascending by exponent)
        List<Long> powers = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 1) == 1) {
                powers.add(1L << i);
            }
        }

        int m = powers.size();
        long[] prefix = new long[m + 1];
        prefix[0] = 1L;
        for (int i = 0; i < m; i++) {
            prefix[i + 1] = (prefix[i] * powers.get(i)) % MOD;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            long numerator = prefix[r + 1];
            long denominator = prefix[l];
            ans[i] = (int) ((numerator * modPow(denominator, MOD - 2)) % MOD);
        }
        return ans;
    }

    private long modPow(long base, long exp) {
        long res = 1L;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
