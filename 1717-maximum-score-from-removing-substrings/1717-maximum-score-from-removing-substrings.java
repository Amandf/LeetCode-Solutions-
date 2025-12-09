class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x >= y) {
            return removeAndScore(s, 'a', 'b', x, y);
        } else {
            return removeAndScore(s, 'b', 'a', y, x);
        }
    }

    private int removeAndScore(String s, char first, char second, int firstScore, int secondScore) {
        Deque<Character> stack = new ArrayDeque<>();
        int score = 0;

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == first && c == second) {
                stack.pop();
                score += firstScore;
            } else {
                stack.push(c);
            }
        }
        StringBuilder remaining = new StringBuilder();
        while (!stack.isEmpty()) {
            remaining.append(stack.removeLast());
        }

        for (char c : remaining.toString().toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == second && c == first) {
                stack.pop();
                score += secondScore;
            } else {
                stack.push(c);
            }
        }
        return score;
    }
}