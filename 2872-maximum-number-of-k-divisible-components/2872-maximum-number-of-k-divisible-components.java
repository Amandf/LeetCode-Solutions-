class Solution {

    List<Integer>[] graph;
    int answer = 0;
    int k;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.k = k;

        // Build adjacency list
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        dfs(0, -1, values); 
        return answer;
    }

    private long dfs(int node, int parent, int[] values) {
        long sum = values[node];

        for (int child : graph[node]) {
            if (child == parent) continue;   // do not go back
            sum += dfs(child, node, values);
        }

        // If this subtree is divisible by k → one valid component
        if (sum % k == 0) {
            answer++;
            return 0;  // cut here, return 0 to parent
        }

        return sum % k;  // pass remainder upward
    }
}
