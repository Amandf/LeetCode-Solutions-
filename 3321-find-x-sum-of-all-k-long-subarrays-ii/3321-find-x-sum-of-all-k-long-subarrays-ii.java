class Solution {
    long sum = 0; // keeps track of sum of (freq * val) for top X elements
    TreeSet<int[]> topX; // stores top X (freq, val) pairs
    TreeSet<int[]> rest; // stores remaining (freq, val) pairs
    Map<Integer, Integer> freq; // frequency map -> value -> count

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        sum = 0;
        freq = new HashMap<>();

        // Custom comparator to sort by (freq ascending, value ascending)
        Comparator<int[]> comp = (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        };

        topX = new TreeSet<>(comp); // top x elements by frequency
        rest = new TreeSet<>(comp); // other elements

        List<Long> resultList = new ArrayList<>();

        int left = 0;

        // Sliding window: move right pointer
        for (int right = 0; right < n; right++) {
            int num = nums[right];

            // Remove old entry if num already exists
            if (freq.containsKey(num)) {
                removePair(new int[]{freq.get(num), num}, x);
            }

            // Increase frequency
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            // Add updated pair
            addPair(new int[]{freq.get(num), num}, x);

            // If window size == k, record answer
            if (right - left + 1 == k) {
                resultList.add(sum);

                // Remove outgoing element (slide window)
                int outNum = nums[left];
                removePair(new int[]{freq.get(outNum), outNum}, x);
                freq.put(outNum, freq.get(outNum) - 1);

                if (freq.get(outNum) == 0) {
                    freq.remove(outNum);
                } else {
                    addPair(new int[]{freq.get(outNum), outNum}, x);
                }

                left++;
            }
        }

        // Convert list to array
        long[] result = new long[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    /**
     * Adds a (freq, value) pair into either topX or rest set.
     */
    void addPair(int[] pair, int x) {
        // If topX not full OR pair is larger than smallest in topX
        if (topX.size() < x || compare(pair, topX.first()) > 0) {
            sum += 1L * pair[0] * pair[1];
            topX.add(pair);

            // Maintain topX size == x
            if (topX.size() > x) {
                int[] smallest = topX.first();
                sum -= 1L * smallest[0] * smallest[1];
                topX.remove(smallest);
                rest.add(smallest);
            }
        } else {
            rest.add(pair);
        }
    }

    /**
     * Removes a (freq, value) pair from topX or rest set.
     */
    void removePair(int[] pair, int x) {
        if (topX.contains(pair)) {
            sum -= 1L * pair[0] * pair[1];
            topX.remove(pair);

            // Move largest from rest into topX (to maintain x elements)
            if (!rest.isEmpty()) {
                int[] largest = rest.last();
                rest.remove(largest);
                topX.add(largest);
                sum += 1L * largest[0] * largest[1];
            }
        } else {
            rest.remove(pair);
        }
    }

    /**
     * Helper to compare two (freq, val) pairs.
     */
    int compare(int[] a, int[] b) {
        if (a[0] != b[0]) return a[0] - b[0];
        return a[1] - b[1];
    }
}