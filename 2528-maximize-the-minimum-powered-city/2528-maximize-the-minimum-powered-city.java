import java.util.*;

public class Solution {
    // Main: maximize minimum power across cities
    public long maxPower(int[] stations, int r, long k) {
        int n = stations.length;
        // compute initial power for each city using prefix sums
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + stations[i];

        long[] initial = new long[n];
        for (int i = 0; i < n; i++) {
            int L = Math.max(0, i - r);
            int R = Math.min(n - 1, i + r);
            initial[i] = pref[R + 1] - pref[L];
        }

        // binary search on answer
        long low = 0;
        // upper bound: existing max power + k (placing all on one city), safe high:
        long high = Arrays.stream(initial).max().orElse(0L) + k + 1;
        long ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canAchieve(initial, r, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Check whether we can ensure every city has power >= target using at most k added stations
    private boolean canAchieve(long[] initial, int r, long k, long target) {
        int n = initial.length;
        long[] addDiff = new long[n + 1]; // difference array for added coverage
        long added = 0; // current cumulative added coverage affecting index i

        for (int i = 0; i < n; i++) {
            added += addDiff[i];               // apply any scheduled removals/additions
            long curr = initial[i] + added;
            if (curr < target) {
                long need = target - curr;     // units (stations) needed to raise city i to target
                if (need > k) return false;   // not enough remaining stations
                k -= need;

                // Greedily place these 'need' stations as far right as possible:
                // choose placement position pos = min(n-1, i + r).
                // Their coverage extends from pos - r to pos + r.
                // As argued, pos - r <= i, so the added coverage starts at index i.
                int end = Math.min(n - 1, i + 2 * r); // rightmost index affected by those stations
                added += need;                         // start effect immediately at i
                if (end + 1 < addDiff.length) addDiff[end + 1] -= need; // schedule removal
            }
        }
        return true;
    }

    // small test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxPower(new int[]{1,2,4,5,0}, 1, 2)); // expected 5
        System.out.println(sol.maxPower(new int[]{4,4,4,4}, 0, 3));    // expected 4

        // additional tests
        System.out.println(sol.maxPower(new int[]{0,0,0,0,0}, 1, 5)); // should be 1 (you can place 5 to make minimum 1)
        System.out.println(sol.maxPower(new int[]{1,0,0,1,0}, 2, 3)); // sanity check
    }
}
