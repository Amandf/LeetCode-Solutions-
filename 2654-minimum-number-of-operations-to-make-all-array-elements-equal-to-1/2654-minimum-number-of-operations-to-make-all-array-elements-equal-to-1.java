class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ones = 0;
        
        // Count how many 1s are already present
        for (int x : nums) {
            if (x == 1) ones++;
        }
        
        // If there are already some 1s, we only need to convert others to 1
        if (ones > 0) return n - ones;
        
        // Otherwise, try to find smallest subarray whose GCD becomes 1
        int minOps = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int gcd = nums[i];
            for (int j = i + 1; j < n; j++) {
                gcd = gcd(gcd, nums[j]);
                if (gcd == 1) {
                    minOps = Math.min(minOps, j - i + 1);
                    break; // no need to go further; already minimal for this i
                }
            }
        }
        
        // If no subarray has GCD = 1 → impossible
        if (minOps == Integer.MAX_VALUE) return -1;
        
        // To spread this 1 to all elements: (minOps - 1) to create one 1 + (n - 1) to propagate it
        return (minOps - 1) + (n - 1);
    }

    // Euclidean GCD helper
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
