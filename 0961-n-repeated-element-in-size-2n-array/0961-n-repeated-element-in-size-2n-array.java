class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        // Time: O(n)
        // Space: O(1)
        // Check only nearby elements.
        // The repeated element must match within distance ≤ 3.
        for(int i=0; i<n-1; i++) {
            if(nums[i]==nums[i+1])
                return nums[i];
            if(i+2<n && nums[i]==nums[i+2])
                return nums[i];
            if(i+3<n && nums[i]==nums[i+3])
                return nums[i];
        }
        return -1;
    }
}
