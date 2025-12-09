class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
     String ans = "";
     int[] freq = new int[26];
     for (char c : s.toCharArray())
     freq[c - 'a']++;

    List<Character> candidates = new ArrayList<>();
    for (char c = 'a'; c <= 'z'; c++)
    if (freq[c - 'a'] >= k)
    candidates.add(c);

    Queue<String> queue = new ArrayDeque<>();
    queue.offer("");

    while (!queue.isEmpty()) {
        String curr = queue.poll();
        if (curr.length() * k > s.length())
        break;

        for (char c : candidates) {
            String next = curr  + c;
            if (isSubseqRepeatedK(next, s, k)) {
                queue.offer(next);
                ans = next;
            }
        }
    }    
    return ans;
    }
    private boolean isSubseqRepeatedK(String subseq, String s, int k) {
        int i = 0;
        for (char ch : s.toCharArray()) {
            if (ch == subseq.charAt(i)) {
                i++;
                if (i == subseq.length()) {
                    i = 0;
                    if (--k == 0)
                    return true;
                }
            }
        }
        return false; 
    }
}