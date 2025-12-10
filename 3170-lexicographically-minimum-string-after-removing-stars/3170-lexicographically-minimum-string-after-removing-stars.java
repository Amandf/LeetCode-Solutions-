
class Solution {
    public String clearStars(String s) {
        List<List<Integer>> charPos = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            charPos.add(new ArrayList<>());
        }

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '*') {
                for (int j = 0; j < 26; j++) {
                    List<Integer> list = charPos.get(j);
                    if (!list.isEmpty()) {
                        int idx = list.remove(list.size() - 1);
                        sb.setCharAt(idx, '*');
                        break;
                    }
                }
            } else {
                charPos.get(c - 'a').add(i);
            }
        }


        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '*') {
                res.append(sb.charAt(i));
            }
        }

        return res.toString();
    }
}
