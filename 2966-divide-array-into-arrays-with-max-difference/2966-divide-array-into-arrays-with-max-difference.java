class Solution {
    public int[][] divideArray(int[] nums, int k) {
     Arrays.sort(nums);
     int n = nums.length;
     int groups = n / 3;
     int [] [] result = new int[groups] [3];

     for (int i = 0; i < groups; i++) {
        int idx = i * 3;
        int a = nums[idx], b = nums[idx + 1], c = nums[idx + 2];
        if ( c - a > k) {
            return new int[0][];
        }
        result[i] = new int[] {a, b, c}; 
     }   

     return result;
    }
}