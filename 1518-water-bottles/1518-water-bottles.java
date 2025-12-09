class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int total = numBottles;  // total bottles drunk
        int empty = numBottles;  // empty bottles after drinking

        while (empty >= numExchange) {
            int newBottles = empty / numExchange;   // exchange for new bottles
            total += newBottles;                   // drink them
            empty = empty % numExchange + newBottles; // remaining + new empties
        }

        return total;
    }
}
