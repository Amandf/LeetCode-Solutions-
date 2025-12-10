import java.util.*;

public class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        if (k > n) return new int[0];
        int outLen = n - k + 1;
        int[] ans = new int[outLen];

        Map<Integer, Integer> freq = new HashMap<>();

        // comparator: higher freq first; if freq equal, larger value first
        Comparator<Integer> comp = (a, b) -> {
            int fa = freq.getOrDefault(a, 0);
            int fb = freq.getOrDefault(b, 0);
            if (fa != fb) return Integer.compare(fb, fa); // descending freq
            return Integer.compare(b, a);                 // descending value
        };

        TreeSet<Integer> order = new TreeSet<>(comp);

        // helper to increment value v
        Runnable dummy = () -> {}; // unused, just to satisfy format if needed
        // add v into freq/order
        BiConsumerAdd addFunc = new BiConsumerAdd(freq, order);
        BiConsumerRemove removeFunc = new BiConsumerRemove(freq, order);

        // Build first k-1 elements
        for (int i = 0; i < k - 1; i++) {
            addFunc.add(nums[i]);
        }

        int idx = 0;
        for (int i = k - 1; i < n; i++) {
            // add new element entering window
            addFunc.add(nums[i]);

            // compute x-sum for current window
            long sum = 0;
            int taken = 0;
            for (int val : order) { // TreeSet iteration in comparator order (highest first)
                if (taken >= x) break;
                int f = freq.getOrDefault(val, 0);
                if (f > 0) {
                    sum += 1L * f * val;
                    taken++;
                }
            }
            ans[idx++] = (int) sum;

            // remove element leaving window
            int outVal = nums[i - (k - 1)];
            removeFunc.remove(outVal);
        }

        return ans;
    }

    // helper classes to encapsulate add/remove logic (avoid inner-class capturing issues)
    static class BiConsumerAdd {
        private final Map<Integer, Integer> freq;
        private final TreeSet<Integer> order;
        BiConsumerAdd(Map<Integer,Integer> freq, TreeSet<Integer> order) {
            this.freq = freq;
            this.order = order;
        }
        void add(int v) {
            int old = freq.getOrDefault(v, 0);
            if (old > 0) {
                // remove old entry (ordering depends on freq)
                order.remove(v);
            }
            int nw = old + 1;
            freq.put(v, nw);
            order.add(v);
        }
    }

    static class BiConsumerRemove {
        private final Map<Integer, Integer> freq;
        private final TreeSet<Integer> order;
        BiConsumerRemove(Map<Integer,Integer> freq, TreeSet<Integer> order) {
            this.freq = freq;
            this.order = order;
        }
        void remove(int v) {
            int old = freq.getOrDefault(v, 0);
            if (old == 0) return;
            order.remove(v); // remove old ordering
            int nw = old - 1;
            if (nw == 0) {
                freq.remove(v);
            } else {
                freq.put(v, nw);
                order.add(v); // re-add with new frequency
            }
        }
    }

    // quick local test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,2,2,1,3,2};
        int k = 3, x = 2;
        int[] res = sol.findXSum(nums, k, x);
        System.out.println(Arrays.toString(res));
        // For this example you can verify outputs manually.
    }
}
