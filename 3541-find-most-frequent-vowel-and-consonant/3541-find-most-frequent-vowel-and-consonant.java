class Solution {
    public int maxFreqSum(String s) {
       int[] freq = new int[26];
       for (char c : s.toCharArray()) {
        freq[c - 'a']++;
       }

       int maxVowel = 0;
       int maxConsonant = 0; 

       for (int i = 0; i < 26; i++) {
        char ch = (char) (i + 'a');
        int f = freq[i];
        if (isVowel(ch)) {
            if (f > maxVowel) maxVowel = f;
        } else {
            if (f > maxConsonant) maxConsonant = f;
        } 
       }
       return maxVowel + maxConsonant;
    }
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}