class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int total = numBottles;
        int empty = numBottles;
        int k = numExchange;

        while (empty >= k) {
            empty -= k;
            total++;
            empty++;
            k++;
        }

        return total;
    }
}