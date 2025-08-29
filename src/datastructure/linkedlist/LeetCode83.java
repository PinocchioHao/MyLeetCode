package datastructure.linkedlist;

/**
 * 83. Remove Duplicates from Sorted List
 * Easy
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 *
 * Example 1:
 *
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 * Example 2:
 *
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class LeetCode83 {

    public static void main(String[] args) {

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(3);


        ListNode res = deleteDuplicates2(l2);

        System.out.println();


    }

    /**
     * 常规解法，前后比较 - Regular approach, comparing previous and current nodes
     * @param head 链表的头节点 - Head of the linked list
     * @return 去重后的链表头节点 - Head of the modified linked list with duplicates removed
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 创建一个虚拟节点，用于处理链表头的特殊情况 - Create a dummy node to handle edge cases for the head of the list
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head; // 将虚拟节点的下一个指向原链表的头节点 - Link the dummy node to the head of the original list
        ListNode prev = dummy; // prev指针指向虚拟节点 - Initialize prev pointer to the dummy node
        ListNode cur = head; // cur指针指向原链表的头节点 - Initialize cur pointer to the head of the original list

        // 遍历链表，直到cur指针到达末尾 - Traverse the list until cur reaches the end
        while (cur != null) {
            // 如果当前节点值与前一个节点值相同，则跳过当前节点
            // If the value of prev equals the value of cur, skip cur by linking prev to cur.next
            if (prev.val == cur.val) {
                prev.next = cur.next; // prev.next指向cur的下一个节点 - Link prev.next to cur's next node
            } else {
                // 如果不相同，则将prev指向当前节点
                // If values are different, move prev to current node
                prev = cur; // Move prev to current node
            }
            // 每次迭代后，将cur指针后移 - Move cur pointer to the next node for the next iteration
            cur = cur.next; // Move cur to the next node
        }
        return dummy.next; // 返回去重后的链表头节点 - Return the head of the modified linked list
    }

    /**
     * 递归解法 - Recursive approach
     * @param head 链表的头节点 - Head of the linked list
     * @return 去重后的链表头节点 - Head of the modified linked list with duplicates removed
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        // 递归终止条件 - 单个或者空节点，直接返回
        // Base case: if head is null or only one node, return head
        if (head == null || head.next == null) {
            return head; // No duplicates to remove
        }
        // 递归调用，继续向后遍历链表，直到到达链表末端
        // Recursive step: proceed to the next node
        head.next = deleteDuplicates1(head.next);
        // 归 - 如果当前节点值等于下一个节点值，则返回下一个节点（跳过当前节点）
        // Returning the next node if current node value equals the next node value, effectively skipping duplicates
        return head.val == head.next.val ? head.next : head; // Otherwise, return current node
    }

    /**
     * 简化版双指针 - Simplified version using the two-pointer technique
     * 双指针解法是链表元素去重问题基本解，适用于重复元素连续和不连续的情况，详见82题
     * The two-pointer technique is a fundamental solution for removing duplicates from a linked list,
     * applicable for both consecutive and non-consecutive duplicate elements.
     * @param head 链表的头节点 - Head of the linked list
     * @return 去重后的链表头节点 - Head of the modified linked list with duplicates removed
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        // 创建一个虚拟节点，用于处理链表头的特殊情况 - Create a dummy node to handle edge cases for the head of the list
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head; // 将虚拟节点的下一个指向原链表的头节点 - Link the dummy node to the head of the original list
        ListNode prev = dummy; // prev指针指向虚拟节点 - Initialize prev pointer to the dummy node
        ListNode cur = head; // cur指针指向原链表的头节点 - Initialize cur pointer to the head of the original list

        // 遍历链表，直到cur指针到达末尾 - Traverse the list until cur reaches the end
        while (cur != null) {
            // 如果cur与下一个节点的值相等，移动cur到最后一个相等的位置
            // Move cur to the last node that has the same value as the current node
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next; // 继续移动cur指针 - Continue moving the cur pointer
            }
            // cur现在是当前值的最后一个节点，prev的next指向cur
            // At this point, cur is the last node with the same value, so link prev to cur
            prev.next = cur; // Connect previous node to the current node
            // 移动prev和cur指针，为下一轮准备 - Move prev and cur pointers for the next iteration
            prev = cur; // prev now points to the current node
            cur = cur.next; // Move cur to the next node for the next iteration
        }
        return dummy.next; // 返回去重后的链表头节点 - Return the head of the modified linked list
    }


}
