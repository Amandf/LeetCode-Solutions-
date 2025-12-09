class Solution {
    class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String name = "";
        boolean toDelete = false;
    }

    Map<String, List<TrieNode>> seen = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode();

        // Build the Trie
        for (List<String> path : paths) {
            TrieNode node = root;
            for (String folder : path) {
                node = node.children.computeIfAbsent(folder, k -> new TrieNode());
                node.name = folder;
            }
        }

        // Detect duplicates
        serialize(root);

        // Collect non-deleted paths
        List<List<String>> result = new ArrayList<>();
        collect(root, new ArrayList<>(), result);
        return result;
    }

    // Serialize subtree and mark duplicates
    private String serialize(TrieNode node) {
        if (node.children.isEmpty()) return "";

        List<String> parts = new ArrayList<>();
        for (TrieNode child : node.children.values()) {
            parts.add(child.name + "(" + serialize(child) + ")");
        }

        Collections.sort(parts);
        String serial = String.join("", parts);

        seen.putIfAbsent(serial, new ArrayList<>());
        seen.get(serial).add(node);

        if (seen.get(serial).size() > 1) {
            for (TrieNode dup : seen.get(serial)) {
                dup.toDelete = true;
            }
        }

        return serial;
    }

    // DFS collect valid paths
    private void collect(TrieNode node, List<String> path, List<List<String>> result) {
        for (TrieNode child : node.children.values()) {
            if (child.toDelete) continue;
            path.add(child.name);
            result.add(new ArrayList<>(path));
            collect(child, path, result);
            path.remove(path.size() - 1);
        }
    }
}
