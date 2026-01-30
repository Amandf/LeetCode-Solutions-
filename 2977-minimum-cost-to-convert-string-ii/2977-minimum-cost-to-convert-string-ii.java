class Solution {

   static class Node {
       String s;
       int cost;
       Node(String s, int cost) {
           this.s = s;
           this.cost = cost;
       }
   }


   public long minimumCost(String source, String target,
                           String[] original, String[] changed, int[] cost) {


       int n = source.length();
       long INF = (long) 1e18;


        // Group substring by length
       Map<Integer, List<Integer>> byLength = new HashMap<>();
       for (int i = 0; i < original.length; i++) {
           int len = original[i].length();
           byLength.computeIfAbsent(len, k -> new ArrayList<>()).add(i);
       }


        // For each length, build graph & run Floyd
       Map<Integer, Map<String, Map<String, Long>>> minSubCost = new HashMap<>();


       for (int len : byLength.keySet()) {
           List<Integer> ids = byLength.get(len);


           Set<String> nodes = new HashSet<>();
           for (int id : ids) {
               nodes.add(original[id]);
               nodes.add(changed[id]);
           }


           Map<String, Map<String, Long>> dist = new HashMap<>();
           for (String a : nodes) {
               dist.put(a, new HashMap<>());
               for (String b : nodes) {
                   dist.get(a).put(b, a.equals(b) ? 0L : INF);
               }
           }


           for (int id : ids) {
               String u = original[id];
               String v = changed[id];
               dist.get(u).put(v,
                   Math.min(dist.get(u).get(v), (long) cost[id]));
           }


           for (String k : nodes)
               for (String i : nodes)
                   for (String j : nodes) {
                       long nd = dist.get(i).get(k) + dist.get(k).get(j);
                       if (nd < dist.get(i).get(j)) {
                           dist.get(i).put(j, nd);
                       }
                   }


           minSubCost.put(len, dist);
       }


        // Step 4: DP
       long[] dp = new long[n + 1];
       Arrays.fill(dp, INF);
       dp[n] = 0;


       for (int i = n - 1; i >= 0; i--) {


           if(source.charAt(i) == target.charAt(i)){
               dp[i] = dp[i+1];
           }


           for (int len : minSubCost.keySet()) {
               if (i + len > n) continue;


               String subS = source.substring(i, i + len);
               String subT = target.substring(i, i + len);


               Map<String, Map<String, Long>> dist = minSubCost.get(len);
               if (!dist.containsKey(subS)) continue;


               long c = dist.get(subS).getOrDefault(subT, INF);
               if (c < INF) {
                   dp[i] = Math.min(dp[i], c + dp[i + len]);
               }
           }
       }


       return dp[0] >= INF ? -1 : dp[0];
   }
}


