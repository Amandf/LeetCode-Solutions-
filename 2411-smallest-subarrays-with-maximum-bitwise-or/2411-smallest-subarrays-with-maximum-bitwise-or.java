class Solution {
    public int[] smallestSubarrays(int[] nums) {
     int n = nums.length;
     int [] answer = new int[n];
     int [] lastSeen = new int[32];

     Arrays.fill(lastSeen, -1);

     for (int i = n - 1; i >= 0; i--) {
        for (int b = 0; b < 32; b++) {
            if ((nums[i] & (1 << b)) != 0) {
                lastSeen[b] = i;
            }
        }

        int maxReach = i;
        for (int b = 0; b < 32; b++) {
            if (lastSeen[b] != -1) {
                maxReach = Math.max(maxReach, lastSeen[b]);
            }
        } 
        answer[i] = maxReach - i + 1;
     }

     return answer;
    }
}