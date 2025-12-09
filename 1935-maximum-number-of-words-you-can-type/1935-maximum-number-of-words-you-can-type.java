class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> broken = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            broken.add(c);
        }

        int count = 0;
        String[] words = text.split(" ");

        outer:
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (broken.contains(c)) {
                    continue outer;
                }
            }
            count++;
        }
        return count;
    }
}