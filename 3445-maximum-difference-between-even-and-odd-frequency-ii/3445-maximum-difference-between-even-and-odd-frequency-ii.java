
public class Solution {
    public int maxDifference(String s, int k) {
        int n = s.length();
        int ans = Integer.MIN_VALUE;

        for (char a = '0'; a <= '4'; a++) {
            for (char b = '0'; b <= '4'; b++) {
                if (a == b) continue;


                int[][] best = new int[2][2];
                for (int i = 0; i < 2; i++)
                    Arrays.fill(best[i], Integer.MAX_VALUE / 2);

                List<Integer> prefixA = new ArrayList<>();
                List<Integer> prefixB = new ArrayList<>();
                prefixA.add(0);
                prefixB.add(0);

                int l = 0;
                for (int r = 0; r < n; r++) {
                    prefixA.add(prefixA.get(r) + (s.charAt(r) == a ? 1 : 0));
                    prefixB.add(prefixB.get(r) + (s.charAt(r) == b ? 1 : 0));


                    while (r - l + 1 >= k &&
                           prefixA.get(l) < prefixA.get(r+1) &&
                           prefixB.get(l) < prefixB.get(r+1)) {
                        int pa = prefixA.get(l) % 2;
                        int pb = prefixB.get(l) % 2;
                        best[pa][pb] = Math.min(best[pa][pb],
                                                prefixA.get(l) - prefixB.get(l));
                        l++;
                    }


                    int curA = prefixA.get(r+1), curB = prefixB.get(r+1);
                    int neededPA = 1 - (curA % 2), neededPB = curB % 2;
                    int candidate = (curA - curB) - best[neededPA][neededPB];
                    ans = Math.max(ans, candidate);
                }
            }
        }

        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
