class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> route = new LinkedList<>();


    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        dfs("JFK");
        return route;
        
    }

    private void dfs(String airport) {
        PriorityQueue<String> dests = graph.get(airport);
        while (dests != null && !dests.isEmpty()) {
            dfs(dests.poll());
        }
        route.addFirst(airport);
    }
}