class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        int minFruit = Integer.MAX_VALUE;

        for (int fruit : basket1) {
            freq.put(fruit, freq.getOrDefault(fruit, 0) + 1);
            minFruit = Math.min(minFruit, fruit);
        }

        for (int fruit : basket2) {
            freq.put(fruit, freq.getOrDefault(fruit, 0) - 1);
            minFruit = Math.min(minFruit, fruit);
        }

        List<Integer> excess = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int count = entry.getValue();
            if (count % 2 != 0) return - 1;
            for (int i = 0; i < Math.abs(count) / 2; i++) {
                excess.add(entry.getKey());
            }
        }

        Collections.sort(excess);
        long cost = 0;
        for (int i = 0; i < excess.size() / 2; i++) {
            cost += Math.min(excess.get(i), 2 * minFruit);
        }

        return cost;
    }
}