
public class Solution {
    public int maxDifference(String s) {  
        Map<Character, Integer> freq = new HashMap<>();
        

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        List<Integer> oddFreqs = new ArrayList<>();
        List<Integer> evenFreqs = new ArrayList<>();
        

        for (int count : freq.values()) {
            if (count % 2 == 1) {
                oddFreqs.add(count);
            } else {
                evenFreqs.add(count);
            }
        }
        

        if (oddFreqs.isEmpty() || evenFreqs.isEmpty()) {
            return 0;
        }
        

        int maxOdd = oddFreqs.stream().max(Integer::compare).get();
        int minEven = evenFreqs.stream().min(Integer::compare).get();
        
        return maxOdd - minEven;
    }
}