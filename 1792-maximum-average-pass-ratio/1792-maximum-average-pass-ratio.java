class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b[0], a[0])
        );

        for (int[] c : classes) {
            int p = c[0], t = c[1];
            pq.offer(new double[]{gain(p, t), p, t});
        }

        for (int i = 0; i < extraStudents; i++) {
            double[] top = pq.poll();
            int p = (int) top[1];
            int t = (int) top[2];
            p++;
            t++;
            pq.offer(new double[]{gain(p, t), p, t});
        }

        double total = 0.0;
        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            int p = (int) curr[1];
            int t = (int) curr[2];
            total += (double) p / t;
        }
        return total / classes.length;
    }

    private double gain(int p, int t){
        return ((double)(p + 1) / (t + 1)) - ((double)p / t);
    }
}