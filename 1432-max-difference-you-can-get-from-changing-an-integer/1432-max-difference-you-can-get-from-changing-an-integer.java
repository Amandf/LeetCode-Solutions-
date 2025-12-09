class Solution {
    public int maxDiff(int num) {
        String s = Integer.toString(num);

     
        char toReplaceMax = ' ';
        for (char c : s.toCharArray()) {
            if (c != '9') {
                toReplaceMax = c;
                break;
            }
        }
        String maxStr = toReplaceMax == ' ' ? s : s.replace(toReplaceMax, '9');

       
        char toReplaceMin = s.charAt(0);
        String minStr;

        if (toReplaceMin != '1') {
            minStr = s.replace(toReplaceMin, '1');
        } else {
       
            char rep = ' ';
            for (char c : s.toCharArray()) {
                if (c != '0' && c != '1') {
                    rep = c;
                    break;
                }
            }
            minStr = rep == ' ' ? s : s.replace(rep, '0');
        }

        int max = Integer.parseInt(maxStr);
        int min = Integer.parseInt(minStr);

        return max - min;
    }
}
