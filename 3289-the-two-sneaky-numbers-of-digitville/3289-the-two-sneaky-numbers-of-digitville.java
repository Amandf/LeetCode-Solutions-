class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        // Since nums.length = n + 2 and values are in [0, n-1], two numbers appear twice.
        int nplus2 = nums.length;
        int n = nplus2 - 2;
        int[] count = new int[n + 1];           // count[i] = occurrences of i
        int[] result = new int[2];
        int resIndex = 0;

        for (int num : nums) {
            count[num]++;
            if (count[num] == 2) {
                result[resIndex++] = num;
                if (resIndex == 2) break;
            }
        }
        return result;
    }
}
