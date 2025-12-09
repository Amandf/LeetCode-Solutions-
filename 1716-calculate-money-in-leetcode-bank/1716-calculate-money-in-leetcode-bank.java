class Solution {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;

        // Sum of weekly complete weeks
        // Week i starts with (i+1) and has sum = start*7 + 21  (since 1+2+..+7 = 28)
        // Sum of weeks = (1+2+..+weeks)*7 + weeks*21
        int total = (weeks * (weeks + 1) / 2) * 7 + weeks * 21;

        // Remaining days in next week
        int start = weeks + 1;  // First deposit for the next week
        for (int i = 0; i < days; i++) {
            total += start + i;
        }

        return total;
    }
}
