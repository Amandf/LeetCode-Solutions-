class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        
        while (i < n - 1) {
            if (bits[i] == 1) {
                i += 2;  // 2-bit character
            } else {
                i += 1;  // 1-bit character
            }
        }
        
        // If we end exactly on last index, last char is 1-bit
        return i == n - 1;
    }
}
