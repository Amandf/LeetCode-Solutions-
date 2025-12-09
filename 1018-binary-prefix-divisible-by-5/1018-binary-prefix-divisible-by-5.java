import java.util.*;

class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int value = 0;

        for (int b : nums) {
            value = ((value << 1) + b) % 5;
            result.add(value == 0);
        }

        return result;
    }
}
