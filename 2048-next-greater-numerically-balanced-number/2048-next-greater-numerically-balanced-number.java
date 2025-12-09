class Solution {
    public int nextBeautifulNumber(int n) {
        int candidate = n + 1;
        while (true) {
            if (isBalanced(candidate)) {
                return candidate;
            }
            candidate++;
        }
    }

    private boolean isBalanced(int x) {
        int[] count = new int[10];
        int temp = x;
        while (temp > 0) {
            int d = temp % 10;
            if (d == 0) {
                // A digit 0 cannot appear (should appear exactly 0 times but then it must not be present)
                return false;
            }
            count[d]++;
            temp /= 10;
        }
        // Now check that for each digit d that appears, count[d] == d
        for (int d = 1; d <= 9; d++) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }
        return true;
    }
}
