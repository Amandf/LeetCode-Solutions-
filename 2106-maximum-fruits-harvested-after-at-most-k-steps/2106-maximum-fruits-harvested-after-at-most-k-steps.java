class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int [] pos = new int [n];
        int [] preSum = new int [n + 1];

        for (int i = 0; i < n; ++i) {
            pos[i] = fruits[i][0];
            preSum[i + 1] = preSum[i] + fruits[i][1];
        }

        int ans = 0;

        for (int x = 0; x <= k; ++x) {
            int left = startPos - x;
            int right = startPos + Math.max(0, k - 2 * x);

            int l = lowerBound(pos, left);
            int r = upperBound(pos, right);
            ans = Math.max(ans, preSum[r] - preSum[l]);
        }

        for (int x = 0; x <= k; ++x) {
            int right = startPos + x;
            int left = startPos - Math.max(0, k - 2 * x);

            int l = lowerBound(pos, left);
            int r = upperBound(pos, right);
            ans = Math.max(ans, preSum[r] - preSum[l]);
        }

        return ans;
    }
    private int lowerBound(int[] pos, int target) {
        int l = 0, r = pos.length;
        while ( l < r) {
            int m = (l + r) /2;
            if (pos[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }
    private int upperBound(int[] pos, int target) {
        int l = 0, r = pos.length;
        while ( l < r) {
            int m = (l + r) / 2;
            if (pos[m] > target) r = m;
            else l = m + 1;
        }
        return l;
    }
}