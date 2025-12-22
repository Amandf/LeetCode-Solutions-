class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int[] dp = new int[m];
        int maxKeep = 1;
        for (int j = 0; j < m; j++) {
            dp[j] = 1; // each column alone
            for (int i = 0; i < j; i++) {
                if (canExtend(strs, i, j)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            maxKeep = Math.max(maxKeep, dp[j]);
        }

        return m - maxKeep;
    }

    private boolean canExtend(String[] strs, int i, int j) {
        for (String s : strs) {
            if (s.charAt(i) > s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}