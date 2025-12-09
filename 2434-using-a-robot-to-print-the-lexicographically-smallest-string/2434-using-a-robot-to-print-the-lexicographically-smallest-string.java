class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] freq = new int[26];


        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        char minChar = 'a';

        for (char c : s.toCharArray()) {
            stack.push(c);
            freq[c - 'a']--;


            while (minChar <= 'z' && freq[minChar - 'a'] == 0) {
                minChar++;
            }


            while (!stack.isEmpty() && stack.peek() <= minChar) {
                result.append(stack.pop());
            }
        }


        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
