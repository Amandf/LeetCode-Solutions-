class Solution {
    public int numberOfBeams(String[] bank) {
        int totalBeams = 0;
        int prevDevices = 0;

        for (String row : bank) {
            int currentDevices = 0;

            // Count number of '1's in current row
            for (char c : row.toCharArray()) {
                if (c == '1') currentDevices++;
            }

            // If row has devices, multiply with previous row that had devices
            if (currentDevices > 0) {
                totalBeams += prevDevices * currentDevices;
                prevDevices = currentDevices;
            }
        }

        return totalBeams;
    }
}
