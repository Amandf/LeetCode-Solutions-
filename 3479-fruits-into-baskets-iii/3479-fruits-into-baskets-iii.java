class SegmentTree{
    int n;
    int[] tree;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[n * 4];
        build(arr, 1, 0,n - 1);
    }

    private void build(int[] arr, int u, int l, int r) {
        if (l == r) {
            tree[u] = arr[l];
        } else {
            int mid = (l + r) >> 1;
            build(arr, u << 1, l, mid);
            build(arr, u << 1 | 1, mid + 1, r);
            tree[u] = Math.max(tree[u << 1], tree[u << 1 | 1]);
        }
    }

    public int query(int u, int l, int r, int target) {
        if (tree[u] < target) return -1;
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tree[u << 1] >= target) {
            return query(u << 1, l, mid, target);
        } else {
            return query(u << 1 | 1, mid + 1, r, target);
        }
    }

    public void update(int u, int l, int r, int idx) {
        if ( l == r) {
            tree[u] = 0;
        } else {
            int mid = (l + r) >> 1;
            if (idx <= mid) {
                update(u << 1, l, mid, idx);
            } else {
                update(u << 1 | 1, mid + 1, r, idx);
            }
            tree[u] = Math.max(tree[u << 1], tree[u << 1 | 1]);
        }
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        SegmentTree st = new SegmentTree(baskets);
        int ans = 0;

        for (int fruit : fruits) {
            int idx = st.query(1, 0, n - 1, fruit);
            if (idx < 0) {
                ans++;
            } else {
                st.update(1, 0, n - 1, idx);
            }
        }
        return ans;
    }
}