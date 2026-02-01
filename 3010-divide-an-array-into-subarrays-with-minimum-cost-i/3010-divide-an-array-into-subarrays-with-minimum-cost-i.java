class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        //nums[0] is always the first subarray's cost
        for(int i=1;i<=n-2;i++) { // start of 2nd subarray
            for(int j=i+1;j<=n-1;j++) { //start of 3rd subarray
                int cost = nums[0] + nums[i] + nums[j];
                ans = Math.min(ans, cost);
            }
        }
        return ans;
    }
}