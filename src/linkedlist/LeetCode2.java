package linkedlist;

/**
 * 2. Add Two Numbers
 * Medium
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class LeetCode2 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(9);
        ListNode rlt = addTwoNumbers(l1, l2);
        System.out.println(rlt);
    }

    /**
     * int carry represents the carry-over value.
     * The current digit value = (l1.val + l2.val + carry) % 10
     * carry = current digit value / 10 (can also be carry = currRealNum >= 10 ? 1 : 0;)
     * Note: If there is a carry-over, it should be carried to the next digit, potentially extending the list.
     *
     * @param l1 the first linked list representing a number
     * @param l2 the second linked list representing a number
     * @return the sum of the two numbers as a linked list
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Create a dummy head to simplify edge cases
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        // Initialize carry to 0
        int carry = 0;

        // Loop through both linked lists until both are null
        while (l1 != null || l2 != null) {
            // Get the value of the current nodes, default to 0 if the node is null
            int num1 = (l1 == null) ? 0 : l1.val;
            int num2 = (l2 == null) ? 0 : l2.val;

            // Calculate the sum of the current digits plus the carry
            int currRealNum = num1 + num2 + carry;
            // Determine the digit to store in the current node
            int currDigit = currRealNum % 10;
            // Update the carry for the next iteration
            // can also be carry = currRealNum >= 10 ? 1 : 0;
            carry = currRealNum / 10;

            // Create a new node with the current digit and attach it to the result list
            curr.next = new ListNode(currDigit);
            curr = curr.next; // Move to the next node

            // Move to the next nodes in l1 and l2 if they exist
            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }

        // If there is still a carry-over, add a new node for it
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        // Return the next node of dummyHead, which is the actual head of the result list
        return dummyHead.next;
    }


}
