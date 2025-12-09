class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>(); 
        int n = nums.length, start = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                int left = Math.max(i - k, start);
                int right = Math.min(i + k, n - 1);
                while (left <= right) {
                    res.add(left++);
                }
                start = right + 1;
            } 
        }

        return res;
    }
}