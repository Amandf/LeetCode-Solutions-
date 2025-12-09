class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        String prev = "";

        for (String f : folder) {
            if (prev.isEmpty() || !f.startsWith(prev + "/")) {
                ans.add(f);
                prev = f;
            }
        }
        return ans;
    }
}