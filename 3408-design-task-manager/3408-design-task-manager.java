import java.util.*;

class TaskManager {
    public final Map<Integer, Task> taskMap;
    private final TreeSet<Task> taskSet;

    private static class Task {
        int userId;
        int taskId;
        int priority;

        Task(int u, int t, int p) {
            userId = u;
            taskId = t;
            priority = p;
        }
    }

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        taskSet = new TreeSet<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority); // higher priority first
            } else {
                return Integer.compare(b.taskId, a.taskId);     // higher taskId first
            }
        });

        for (List<Integer> t : tasks) {
            int u = t.get(0), id = t.get(1), p = t.get(2);
            add(u, id, p);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskMap.put(taskId, task);
        taskSet.add(task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);
        if (old == null) return;
        taskSet.remove(old);

        Task updated = new Task(old.userId, taskId, newPriority);
        taskMap.put(taskId, updated);
        taskSet.add(updated);
    }

    public void rmv(int taskId) {
        Task t = taskMap.remove(taskId);
        if (t != null) {
            taskSet.remove(t);
        }
    }

    public int execTop() {
        if (taskSet.isEmpty()) return -1;
        Task top = taskSet.first();
        taskSet.remove(top);
        taskMap.remove(top.taskId);
        return top.userId;
    }
}
