
class Solution {
    private int[] prices;
    private long[][][] memo;
    private static final long NEG = Long.MIN_VALUE / 4;
    public long maximumProfit(int[] prices, int k) {
        this.prices = prices;
        int n = prices.length;
        memo = new long[n][k + 1][3];
        // initialize memo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int s = 0; s < 3; s++) {
                    memo[i][j][s] = NEG;
                }
            }
        }
        return dfs(n - 1, k, 0); // must end flat
    }

    private long dfs(int i, int j, int state) {
        // no days left
        if (i < 0) {
            return state == 0 ? 0 : NEG;
        }
        if (memo[i][j][state] != NEG) {
            return memo[i][j][state];
        }
        int p = prices[i];
        long res;
        if (state == 0) {
            // stay flat
            res = dfs(i - 1, j, 0);
            // close long
            res = Math.max(res, dfs(i - 1, j, 1) + p);
            // close short
            res = Math.max(res, dfs(i - 1, j, 2) - p);
        } else if (state == 1) {
            // keep holding long
            res = dfs(i - 1, j, 1);
            // open long (buy)
            if (j > 0) {
                res = Math.max(res, dfs(i - 1, j - 1, 0) - p);
            }
        } else { // state == 2
            // keep holding short
            res = dfs(i - 1, j, 2);
            // open short (sell)
            if (j > 0) {
                res = Math.max(res, dfs(i - 1, j - 1, 0) + p);
            }
        }
        memo[i][j][state] = res;
        return res;
    }
}


