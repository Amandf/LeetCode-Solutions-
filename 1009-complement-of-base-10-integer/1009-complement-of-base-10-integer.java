class Solution {
    public int bitwiseComplement(int n) {
        
        if(n == 0) 
           return 1;

        int hbit = Integer.highestOneBit(n)
<<1;        

        return n ^ (hbit - 1); 
    }
}