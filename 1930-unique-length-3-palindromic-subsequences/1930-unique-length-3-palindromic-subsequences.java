class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        // initialize 
        for (int i = 0; i < 26; i++) {
            first[i] = -1;
            last[i] = -1;
        }

        // record first and last positions
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        int result = 0;

        // for each letter as the outer character
        for (int c = 0; c < 26; c++) {
            if (first[c] != -1 && last[c] != -1 && first[c] < last[c]) {
                boolean[] seen = new boolean[26];

                for (int i = first[c] + 1; i < last[c]; i++) {
                    seen[s.charAt(i) - 'a'] = true;
                }

                // count distinct inner characters
                for (boolean b : seen) if (b) result++;
            }
        }

        return result;
    }
}
