import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] freq = new int[value];
        for (int num : nums) {
            int r = ((num % value) + value) % value; // handle negative mods
            freq[r]++;
        }

        for (int x = 0; ; x++) {
            int r = x % value;
            if (freq[r] == 0) {
                return x; // this remainder has been used up
            }
            freq[r]--;
        }
    }
}
