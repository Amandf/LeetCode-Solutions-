class Solution {
    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    public int minimumDistance(String word) {
        int n = word.length();
        // dp[j] = max saving when second finger is at j
        int[] dp = new int[26];
        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            int cur = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int d = dist(cur, next);
            total += d;
            int[] newDp = new int[26];
            for (int j = 0; j < 26; j++) {
                // case 1: don't use second finger
                newDp[j] = Math.max(newDp[j], dp[j]);
                // case 2: use second finger (save distance)
                int gain = d - dist(j, next);
                newDp[cur] = Math.max(newDp[cur], dp[j] + gain);
            }
            dp = newDp;
        }
        int maxSaving = 0;
        for (int x : dp) maxSaving = Math.max(maxSaving, x);
        return total - maxSaving;
    }
}