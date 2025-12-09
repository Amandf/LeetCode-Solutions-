class Solution {
    public int numSub(String s) {
        long MOD = 1_000_000_007;
        long count = 0;  // length of current block of 1s
        long result = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                count++;
                result = (result + count) % MOD;  // each new '1' extends all previous substrings
            } else {
                count = 0; // reset when we hit '0'
            }
        }

        return (int) result;
    }
}
