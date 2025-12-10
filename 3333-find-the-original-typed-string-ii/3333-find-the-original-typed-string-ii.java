public class Solution {
    static int MOD = (int)1e9 + 7;

    public int possibleStringCount(String word, int k) {
        if (word.length() == 0)
            return 0;

        ArrayList<Integer> groups = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count);

        long total = 1;
        for (int num : groups)
            total = (total * num) % MOD;

        if (k <= groups.size())
            return (int) total;

        int[] dp = new int[k];
        dp[0] = 1;

        for (int freq : groups) {
            int[] tempDp = new int[k];
            long[] prefix = new long[k + 1];
            for (int i = 0; i < k; i++) {
                prefix[i + 1] = (prefix[i] + dp[i]) % MOD;
            }

            for (int i = 1; i < k; i++) {
                int l = Math.max(0, i - freq);
                tempDp[i] = (int) ((prefix[i] - prefix[l] + MOD) % MOD);
            }

            dp = tempDp;
        }

        long invalidCount = 0;
        for (int i = 0; i < k; i++) {
            invalidCount = (invalidCount + dp[i]) % MOD;
        }

        return (int) ((total - invalidCount + MOD) % MOD);
    }
}
