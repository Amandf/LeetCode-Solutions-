class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Use a Set to track seen positions
        Set<Integer> seen = new HashSet<>();
        int prev = Integer.MIN_VALUE;

        // Step 3: Iterate through nums and adjust elements
        for (int num : nums) {
            // Calculate the adjusted position
            int pos = Math.max(prev + 1, num - k);
            if (pos <= num + k) {
                seen.add(pos);
                prev = pos;
            }
        }

        // Step 4: Return the size of the Set
        return seen.size();
    }
}

