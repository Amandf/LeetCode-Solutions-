class Fancy {

    List <Long> arr;
    long mul = 1;
    long add = 0;
    int MOD = 1000000007;

    public Fancy() {
        arr = new ArrayList<>();
    }
    
    public void append(int val) {
        long x = (val - add + MOD) % MOD;
        x = (x * modInverse(mul)) % MOD;
        arr.add(x);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if(idx >= arr.size()) return -1;

        long val = arr.get(idx);
        return (int) ((val * mul + add) % MOD);
    }
    
    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while(exp > 0) {
            if((exp & 1) == 1) 
              result = (result * base) % MOD;

            base = (base * base) % MOD;
            exp >>= 1;  
        }
        return result;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */