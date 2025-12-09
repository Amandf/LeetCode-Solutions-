class Solution {
    public int minMaxDifference(int num) {
        String s = Integer.toString(num);
        int maxNum = getMaxNumber(s);
        int minNum = getMinNumber(s);
        return maxNum - minNum;
    }

    private int getMaxNumber(String s) {
        char[] chars = s.toCharArray();
        char target = '9';

        for (char c : chars) {
            if (c != '9') {
                target = c;
                break;
            }
        }

        String maxStr = s.replace(target, '9');
        return Integer.parseInt(maxStr);
    }

    private int getMinNumber(String s) {
        char[] chars = s.toCharArray();
        char target = '0';

        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                if (chars[i] != '0') {
                    target = chars[i];
                    break;
                }
            } else {
                if (chars[i] != '0' && chars[i] != chars[0]) {
                    target = chars[i];
                    break;
                }
            }
        }

        String minStr = s.replace(target, '0');
        return Integer.parseInt(minStr);
    }
}