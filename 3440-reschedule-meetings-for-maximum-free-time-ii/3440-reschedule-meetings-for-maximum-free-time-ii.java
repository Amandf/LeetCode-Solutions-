class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) { 
        int n = startTime.length;

        int[] gapArr = new int[n + 1];
        gapArr[0] = startTime[0];

        for (int i = 1; i < n; i++) {
            gapArr[i] = startTime[i] - endTime[i - 1];
        }

        
        gapArr[n] = eventTime - endTime[n - 1];

        int[] leftMax = new int[n + 2];   
        int[] rightMax = new int[n + 2];

        leftMax[0] = gapArr[0];
        for (int i = 1; i <= n; i++) {
           
            leftMax[i] = Math.max(gapArr[i], leftMax[i - 1]);
        }

        rightMax[n] = gapArr[n];
        for (int i = n - 1; i >= 0; i--) {
            rightMax[i] = Math.max(gapArr[i], rightMax[i + 1]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int occupiedSpace = endTime[i] - startTime[i];
            int currAns = gapArr[i] + gapArr[i + 1];

            
            boolean canMove = false;
            if (i - 1 >= 0 && leftMax[i - 1] >= occupiedSpace) canMove = true;
            if (i + 2 <= n && rightMax[i + 2] >= occupiedSpace) canMove = true;

            if (canMove) currAns += occupiedSpace;

            ans = Math.max(currAns, ans);
        }

        return ans;
    }
}
