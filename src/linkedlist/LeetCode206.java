package linkedlist;

import java.util.Stack;

/**
 * 206. Reverse Linked List
 * Easy
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class LeetCode206 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);

        ListNode rlt = reverseList2(l1);
        System.out.println(rlt);
    }

    /**
     * 栈 - 本题用栈会带来额外开销，但也是一种思路
     * Stack - This approach uses a stack, which incurs extra overhead, but it is still a valid method.
     * @param head 链表的头节点 - Head of the linked list
     * @return 反转后的链表头节点 - Head of the reversed linked list
     */
    public static ListNode reverseList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回头节点
        // If the list is empty or has only one node, return the head
        if (head == null || head.next == null) return head;

        // 使用栈存储链表的值
        // Use a stack to store the values of the list
        Stack<Integer> stack = new Stack<>();

        // 将链表节点的值压入栈中
        // Push all node values onto the stack
        while (head != null) {
            stack.push(head.val); // 将当前节点的值压入栈
            head = head.next; // 移动到下一个节点
        }

        // 弹出栈中的值，创建新的反转链表
        // Pop from the stack and create the reversed list
        // 注意这里要新建游标节点cur来操作，如果全用newHead进行操作的话，会损失节点，最后返回的是最后处理的节点
        // Create a new head with the top value of the stack
        ListNode newHead = new ListNode(stack.pop()); // 创建新链表的头节点
        ListNode cur = newHead; // 使用cur指针操作新链表

        // 继续弹出剩余的值并创建新节点
        // Continue popping values and creating new nodes
        while (!stack.empty()) {
            cur.next = new ListNode(stack.pop()); // 创建新节点并连接
            cur = cur.next; // 移动游标到新创建的节点
        }

        return newHead; // 返回反转后的链表头节点
    }

    /**
     * 递归 - 反转链表的递归解法
     * Recursive approach for reversing the linked list
     * @param head 链表的头节点 - Head of the linked list
     * @return 反转后的链表头节点 - Head of the reversed linked list
     */
    public static ListNode reverseList1(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回头节点
        // If the list is empty or has only one node, return the head
        if (head == null || head.next == null) return head;

        // 新的头部是后面一部分递归得到
        // The new head is obtained from the recursive call on the next node
        ListNode newHead = reverseList1(head.next); // 递归处理后面的链表

        // 两两交换位置 - Swap the positions of the current and next nodes
        ListNode first = head; // 当前节点
        ListNode second = head.next; // 下一个节点

        // 最基础情况：第二个指向第一个，第一个指向null
        // The base case: point the second node to the first and set the first's next to null
        second.next = first; // 让第二个节点指向第一个
        first.next = null; // 将第一个节点的next设置为null（反转）

        return newHead; // 返回新的头部
    }

    /**
     * 一次遍历链表，把当前遍历节点的next指向前一节点即可
     * Iterative approach - Traverse the list and point each node's next to the previous one
     * @param head 链表的头节点 - Head of the linked list
     * @return 反转后的链表头节点 - Head of the reversed linked list
     */
    public static ListNode reverseList2(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回头节点
        // If the list is empty or has only one node, return the head
        if (head == null || head.next == null) return head;

        ListNode prev = null; // 初始化prev为null，用于记录前一个节点
        ListNode cur = head; // 当前节点初始化为头节点

        // 遍历链表，直到cur指针到达末尾
        // Traverse the list until cur reaches the end
        while (cur != null) {
            // 必须要next作为中转变量
            // Must use next as a temporary variable to hold the next node
            ListNode next = cur.next; // 保存当前节点的下一个节点
            cur.next = prev; // 将当前节点的next指向前一个节点

            // 进入下一轮操作
            prev = cur; // 更新prev为当前节点
            cur = next; // 移动cur到下一个节点
        }

        // cur遍历到终止为null了，此时prev恰好是最后一个元素，由于所有节点的next都指向前一元素了，所以返回此刻的prev
        // At the end, cur is null and prev is the new head of the reversed list
        return prev; // 返回反转后的链表头节点
    }




}
