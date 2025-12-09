class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int count = 0;
        int sum = 0;
        boolean isValidOne = true;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') count++;
            else if(isValidOne) {
                int idx = n - 1 - i;
                sum += Math.pow(2, idx);
                if (sum <= k) {
                    count++;
                } else {
                    isValidOne = false;
                }
            }
        }
        return count;
   }
}   
