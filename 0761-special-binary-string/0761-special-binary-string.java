class Solution {
    public String makeLargestSpecial(String s) {
        if(s.length() <= 2) return s;

        List<String> list = new ArrayList<>();
        int count = 0, start = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') count++;
            else count--;

            if(count == 0){
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                list.add("1" + inner + "0");
                start = i + 1;
            }
        }
        collections.sort(list, collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        for(String str : list) {
            result.append(str);
        }
        return result.toString();
    }
}