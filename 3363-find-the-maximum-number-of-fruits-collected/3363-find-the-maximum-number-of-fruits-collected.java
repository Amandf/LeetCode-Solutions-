class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n - 1 - i; j++) {
                if (i == j) continue;
                fruits[i][j] = 0;
            }

            int result = 0;
            for (int i = 0; i < n; i++) 
                result += fruits[i][i];
            for (int i = 1; i < n; i++)
                for (int j = i + 1; j < n; j++)
                     fruits[i][j] += Math.max(fruits[i - 1][j - 1], Math.max(fruits[i - 1][j], j + 1 < n ? fruits[i - 1][j + 1] : 0));
                     for (int j = 1; j < n; j++)
                          for (int i = j + 1; i < n; i++)
                               fruits[i][j] += Math.max(fruits[i - 1][j - 1], Math.max(fruits[i][j - 1], i + 1 < n ? fruits[i + 1][j - 1] : 0));

                               return result + fruits[n - 1][n - 2] + fruits[n - 2][n - 1];     
            
        
    }
}