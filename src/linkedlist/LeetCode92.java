package linkedlist;

/**
 * 92. Reverse Linked List II
 * Medium
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * Follow up: Could you do it in one pass?
 */
public class LeetCode92 {

    public static void main(String[] args) {

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(4);
        l2.next.next.next.next = new ListNode(5);


        ListNode res = reverseBetween(l2, 2, 4);

        System.out.println();


    }

    /**
     * 常规解法：（本题主要是找到区间边界，至于反转区间可以采用遍历指针指向前一元素，也可以采用stack）
     * 先向后left步找到不反转的最后一个节点和反转的第一个节点
     * 然后在left~right区间进行链表反转
     * 最后将不反转的最后一个节点连接反转区间链表的新头节点
     * 将反转链表新的尾节点连接right之后的不反转的头节点
     *
     *
     * Standard Solution:
     * First, traverse 'left' steps to find the last node before the non-reversed segment
     * and the first node of the segment to be reversed.
     * Then reverse the segment between 'left' and 'right'.
     * Finally, connect the last node of the non-reversed segment to the new head of the reversed segment.
     * Connect the new tail of the reversed segment to the first node after 'right'.
     *
     *
     * @param head  链表的头节点
     * @param left  左边界
     * @param right 右边界
     * @param head  The head of the list
     * @param left  The left boundary
     * @param right The right boundary
     * @return 返回新的链表头节点
     * @return The new head of the list
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;

        // Create a dummy node to simplify edge case handling
        // 创建虚拟头节点以简化边界情况的处理
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode cur = head;
        ListNode prev = dummy;

        // Traverse to the left boundary. After the loop, 'prev' is the last node before the reverse,
        // and 'cur' is the first node to be reversed.
        // 遍历到left的边界处，遍历完后，prev位于不反转的最后一个节点，cur位于要反转的第一个节点
        for (int i = 1; i < left; i++) {
            cur = cur.next;
            prev = prev.next;
        }

        // The last node of the non-reversed segment 0~left
        // 0~left 区间不反转的最后一个节点
        ListNode leftLastNode = prev;
        // The head of the reversed segment left~right
        // left~right 反转区间的头节点
        ListNode reverseHead = cur;

        // Reverse the segment
        // 开始反转left~right区间内的链表
        // 注意此时newPrev不用通过.next来移动，所以可以初始化为null
        ListNode newPrev = null;
        for (int i = 0; i <= right - left; i++) {
            ListNode nextNode = cur.next;
            cur.next = newPrev;
            newPrev = cur;
            cur = nextNode;
        }

        // After reversing, 'newPrev' points to the last node of the reversed segment,
        // which should be connected to the last node before the reverse.
        // 遍历完后，此时newPrev位于反转区间left~right的最后一个元素，
        // 即反转区间的头节点，与前面0~left不反转区间最后节点相连
        leftLastNode.next = newPrev;

        // 'cur' now points to the first node after the reversed segment,
        // so the last node of the reversed segment (reverseHead) should point to 'cur'.
        // 此时cur位于right~tail之后不反转区间的第一个元素，
        // 反转区间left~right的reverseHead变成了新的尾节点，与cur相连
        reverseHead.next = cur;

        // Return the new head of the list.
        // 返回新的链表头节点
        return dummy.next;
    }


}
