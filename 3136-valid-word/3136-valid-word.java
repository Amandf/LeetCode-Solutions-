class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) return false;

        boolean hasVowel = false, hasConsonant = false;
        String vowels = "aeiouAEIOU";

        for (char c : word.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) return false;

            if (Character.isLetter(c)) {
                if (vowels.indexOf(c) >= 0) hasVowel = true;
                else hasConsonant = true;
            }
        }
        return hasVowel && hasConsonant;
    }
}