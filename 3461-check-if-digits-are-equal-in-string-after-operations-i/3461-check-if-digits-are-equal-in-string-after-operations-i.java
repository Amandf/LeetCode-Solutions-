class Solution {
    public boolean hasSameDigits(String s) {
        if (s.length() < 2) return false; // Handle edge cases

        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        
        while (n > 2) {
            StringBuilder newsb = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                newsb.append(((sb.charAt(i) - '0') + (sb.charAt(i + 1) - '0')) % 10);
            }
            sb = new StringBuilder(newsb);
            n = sb.length();
        }
        
        return sb.charAt(0) == sb.charAt(1);
    }
}