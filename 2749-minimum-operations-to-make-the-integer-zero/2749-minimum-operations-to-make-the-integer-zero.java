class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 0; k <= 60; ++k) {
            long target = (long) num1 - k * num2;
            if (target < 0) continue;
            int bits = Long.bitCount(target);
            if (bits <= k && k <= target) {
                return (int) k;
            }
        }
        return -1;
    }
}