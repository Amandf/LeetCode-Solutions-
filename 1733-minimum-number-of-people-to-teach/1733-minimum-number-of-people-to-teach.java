class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
     Set<Integer> needTeaching = new HashSet<>();

     for (int[] pair : friendships) {
        int u = pair[0], v = pair[1];
        if (!canCommunicate(languages[u - 1], languages[v - 1])) {
            needTeaching.add(u);
            needTeaching.add(v);
        }
     }

     if (needTeaching.isEmpty()) {
        return 0;
     }   

     int[] freq = new int[n + 1];
     for (int user : needTeaching) {
        for (int lang : languages[user - 1]) {
            freq[lang]++;
        }
     }

     int maxKnown = 0;
     for (int lang = 1; lang <= n; lang++) {
        maxKnown = Math.max(maxKnown, freq[lang]);
     }
     return needTeaching.size() - maxKnown;
    }

    private boolean canCommunicate(int[] langs1, int[] langs2) {
        Set<Integer> s = new HashSet<>();
        for (int l : langs1) s.add(l);
        for (int l : langs2) if (s.contains(l)) return true;
        return false;
    }
}