

class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        long total = 0;
        for (int x : nums) {
            total += x;
        }

        long leftSum = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                long rightSum = total - leftSum;
                // Check condition: leftSum vs rightSum
                if (leftSum == rightSum) {
                    ans += 2;  // both directions valid
                } else if (Math.abs(leftSum - rightSum) == 1) {
                    ans += 1;  // only one direction valid
                }
            }
            // If non-zero, accumulate into leftSum
            leftSum += nums[i];
        }

        return ans;
    }

    // Example test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {1, 0, 2, 0, 3};
        System.out.println(sol.countValidSelections(nums1)); // Expect 2

        int[] nums2 = {2, 3, 4, 0, 4, 1, 0};
        System.out.println(sol.countValidSelections(nums2)); // Expect 0
    }
}
