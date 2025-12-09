class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Since nums[i] is in [1,100], we can use fixed array
        int[] count = new int[101];

        // 1. Count frequencies
        for (int x : nums) {
            count[x]++;
        }

        // 2. Find maximum frequency
        int maxFreq = 0;
        for (int freq : count) {
            if (freq > maxFreq) {
                maxFreq = freq;
            }
        }

        // 3. Sum frequencies of elements that have frequency == maxFreq
        int ans = 0;
        for (int freq : count) {
            if (freq == maxFreq) {
                ans += freq;
            }
        }

        return ans;
    }
}
