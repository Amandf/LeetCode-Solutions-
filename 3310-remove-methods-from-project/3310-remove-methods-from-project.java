import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] e : invocations) {
            graph.get(e[0]).add(e[1]);
        }

        boolean[] removed = new boolean[n];
        dfs(k, graph, removed);

        for (int[] e : invocations) {
            int u = e[0], v = e[1];

            if (!removed[u] && removed[v]) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) all.add(i);
                return all;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!removed[i]) res.add(i);
        }

        return res;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] removed) {
        if (removed[node]) return;

        removed[node] = true;

        for (int nei : graph.get(node)) {
            dfs(nei, graph, removed);
        }
    }
}