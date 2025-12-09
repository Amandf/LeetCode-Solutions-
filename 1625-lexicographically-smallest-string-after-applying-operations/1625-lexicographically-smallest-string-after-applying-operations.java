import java.util.*;

public class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        String smallest = s;

        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            // Operation 1: Add 'a' to all odd indices
            char[] chars = curr.toCharArray();
            for (int i = 1; i < chars.length; i += 2) {
                chars[i] = (char) ((chars[i] - '0' + a) % 10 + '0');
            }
            String addOp = new String(chars);

            // Operation 2: Rotate right by 'b'
            String rotateOp = curr.substring(curr.length() - b) + curr.substring(0, curr.length() - b);

            if (visited.add(addOp)) queue.offer(addOp);
            if (visited.add(rotateOp)) queue.offer(rotateOp);
        }

        return smallest;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findLexSmallestString("5525", 9, 2)); // Output: 2050
    }
}
