class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[][] memo;

    public int numberOfWays(int n, int x) {
        int maxBase = computeMaxBase(n, x);
        memo = new Long[maxBase + 2][n + 1];
        return (int) dfs(1, n, x, maxBase);
    }

    private long dfs(int base, int target, int power, int maxBase) {
        if (target == 0) return 1;
        if (base > maxBase || target < 0) return 0;
        if (memo[base][target] != null) return memo[base][target];

        long val = ipow(base, power);
        long take = dfs(base + 1, (int)(target - val), power, maxBase) % MOD;
        long skip = dfs(base + 1, target, power, maxBase) % MOD;
        return memo[base][target] = (take + skip) % MOD;
    }

    // integer power (base^exp), returns long
    private long ipow(int base, int exp) {
        long res = 1L;
        for (int i = 0; i < exp; i++) {
            res *= base;
            // optional: early break if res > 1e9+7 and you want to cap, but here it's fine
        }
        return res;
    }

    // compute largest integer b such that b^x <= n
    private int computeMaxBase(int n, int x) {
        int b = 1;
        while (true) {
            long next = ipow(b + 1, x);
            if (next <= n) b++;
            else break;
        }
        return b;
    }
}
