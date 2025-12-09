class Solution {
    public String largestGoodInteger(String num) {
        String best = "";
        for (int i = 0; i <= num.length() - 3; i++) {
            char c = num.charAt(i);
            if (num.charAt(i + 1) == c && num.charAt(i + 2) == c) {
                String triplet = num.substring(i, i + 3);
                if (triplet.compareTo(best) > 0) {
                    best = triplet;
                }
            }
        }
        return best;
    }
}
