class Solution {
    public int numberOfWays(String corridor) {
        int seat = 0, plant = 0, n = corridor.length(), total = 0, mod = (int) 1e9 + 7;
        long ans = 1;
        boolean isFirstDividerInstalled = false;
        for (int i = 0; i < n; i++) {
            char ch = corridor.charAt(i);
            if (ch == 'S') {
                total++;
                seat++;
            } else if (seat == 0)
                plant++;

            if (seat == 2) {
                if (isFirstDividerInstalled)
                    ans = (ans * (plant + 1) % mod) % mod;

                isFirstDividerInstalled = true;
                seat = 0;
                plant = 0;
            }
        }

        return (total > 0 && total % 2 == 0) ? (int) ans % mod : 0;
    }
}