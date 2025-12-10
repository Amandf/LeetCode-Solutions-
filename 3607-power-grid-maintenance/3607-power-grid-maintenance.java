
public class Solution {
    // Union-Find (DSU) structure
    static class DSU {
        int[] parent;
        int[] rank;
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);

        // 1. Build components
        for (int[] edge : connections) {
            int u = edge[0], v = edge[1];
            dsu.union(u, v);
        }

        // 2. For each root component, maintain a sorted set of currently online station IDs
        Map<Integer, TreeSet<Integer>> compOnline = new HashMap<>();

        // Initialize: each station 1..c is online
        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            compOnline.computeIfAbsent(root, r -> new TreeSet<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();

        // 3. Process queries
        for (int[] q : queries) {
            int type = q[0], x = q[1];
            int root = dsu.find(x);

            if (type == 1) {
                // maintenance check
                TreeSet<Integer> set = compOnline.getOrDefault(root, new TreeSet<>());
                if (set.contains(x)) {
                    // x is online
                    result.add(x);
                } else {
                    // x is offline; pick smallest online in component or -1
                    if (set.isEmpty()) {
                        result.add(-1);
                    } else {
                        result.add(set.first());
                    }
                }
            } else if (type == 2) {
                // take station x offline
                TreeSet<Integer> set = compOnline.get(root);
                if (set != null) {
                    set.remove(x);
                }
            }
        }

        // convert result list to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Example test
    public static void main(String[] args) {
        Solution sol = new Solution();

        int c = 5;
        int[][] connections = { {1,2}, {2,3}, {3,4}, {4,5} };
        int[][] queries = { {1,3}, {2,1}, {1,1}, {2,2}, {1,2} };

        int[] ans = sol.processQueries(c, connections, queries);
        System.out.println(Arrays.toString(ans));  // Expected: [3,2,3]
    }
}
