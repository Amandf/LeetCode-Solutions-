class Solution {
    public long minimumDifference(int[] nums) {
     int n = nums.length / 3;
     long[] leftSum = new long[3 * n];
     long[] rightSum = new long[3 * n];

     PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
     long sum = 0;
     for (int i = 0; i < 2 * n; i++) {
        maxHeap.offer(nums[i]);
        sum += nums[i];
        if (maxHeap.size() > n) {
            sum -= maxHeap.poll();
        }
        leftSum[i] = sum;
     }    

     PriorityQueue<Integer> minHeap = new PriorityQueue<>();
     sum = 0;
     for (int i = 3 * n - 1; i >= n; i--) {
        minHeap.offer(nums[i]);
        sum += nums[i];
        if (minHeap.size() > n) {
            sum -= minHeap.poll();
        }
        rightSum[i] = sum;
     }

     long minDiff = Long.MAX_VALUE;
     for (int i = n - 1; i < 2 * n; i++) {
        long front = leftSum[i];
        long back = rightSum[i + 1];
        minDiff = Math.min(minDiff, front - back);
     }

     return minDiff;
    }
}