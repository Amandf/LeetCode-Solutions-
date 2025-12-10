public class Solution {
    private final int MOD = 1_000_000_007;
    private long[] fact, invFact; 

    public int countGoodArrays(int n, int m, int k) {
        precompute(n);
                long comb = fact[n - 1] * invFact[k] % MOD * invFact[n - 1 - k] % MOD;
        long ways = comb * m % MOD * modPow(m - 1, n - k - 1) % MOD;
        return (int) ways;
    }

    private void precompute(int n) {
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
    }

    private long modPow(long base, int exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
}