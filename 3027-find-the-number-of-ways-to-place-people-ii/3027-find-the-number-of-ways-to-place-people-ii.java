class Solution {
    public int numberOfPairs(int[][] points) {
     Arrays.sort(points, (a, b) -> {
        if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
        return Integer.compare(b[1], a[1]);
     });

     int ans = 0, n = points.length;

     for (int i = 0; i < n; i++) {
        int yi = points[i][1];
        int maxY = Integer.MIN_VALUE;

        for (int j = i + 1; j < n; ++j) {
            int yj = points[j][1];

            if (yj <= yi && yj > maxY) {
                ans++;
                maxY = yj;
            }
        }
     }  
     return ans;
    }
}