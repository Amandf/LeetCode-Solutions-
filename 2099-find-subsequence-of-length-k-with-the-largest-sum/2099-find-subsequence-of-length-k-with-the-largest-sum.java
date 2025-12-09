class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (i, j) -> Integer.compare(nums[j], nums[i]));
        Integer[] topK = Arrays.copyOf(idx, k);

        Arrays.sort(topK);

        int[] ans = new int[k];
        for (int i = 0; i< k; i++) {
            ans[i] = nums[topK[i]];
        }
        return ans;
    }
}