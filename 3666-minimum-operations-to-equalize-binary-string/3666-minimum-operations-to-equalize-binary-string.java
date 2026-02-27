class Solution {
    public int minOperations(String s, int k) {
        
        int n = s.length();
        int zeroCount = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '0') {
                zeroCount++;
            }
        }
        if(zeroCount == 0) return 0;

        int[] steps = new int[n + 1];
        Arrays.fill(steps, -1);

        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();

        for(int i = 0; i <= n; i++) {
            if((i & 1) == 0) even.add(i);
            else odd.add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(zeroCount);
        steps[zeroCount] = 0;

        if((zeroCount & 1) == 0) even.remove(zeroCount);
        else odd.remove(zeroCount);

        while(!queue.isEmpty()) {
            int curr = queue.poll();

            int minReach = curr + k - 2 * Math.min(k, curr);
            int maxReach = curr + k - 2 * Math.max(0, k - (n - curr));

            TreeSet<Integer> target = (minReach % 2 == 0) ? even : odd;

            Integer next = target.ceiling(minReach);

            while(next != null && next <= maxReach) {
                steps[next] = steps[curr] + 1;
                if(next == 0) {
                    return steps[next];
                }

                queue.offer(next);
                target.remove(next);

                next = target.ceiling(minReach);
            }
        }
        return -1;
    }
}