class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X = 0;
        for (String op : operations) {
            if (op.contains("++")) {
                X++;
            } else {
                X--;
            }
        }
        return X;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] operations = {"--X", "X++", "X++"};
        System.out.println(sol.finalValueAfterOperations(operations)); // Output: 1
    }
}
