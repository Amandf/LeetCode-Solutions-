import java.util.*;

class Solution {

    static class Event {
        double x;
        int type;
        double y1, y2;

        Event(double x, int type, double y1, double y2) {
            this.x = x;
            this.type = type;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE, high = Double.MIN_VALUE;

        for (int[] s : squares) {
            low = Math.min(low, s[1]);
            high = Math.max(high, s[1] + s[2]);
        }

        double totalArea = areaBelow(squares, high);
        double target = totalArea / 2.0;

        for (int i = 0; i < 60; i++) {
            double mid = (low + high) / 2;
            if (areaBelow(squares, mid) < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private double areaBelow(int[][] squares, double y) {
        List<double[]> rects = new ArrayList<>();

        for (int[] s : squares) {
            double bottom = s[1];
            double top = Math.min(s[1] + s[2], y);
            if (top > bottom) {
                rects.add(new double[]{s[0], s[0] + s[2], bottom, top});
            }
        }
        if (rects.isEmpty()) return 0;
        return unionArea(rects);
    }

    private double unionArea(List<double[]> rects) {
        List<Event> events = new ArrayList<>();

        for (double[] r : rects) {
            events.add(new Event(r[0], 1, r[2], r[3]));
            events.add(new Event(r[1], -1, r[2], r[3]));
        }

        events.sort(Comparator.comparingDouble(e -> e.x));

        List<double[]> active = new ArrayList<>();
        double prevX = events.get(0).x;
        double area = 0;

        for (Event e : events) {
            double dx = e.x - prevX;
            if (dx > 0) {
                area += dx * mergedY(active);
            }

            if (e.type == 1) {
                active.add(new double[]{e.y1, e.y2});
            } else {
                for (int i = 0; i < active.size(); i++) {
                    if (active.get(i)[0] == e.y1 && active.get(i)[1] == e.y2) {
                        active.remove(i);
                        break;
                    }
                }
            }
            prevX = e.x;
        }
        return area;
    }

    private double mergedY(List<double[]> intervals) {
        if (intervals.isEmpty()) return 0;

        intervals.sort(Comparator.comparingDouble(a -> a[0]));
        double total = 0;
        double start = intervals.get(0)[0];
        double end = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            double s = intervals.get(i)[0];
            double e = intervals.get(i)[1];
            if (s > end) {
                total += end - start;
                start = s;
                end = e;
            } else {
                end = Math.max(end, e);
            }
        }
        total += end - start;
        return total;
    }
}
