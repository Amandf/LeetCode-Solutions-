class Solution {
    int[] xor, tin, tout;
    int timer = 0;
    List<Integer>[] tree;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        xor = new int[n];
        tin = new int[n];
        tout = new int[n];
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        dfs(0, -1, nums);

        int totalXor = xor[0];
        int res = Integer.MAX_VALUE;

        // Consider all edge pairs
        for (int[] e1 : edges) {
            int a = isAncestor(e1[0], e1[1]) ? e1[1] : e1[0];
            for (int[] e2 : edges) {
                if (e1 == e2) continue;
                int b = isAncestor(e2[0], e2[1]) ? e2[1] : e2[0];

                if (isAncestor(a, b)) {
                    int x = xor[b];
                    int y = xor[a] ^ xor[b];
                    int z = totalXor ^ xor[a];
                    res = Math.min(res, maxDiff(x, y, z));
                } else if (isAncestor(b, a)) {
                    int x = xor[a];
                    int y = xor[b] ^ xor[a];
                    int z = totalXor ^ xor[b];
                    res = Math.min(res, maxDiff(x, y, z));
                } else {
                    int x = xor[a];
                    int y = xor[b];
                    int z = totalXor ^ xor[a] ^ xor[b];
                    res = Math.min(res, maxDiff(x, y, z));
                }
            }
        }

        return res;
    }

    void dfs(int node, int parent, int[] nums) {
        xor[node] = nums[node];
        tin[node] = ++timer;
        for (int child : tree[node]) {
            if (child == parent) continue;
            dfs(child, node, nums);
            xor[node] ^= xor[child];
        }
        tout[node] = ++timer;
    }

    boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    int maxDiff(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min;
    }
}
