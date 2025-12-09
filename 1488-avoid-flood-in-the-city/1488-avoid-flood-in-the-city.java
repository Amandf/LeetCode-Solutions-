import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        
        Map<Integer, Integer> full = new HashMap<>();  // lake -> last day filled
        TreeSet<Integer> dryDays = new TreeSet<>();   // indices of days we can dry

        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                dryDays.add(i);  // can dry later
                ans[i] = 1;      // default, will update if we dry a specific lake
            } else {
                ans[i] = -1; // raining day
                if (full.containsKey(lake)) {
                    Integer dryDay = dryDays.higher(full.get(lake));
                    if (dryDay == null) {
                        return new int[0]; // cannot prevent flood
                    }
                    ans[dryDay] = lake;   // dry this lake
                    dryDays.remove(dryDay);
                }
                full.put(lake, i); // mark this lake as full
            }
        }
        return ans;
    }
}
