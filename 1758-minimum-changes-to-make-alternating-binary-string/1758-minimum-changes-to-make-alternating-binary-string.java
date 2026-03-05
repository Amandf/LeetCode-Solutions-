class Solution {
    public int minOperations(String s) {
        
        int zerostart = 0, onestart = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(i % 2 == 0) {
                if(ch == '0')
                   onestart++;
                else zerostart++;   
            }
            else {
                if(ch == '0') 
                   zerostart++;
                else onestart++;   
            }
        }
        return Math.min(zerostart, onestart);
    }
}