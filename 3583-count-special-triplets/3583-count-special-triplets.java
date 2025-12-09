/* -------------------------------- HashMap Approach -------------------------------- */
class Solution {
    public int specialTriplets(int[] nums) {

        // one thing - counting arrays
        // leftcount[] = new int[100001];
        // rightcount[]
        
        HashMap<Integer, Integer> leftcount = new HashMap<>();
        HashMap<Integer, Integer> rightcount = new HashMap<>();

        long ans = 0;
        int mod = 1000000007;

        for(int num : nums) {
            rightcount.put(num, rightcount.getOrDefault(num, 0)+1);
        }

        for(int ind=0; ind<nums.length; ind++) {

            int num = nums[ind];
            rightcount.put(num, rightcount.get(num)-1);
            if(rightcount.get(num)==0)
                rightcount.remove(num);
            int left = leftcount.containsKey(num*2) ?  leftcount.get(num*2) : 0;
            int right = rightcount.containsKey(num*2) ? rightcount.get(num*2) : 0;

            ans = (ans%mod + ((long)left*right)%mod)%mod;

            leftcount.put(num, leftcount.getOrDefault(num,0)+1);

        }

        return (int)ans;

    }
}



