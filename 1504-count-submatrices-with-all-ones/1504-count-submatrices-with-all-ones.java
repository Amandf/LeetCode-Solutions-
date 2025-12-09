class Solution {
    public int numSubmat(int[][] mat) {
       int n = mat.length;
       int m = mat[0].length;
       int[] height = new int[m];
       long ans = 0L;

       for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            height[j] = (mat[i][j] == 1) ? height[j] + 1: 0;
        }

        int[] dp = new int[m];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int j = 0; j < m; j++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[j]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                dp[j] = height[j] * (j + 1);
            } else {
                int prev = stack.peek();
                dp[j] = dp[prev] + height[j] * (j - prev);
            }

            stack.push(j);
            ans += dp[j];
        }  
       } 
       return (int) ans;
    }
}