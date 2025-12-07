class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        for (int num : count.keySet()) {
            if (count.containsKey(num + 1)) {
                ans = Math.max(ans, count.get(num) + count.get(num + 1));
            }
        }

        return ans;
    }
}