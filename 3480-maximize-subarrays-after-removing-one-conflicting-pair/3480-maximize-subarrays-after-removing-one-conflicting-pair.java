
class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {

        List<List<int[]>> conflictsAtRight = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            conflictsAtRight.add(new ArrayList<>());
        }

        for (int i = 0; i < conflictingPairs.length; i++) {
            int u = conflictingPairs[i][0];
            int v = conflictingPairs[i][1];
            int minVal = Math.min(u, v);
            int maxVal = Math.max(u, v);
            conflictsAtRight.get(maxVal).add(new int[]{minVal, i});
        }


        int[] currentMaxLeft = {0, -1};
        int[] currentSecondMaxLeft = {0, -1};

        
        long[] gains = new long[conflictingPairs.length];
        
        long totalValidSubarraysWithoutRemoval = 0;

        for (int right = 1; right <= n; right++) {
        
            for (int[] conflict : conflictsAtRight.get(right)) {
                int leftVal = conflict[0];
                int pairIdx = conflict[1];

                if (leftVal > currentMaxLeft[0]) {
                    currentSecondMaxLeft[0] = currentMaxLeft[0];
                    currentSecondMaxLeft[1] = currentMaxLeft[1];
                    currentMaxLeft[0] = leftVal;
                    currentMaxLeft[1] = pairIdx;
                } else if (leftVal > currentSecondMaxLeft[0]) {
                    currentSecondMaxLeft[0] = leftVal;
                    currentSecondMaxLeft[1] = pairIdx;
                }
            }


            totalValidSubarraysWithoutRemoval += (right - currentMaxLeft[0]);


            if (currentMaxLeft[1] != -1) {

                gains[currentMaxLeft[1]] += (currentMaxLeft[0] - currentSecondMaxLeft[0]);
            }
        }

        long maxGain = 0;
        for (long gain : gains) {
            maxGain = Math.max(maxGain, gain);
        }

        return totalValidSubarraysWithoutRemoval + maxGain;
    }
}