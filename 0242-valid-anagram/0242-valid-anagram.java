class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> hn = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (hn.containsKey(ch) == true) {
                hn.put(ch, hn.get(ch) + 1);
            } else {
                hn.put(ch, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);

            if (hn.containsKey(ch) == true) {
                if (hn.get(ch)==1) {
                    hn.remove(ch);
                } else {
                    hn.put(ch, hn.get(ch) - 1);
                }
            } else {
                return false;
            }
        }

        if (hn.size()==0) {
            return true;
        } else {
            return false;
        }
    }
}