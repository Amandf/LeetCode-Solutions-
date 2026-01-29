class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long ans = 0;

        // source.length()==n, original = k
        long mincost[][] = new long[26][26];


        for(long c[] : mincost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }

        for (int i = 0; i<original.length; i++) {
            int src = original[i]-'a';
            int dest = changed[i]-'a';
            mincost[src][dest] = Math.min(mincost[src][dest], cost[i]);
        }

        //Floydd warshall algo
        for(int k=0; k <26; k++) {
            for(int i =0; i<26; i++) {
                for(int j=0; j<26; j++) {
                    mincost[i][j] = Math.min(mincost[i][j], mincost[i][k] + mincost[k][j]);
                }
            }
        }
        for (int i=0; i<source.length(); i++) {
            int s = source.charAt(i)-'a';
            int d = target.charAt(i)-'a';
            if(s==d)
               continue;
            if(mincost[s][d]==Integer.MAX_VALUE)
               return -1;
            ans += mincost[s][d];      
        }
        return ans;
    }
}
