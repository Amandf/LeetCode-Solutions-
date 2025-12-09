class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int groups = (n + k - 1) / k;
        String[] res = new String[groups];

        int idx = 0;
        for (int i = 0; i < groups; i++) {
            int end = Math.min(idx + k, n);
            String chunk = s.substring(idx, end);
            if (chunk.length() < k) {
                StringBuilder sb = new StringBuilder(chunk);
                for (int j = chunk.length(); j < k; j++) sb.append(fill);
                chunk = sb.toString();
            }
            res[i] = chunk;
            idx += k;
        }
        return res;
    }
}