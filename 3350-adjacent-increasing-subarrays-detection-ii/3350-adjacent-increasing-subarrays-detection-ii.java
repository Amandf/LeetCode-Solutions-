class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];

        // Compute lengths of increasing subarrays ending at i
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums.get(i) > nums.get(i - 1)) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Compute lengths of increasing subarrays starting at i
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && nums.get(i) < nums.get(i + 1)) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Find the maximum minimum length of two adjacent increasing subarrays
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.min(left[i - 1], right[i]));
        }

        return ans;
    }
}
