class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();

        for (int num : nums) {
            stack.addLast(num);

            while (stack.size() >= 2) {
                int y = stack.removeLast();
                int x = stack.removeLast();
                int g = gcd(x, y);
                if (g == 1) {
                    stack.addLast(x);
                    stack.addLast(y);
                    break;
                }
                long lcm = (long)x * y / g;

                int merged = (int) lcm;

                stack.addLast(merged);
            }
        }
        return stack;
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}