class Solution {
    Map<String, List<Character>> map = new HashMap<>();
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s:allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);

            List<Character> l = map.getOrDefault(key, new ArrayList<>());
            l.add(top);
            map.put(key, l);
        }
        return dfs(bottom);
    }

    private boolean dfs(String current) {
        if(current.length() == 1) {
            return true;
        }
        return buildNextRow(current, 0, new StringBuilder());
    }

    private boolean buildNextRow(String current, int index, StringBuilder next) {
        if(index == current.length()-1) {
            return dfs(next.toString());
        }

        String key = current.substring(index, index+2);
        if(!map.containsKey(key)) {
            return false;
        }

        for(char ch:map.get(key)){    
            next.append(ch);
            if(buildNextRow(current, index+1, next)) {
                return true;
            }
            next.deleteCharAt(next.length()-1);
        }
        return false;
    }
}