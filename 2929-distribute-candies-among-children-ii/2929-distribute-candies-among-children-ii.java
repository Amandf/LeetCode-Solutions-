class Solution {
    public long distributeCandies(int n, int limit) {
    
            long res = comb(n + 3 - 1, 3 -1);

            for (int mask = 1; mask < 8; mask++) {
                int tmpN = n;
                int bits = 0 ;
                for ( int i = 0; i < 3; i++) {
                    if ((mask & (1 << i)) != 0) {
                        tmpN -= (limit + 1);
                        bits++;
                    }
                }
                if (tmpN < 0) continue;
                long ways = comb(tmpN + 3 - 1, 3 - 1 );
                if (bits % 2 == 1) res -= ways;
                else res += ways;
            }
            return res;
        }

        private long comb (int n, int k) {
            if ( n < k) return 0;
            long res = 1;
            for (int i = 0; i < k; i++) {
                res = res * (n - i) / (i + 1);
            }
            return res;
        }
    }
