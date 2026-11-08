
class Solution {
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Find the middle of the linked list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        // Calculate maximum twin sum
        int maxSum = 0;
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            int sum = first.val + second.val;
            maxSum = Math.max(maxSum, sum);

            first = first.next;
            second = second.next;
        }

        return maxSum;
    }
}
