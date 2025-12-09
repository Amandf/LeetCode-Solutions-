class Solution {
    public String sortVowels(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o','u', 'A', 'E', 'I', 'O', 'U');
        List<Character> vowList =  new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                vowList.add(c);
            }
        }
        Collections.sort(vowList);

        StringBuilder sb = new StringBuilder();
        int vi = 0;
        for(char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append(vowList.get(vi++));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}