class Solution {
    public int minimumDeletions(String word, int k) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) count[c - 'a']++;

        int ans = Integer.MAX_VALUE;
        for (int minFreq : count) {
            int deletions = 0;
            for (int freq : count) {
                if (freq < minFreq)
                    deletions += freq;
                else if (freq > minFreq + k)
                    deletions += freq - (minFreq + k);
            }
            ans = Math.min(ans, deletions);
        }
        return ans;
    }
}
