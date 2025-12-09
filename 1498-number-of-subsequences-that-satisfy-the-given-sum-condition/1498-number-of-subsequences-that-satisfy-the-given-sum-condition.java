class Solution {
    public int numSubseq(int[] nums, int target) {
        final int MOD = 1_000_000_007;
        int n = nums.length;

        Arrays.sort(nums);

        int[] p2 = new int[n];
        p2[0] = 1;
        for (int i = 1; i < n; i++) {
            p2[i] = (p2[i - 1] * 2) % MOD;
        }

        int ans = 0;
        int l = 0, r = n - 1;

        while (l <= r) {
            if (nums[l] + nums[r] <= target) {
                ans = (ans + p2[r - l]) % MOD;
                l++;
            } else {
                r--;
            }
        }

        return ans;
    }
}