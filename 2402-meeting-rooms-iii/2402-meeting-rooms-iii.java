import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> 
            a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        ); 

        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freeRooms.offer(i);
        }

        int[] roomUsage = new int[n];

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                freeRooms.offer((int) busyRooms.poll()[1]);
            }

            if (!freeRooms.isEmpty()) {
                int room = freeRooms.poll();
                busyRooms.offer(new long[]{end, room});
                roomUsage[room]++;
            } else {
                long[] top = busyRooms.poll();
                long newStart = top[0], room = top[1];
                long newEnd = newStart + (end - start);
                busyRooms.offer(new long[]{newEnd, room});
                roomUsage[(int) room]++;
            }
        }

        int maxRoom = 0;
        for (int i = 1; i < n; i++) {
            if (roomUsage[i] > roomUsage[maxRoom]) {
                maxRoom = i;
            }
        }
        return maxRoom;
    }
}
