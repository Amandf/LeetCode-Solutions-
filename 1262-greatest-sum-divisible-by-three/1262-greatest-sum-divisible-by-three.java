import java.util.*;

class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;

        // To track smallest remainders
        int min1_r1 = Integer.MAX_VALUE;
        int min2_r1 = Integer.MAX_VALUE;

        int min1_r2 = Integer.MAX_VALUE;
        int min2_r2 = Integer.MAX_VALUE;

        for (int x : nums) {
            sum += x;

            int r = x % 3;

            if (r == 1) {
                if (x < min1_r1) { 
                    min2_r1 = min1_r1; 
                    min1_r1 = x; 
                } else if (x < min2_r1) {
                    min2_r1 = x;
                }
            }

            if (r == 2) {
                if (x < min1_r2) {
                    min2_r2 = min1_r2;
                    min1_r2 = x;
                } else if (x < min2_r2) {
                    min2_r2 = x;
                }
            }
        }

        if (sum % 3 == 0) return sum;

        int ans = 0;

        if (sum % 3 == 1) {
            int option1 = min1_r1;
            int option2 = (min1_r2 == Integer.MAX_VALUE || min2_r2 == Integer.MAX_VALUE)
                        ? Integer.MAX_VALUE
                        : min1_r2 + min2_r2;

            int remove = Math.min(option1, option2);
            ans = sum - remove;
        } else {  // sum % 3 == 2
            int option1 = min1_r2;
            int option2 = (min1_r1 == Integer.MAX_VALUE || min2_r1 == Integer.MAX_VALUE)
                        ? Integer.MAX_VALUE
                        : min1_r1 + min2_r1;

            int remove = Math.min(option1, option2);
            ans = sum - remove;
        }

        return ans < 0 ? 0 : ans;
    }
}
