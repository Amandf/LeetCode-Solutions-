class Solution {
    public int longestBalanced(String s) {
        int maxlen = 0;

        maxlen = Math.max(maxlen, longestSingleChar(s));

        maxlen = Math.max(maxlen, solveTwo(s, 'a', 'b', 'c'));
        maxlen = Math.max(maxlen, solveTwo(s, 'a', 'c', 'b'));
        maxlen = Math.max(maxlen, solveTwo(s, 'b', 'c', 'a'));

        maxlen = Math.max(maxlen, solveThree(s));

        return maxlen;
    }
    private int longestSingleChar(String s) {
        int max = 1, count = 1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(i - 1)) {
                count++;
            }
            else {
                count = 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private int solveTwo(String s, char x, char y, char splitChar) {
        int maxlen = 0;
        int start = 0;

        for(int i=0;i<=s.length();i++) {
            if(i == s.length() || s.charAt(i) == splitChar) {
                maxlen = Math.max(maxlen, solveSegment(s, start, i - 1, x, y));
                start = i + 1;
            }
        }
        return maxlen;
    }

    private int solveSegment(String s, int left, int right, char x, char y) {
        if(left > right) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, left - 1);

        int diff = 0;
        int maxlen = 0;

        for(int i = left; i<=right; i++) {
            if(s.charAt(i) == x) diff++;
            else if (s.charAt(i) == y) diff--;

            if(map.containsKey(diff)) {
                maxlen = Math.max(maxlen, i - map.get(diff));
            }
            else {
                map.put(diff, i);
            }
        }
        return maxlen;
    }
    private int solveThree(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("0#0", -1);

        int a = 0, b = 0, c = 0;
        int maxlen = 0;

        for(int i = 0; i<s.length();i++) {
            char ch = s.charAt(i);
            if (ch == 'a') a++;
            else if (ch == 'b') b++;
            else c++;

            int diffAB = a - b;
            int diffAC = a - c;

            String key = diffAB + "#" + diffAC;

            if(map.containsKey(key)) {
                maxlen = Math.max(malen, i - map.get(key));
            }
            else {
                map.put(key, i);
            }
        }
        return maxlen;
    }
}