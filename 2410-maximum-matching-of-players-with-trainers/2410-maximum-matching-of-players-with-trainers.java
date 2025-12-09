class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int ans = 0;
        int i = 0;

        for (int t : trainers) {
            if (i < players.length && players[i] <= t) {
                ans++;
                i++;
            }

            if (i == players.length) break;
        }

        return ans;
    }
}