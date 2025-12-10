class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Store all numbers from nums in a HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Create a dummy node to simplify edge cases (like deleting the head)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        // Traverse the list and remove nodes that appear in nums
        while (curr != null) {
            if (set.contains(curr.val)) {
                prev.next = curr.next;  // Skip the node
            } else {
                prev = curr;  // Move prev only if not deleted
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}
