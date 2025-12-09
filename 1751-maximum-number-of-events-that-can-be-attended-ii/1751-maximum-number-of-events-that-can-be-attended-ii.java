class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        int n = events.length;
        int[] startTimes = new int[n];
        for (int i = 0; i < n; i++) {
            startTimes[i] = events[i][0];
        }

        int[][] dp = new int [n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = events[i - 1];
            int start = curr[0], end = curr[1], val = curr[2];

            int j = binarySearch(events, start);

            for (int t = 1; t <= k; t++) {
                dp[i][t] = Math.max(dp[i - 1][t], dp[j + 1][t - 1] + val);
            }
        }

        return dp[n][k];
    }

    private int binarySearch(int[][] events, int target) {
        int lo = 0, hi = events.length - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (events[mid][1] < target) {
                ans = mid;
                lo = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}