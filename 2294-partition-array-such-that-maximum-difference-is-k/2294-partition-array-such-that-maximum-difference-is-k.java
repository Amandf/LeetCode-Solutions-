class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int partitions = 1;
        int start = nums[0];

        for (int x : nums) {
            if (x - start > k) {
                partitions++;
                start = x;
            }
        }

        return partitions;
    }
}