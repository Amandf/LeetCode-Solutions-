class Solution {
    public int minimumOneBitOperations(int n) {
        if (n == 0) return 0;

        int msb = 0;
        // Find the position of the most significant bit (MSB)
        while ((1 << (msb + 1)) <= n) {
            msb++;
        }

        // Apply the recursive relation based on Gray code transformation
        return (1 << (msb + 1)) - 1 - minimumOneBitOperations(n ^ (1 << msb));
    }
}
