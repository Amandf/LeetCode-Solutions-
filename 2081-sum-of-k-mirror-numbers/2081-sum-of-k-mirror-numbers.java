class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int found = 0;

        for (int len = 1; found < n; len++) {
            int start = (int) Math.pow(10, (len - 1) / 2);
            int end = (int) Math.pow(10, (len + 1) / 2);

            for (int half = start; half < end && found < n; half++) {
                long num = makePalindrome(half, len % 2 == 1);
                if (isPalindromeInBase(num, k)) {
                    sum += num;
                    found++;
                }
            }
        }
        return sum;
    }

    private long makePalindrome(int half, boolean odd) {
        long pal = half;
        int tmp = odd ? half /10 : half;
        while (tmp > 0) {
            pal = pal * 10 + (tmp % 10);
            tmp /= 10;
        }
        return pal;
    }

    private boolean isPalindromeInBase(long num, int base) {
        StringBuilder sb = new StringBuilder();
        long t = num;
        while ( t > 0) {
            sb.append((char)('0' + (t % base)));
            t /= base;
        }
        String s = sb.toString();
        return s.equals(sb.reverse().toString());
    }
}