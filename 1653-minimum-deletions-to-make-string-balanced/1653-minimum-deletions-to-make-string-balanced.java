//Keeping count of 'b'
//For each 'a', choose min cost: delete all previous 'b's OR delete this 'a'
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int bcount = 0;
        int ans = 0;

        for(char ch : s.toCharArray()) {
            if(ch=='a') {
                //Either delete all 'b's before this 'a', or delete this 'a'
                ans = Math.min(bcount, 1+ans);
            }
            else bcount++;
        }
        return ans;
    }
}