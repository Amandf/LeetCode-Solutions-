class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelError = new HashMap<>();

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        for (String w : wordlist) {
            exact.add(w);

            String lower = w.toLowerCase();
            caseInsensitive.putIfAbsent(lower, w);

            String mask = maskVowels(lower, vowels);
            vowelError.putIfAbsent(mask, w);
        }

        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q)) {
                ans[i] = q;
            }
            else {
                String lower = q.toLowerCase();
                if (caseInsensitive.containsKey(lower)) {
                    ans[i] = caseInsensitive.get(lower);
                }
                else {
                    String mask = maskVowels(lower, vowels);
                    if (vowelError.containsKey(mask)) {
                        ans[i] = vowelError.get(mask);
                    }
                    else {
                        ans[i] = "";
                    }
                }
            }
        }
        return ans;
    }
    private String maskVowels(String word, Set<Character> vowels) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}