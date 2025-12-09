class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        double maxDiagonal = 0;
        int maxArea = 0;

        for (int[] rect : dimensions) {
            int l = rect[0], w = rect[1];
            double diagonal = Math.sqrt(l * l + w * w);
            int area = l * w;

            if (diagonal > maxDiagonal) {
                maxDiagonal = diagonal;
                maxArea = area;
            } else if (diagonal == maxDiagonal) {
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}