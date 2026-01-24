class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums); // Nlogn, O(1)
        int max = 0;
        int i=0, j=nums.length-1;
        while( i < j) {
            max = Math.max(max, nums[i]+nums[j]);
            i++; j--; 
        }
        return max;
    }
}
