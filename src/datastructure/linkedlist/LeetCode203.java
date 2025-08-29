package datastructure.linkedlist;

/**
 203. Remove Linked List Elements
 Easy

 Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

 Example 1:

 Input: head = [1,2,6,3,4,5,6], val = 6
 Output: [1,2,3,4,5]
 Example 2:

 Input: head = [], val = 1
 Output: []
 Example 3:

 Input: head = [7,7,7,7], val = 7
 Output: []

 Constraints:

 The number of nodes in the list is in the range [0, 104].
 1 <= Node.val <= 50
 0 <= val <= 50
 */
public class LeetCode203 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(2);

        ListNode rlt = removeElements(l1, 2);
        System.out.println(rlt);
    }


    /**
     * 找到删除节点，将上一节点next连向当前next即可
     * Find the node to delete, then link the previous node's next to the current node's next.
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        // 创建一个虚拟节点，方便处理头节点
        // Create a dummy node to simplify edge case handling for the head node.
        ListNode dummy = new ListNode(-1);
        // 将虚拟节点的 next 指向头节点
        // Set the dummy node's next pointer to the head of the list.
        dummy.next = head;
        // prev 指向虚拟节点
        // Initialize prev pointer to the dummy node.
        ListNode prev = dummy;
        // cur 指向头节点
        // Initialize cur pointer to the head node.
        ListNode cur = head;
        while (cur != null) {
            // 当前值为要删除的值，则将上一节点next指向该节点之后
            // If the current node's value equals the value to be deleted, link the previous node's next to the current node's next.
            if (cur.val == val) {
                prev.next = cur.next;
            }
            // 否则上一节点正常后移指向当前节点
            // Otherwise, move the previous node to the current node.
            else {
                prev = prev.next;
            }
            // 每一步cur指针后移
            // Move the current node pointer to the next node.
            cur = cur.next;
        }
        // 返回处理后的链表头节点
        // Return the new head of the modified list (dummy.next).
        return dummy.next;
    }



}
