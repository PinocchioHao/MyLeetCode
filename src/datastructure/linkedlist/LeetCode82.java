package datastructure.linkedlist;

/**
 82. Remove Duplicates from Sorted List II
 Medium

 Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

 Example 1:

 Input: head = [1,2,3,3,4,4,5]
 Output: [1,2,5]
 Example 2:

 Input: head = [1,1,1,2,3]
 Output: [2,3]

 Constraints:

 The number of nodes in the list is in the range [0, 300].
 -100 <= Node.val <= 100
 The list is guaranteed to be sorted in ascending order.
 */
public class LeetCode82 {

    public static void main(String[] args) {

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(2);
        l2.next.next.next = new ListNode(3);


        ListNode res = deleteDuplicates1(l2);

        System.out.println();


    }


    /**
     * 常规解法 - 前后比较，发现相等则让前一不等值指向相等这段后面的节点
     *
     * Standard Solution - Compare the current and next node.
     * If they are equal, skip the duplicates by linking the previous distinct node to the node after the duplicates.
     *
     * @param head 链表的头节点 The head node of the list
     * @return 去重后的链表 List with duplicates removed
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 边界情况处理：如果链表为空或只有一个节点，直接返回
        // Edge case handling: If the list is empty or has only one node, return the list as is
        if (head == null || head.next == null) { return head; }

        // 创建一个哑节点，帮助处理链表
        // Create a dummy node to help with edge cases
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        // 记录上一个不同值的节点
        // Track the previous distinct node
        ListNode prevDiff = dummy;
        ListNode cur = head;

        // 标记是否有相等的值
        // Flag to indicate if duplicates have been found
        boolean equalFlag = false;

        // 遍历链表，寻找相等节点
        // Traverse the list, looking for duplicates
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                // 如果当前节点与下一个节点值相等，跳过当前节点
                // If the current node is equal to the next one, move the pointer to skip duplicates
                cur = cur.next;
                equalFlag = true;
            } else {
                // 如果之前有相等值，跳过这一段
                // If duplicates were found earlier, skip the duplicate segment
                if (equalFlag) {
                    prevDiff.next = cur.next;
                    equalFlag = false;
                } else {
                    // 如果没有相等值，更新prevDiff指向当前节点
                    // If no duplicates were found, update prevDiff to point to the current node
                    prevDiff.next = cur;
                    prevDiff = prevDiff.next;
                }
                cur = cur.next;
            }
        }

        // 如果最后一段也是重复的，跳过它
        // Handle the case where the last segment is duplicated
        if (equalFlag) {
            prevDiff.next = cur.next;
        }

        return dummy.next;
    }

    /**
     * 递归 - 注意与83题不同的是83的递归每一步都可以往后连接，而该题分情况进行连接
     * 不相等的情况才将next指针连接到递归结果
     * 而相等的情况不进行连接，而是返回下一个不相等的元素，供上一不相等的节点进行连接
     *
     * Recursion - Unlike in problem 83 where the recursion can always connect to the next node,
     * this problem involves conditional connections.
     * In the case of unequal nodes, the next pointer is connected to the result of the recursive call.
     * In the case of equal nodes, no connection is made, and the function skips over duplicates,
     * returning the next distinct node for the previous unequal node to connect to.
     *
     * @param head 链表的头节点 // The head node of the list
     * @return 去重后的链表头节点 // The head of the deduplicated list
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        // 边界情况处理：如果链表为空或只有一个节点，直接返回表头
        // Edge case: If the list is empty or contains only one node, return the head directly
        if (head == null || head.next == null) { return head; }

        // 如果当前节点值跟下一节点值不同，则可以将head.next进行连接，递归处理后续节点，注意此处是返回表头head
        // If the current node's value is different from the next node's value, connect head.next after recursively processing the rest of the list, returning the head node
        if (head.val != head.next.val) {
            head.next = deleteDuplicates1(head.next);
            return head;
        }
        // 如果当前节点值跟下一节点值相同，则直接跳过该段所有节点，不进行连接操作
        // If the current node's value is the same as the next, skip all nodes with the same value and do not connect them
        else {
            // 递归处理，只进行了head的遍历，遍历到最后一个不跟前值相等的节点处
            // Recursive processing, traverse until the last node that does not match the previous value
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // 返回这个不等的节点，在向上层调用处回归的时候连给前一值的next
            // Return the first node that differs from the duplicates and reconnect in the recursive call above
            return deleteDuplicates1(head.next);
        }
    }


}
