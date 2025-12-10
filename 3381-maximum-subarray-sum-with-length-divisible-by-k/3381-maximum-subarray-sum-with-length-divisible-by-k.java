class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long prefixSum = 0;
        long maxSum = Long.MIN_VALUE;
        long[] KSum = new long[k];
        for (int i = 0; i < k; i++) {
            KSum[i] = Long.MAX_VALUE / 2;
        }
        KSum[k - 1] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            maxSum = Math.max(maxSum, prefixSum - KSum[i % k]);
            KSum[i % k] = Math.min(KSum[i % k], prefixSum);
        }
        return maxSum;
    }
}