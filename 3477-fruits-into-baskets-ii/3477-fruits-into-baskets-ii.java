class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
     int n = fruits.length;
     boolean[] used = new boolean [n];
     int unplaced = n;

     for (int fruit: fruits) {
        for (int j = 0; j < n; j++) {
            if (!used[j] && baskets[j] >= fruit) {
                used[j] = true;
                unplaced--;
                break;
            }
        }
     }   
     return unplaced;
    }
}