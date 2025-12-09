class Solution {
    public int[] earliestAndLatest(int n, int first, int second) {
        int[][][][] memo = new int[n + 1][n + 1][n + 1][2];
        return solve(first, n - second + 1, n, memo);
    }

    private int[] solve(int l, int r, int k, int[][][][] memo) {
        if (l == r) return new int[] {1, 1};
        if (l > r) return solve(r, l, k, memo);
        if (memo[l][r][k][0] != 0) return memo[l][r][k];

        int earliest = Integer.MAX_VALUE;
        int latest = Integer.MIN_VALUE;
        int half = (k + 1) / 2;

        for (int i = 1; i <= l; i++) {
            for (int j = l - i + 1; j <= r - i; j++) {
                if (i + j > half || i + j < l + r - k / 2) continue;
                int[] res = solve(i, j, half, memo);
                earliest = Math.min(earliest, res[0] + 1);
                latest = Math.max(latest, res[1] + 1);
            }
        }

        memo[l][r][k][0] = earliest;
        memo[l][r][k][1] = latest;
        return memo[l][r][k];
    }
}