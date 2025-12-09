class Solution {
    public int maximumUniqueSubarray(int[] nums) {
     Set<Integer> used = new HashSet<>();
     int left = 0, maxSum = 0;
     long currentSum =0;

     for (int right = 0; right < nums.length; right++) {
        int x = nums[right];
        while (used.contains(x)) {
            used.remove(nums[left]);
            currentSum -= nums[left];
            left++;
        }

        used.add(x);
        currentSum += x;
        maxSum = (int) Math.max(maxSum, currentSum);
     }
     return maxSum;

    }
}