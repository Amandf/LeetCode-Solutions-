class Solution {
    public boolean isTrionic(int[] nums) {
        int i=1;

        // Cheking for the increasing order
        while(i<nums.length && nums[i] > nums[i-1]) {
            i++;
        }
        // If we could not start or fail to go to next part, return false 
        if(i==1 || i==nums.length) {
            return false;
        }

        // Checking for decreasing order 
        while(i<nums.length && nums[i] < nums[i-1]) {
            i++;
        }

        // If we fail to go to last part return false
        if(i==nums.length) {
            return false;
        }

        // Checking for increasing order
        while(i<nums.length && nums[i] > nums[i-1]) {
            i++;
        }


        //If er traversed all parts properly and are at the end of the array, return true 
        return (i==nums.length) ? true : false;


    }
}

// Increasing -- decreasing -- incresing 