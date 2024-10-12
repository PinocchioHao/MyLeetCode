package linkedlist;

import java.util.HashMap;

/**
 * 61. Rotate List
 * Medium
 * 
 * Given the head of a linked list, rotate the list to the right by k places.
 * 
 * Example 1:
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 * 
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * 
 * Constraints:
 * 
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class LeetCode61 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode l2 = rotateRight2(l1, 2);
//        ListNode l3 = rotateRight1(l1, 2);

        System.out.println(4 / 3);
        System.out.println(7 % 3);
        System.out.println(7 / 3);
        System.out.println(5 / 2);
        System.out.println(5 % 2);

        System.out.println();

    }




    /**
     * 哈希表解法 - 通过遍历链表，将每个节点及其索引存入HashMap，然后根据k计算旋转后的起始位置，并处理尾节点的连接和断开
     *
     * HashMap Solution - Traverse the list, store each node and its index in a HashMap,
     * then calculate the new starting position after k rotations and handle the connection and disconnection of the tail node.
     *
     * @param head 链表的头节点 The head node of the list
     * @param k 旋转的次数 Number of rotations
     * @return 旋转后的链表 Rotated list
     */
    public static ListNode rotateRight(ListNode head, int k) {
        // 边界情况处理：如果链表为空或者只有一个节点，直接返回原链表
        // Handle edge cases: if the list is empty or has only one node, return the original list
        if (head == null || head.next == null) return head;

        // 使用HashMap记录链表的每个节点
        // Use a HashMap to store each node in the list
        HashMap<Integer, ListNode> map = new HashMap<>();
        int length = 0;
        ListNode cur = head;

        // 遍历链表，并将每个节点存入HashMap
        // Traverse the list and store each node in the HashMap
        while (cur != null) {
            map.put(length, cur);
            length++;
            cur = cur.next;
        }

        // 移动位数
        // Calculate the number of effective rotations
        int flag = k % length;

        // 恰好不移动，直接返回原头结点
        // If no movement is needed, return the original head node
        if (flag == 0) {
            return head;
        }

        // flag就是断开处，由于是倒数，其下标为i-flag
        // The flag is where the list will be cut, calculated from the end of the list (index i - flag)
        ListNode newHead = map.get(length - flag);
        ListNode newTail = map.get(length - flag - 1);

        // 断开新尾结点的连接
        // Disconnect the new tail node
        newTail.next = null;

        // 将原来的尾结点next指向头结点
        // Point the original tail node's next to the original head
        ListNode formerTail = map.get(length - 1);
        formerTail.next = head;

        return newHead;
    }


    /**
     * 双指针解法 - 先遍历求出链表长，然后求出断点flag位置，快慢指针距离flag同速前进，最后快指针在表尾，慢指针在断点，处理相应节点
     *
     * Two-pointer solution - First, traverse the list to calculate its length,
     * then find the cut point flag. Use two pointers that move at the same pace,
     * one fast and one slow. When the fast pointer reaches the end, the slow pointer is at the cut point.
     *
     * @param head 链表的头节点 The head node of the list
     * @param k 旋转的次数 Number of rotations
     * @return 旋转后的链表 Rotated list
     */
    public static ListNode rotateRight1(ListNode head, int k) {
        // 边界情况处理：如果链表为空或者只有一个节点，直接返回原链表
        // Handle edge cases: if the list is empty or has only one node, return the original list
        if (head == null || head.next == null) return head;

        int length = 0;
        ListNode cur = head;

        // 计算链表的长度
        // Calculate the length of the list
        while (cur != null) {
            cur = cur.next;
            length++;
        }

        // 计算旋转的有效次数
        // Calculate the effective number of rotations
        int flag = k % length;
        if (flag == 0) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;

        // 快指针先走flag步
        // Move the fast pointer forward by 'flag' steps
        while (flag > 0) {
            fast = fast.next;
            flag--;
        }

        // 两个指针一起移动，直到快指针到达表尾
        // Move both pointers until the fast pointer reaches the end of the list
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow此时到达断点，fast到达尾部
        // Now, slow is at the cut point, and fast is at the tail
        ListNode newHead = slow.next;

        // 将原尾部与头部连接
        // Connect the original tail to the head
        fast.next = head;

        // 断开新尾结点
        // Disconnect the new tail node
        slow.next = null;

        return newHead;
    }


    /**
     * 把链表首尾相连，从表头走k-flag步然后断开
     *
     * Concatenate the head and tail of the list,
     * walk k-flag steps from the head, and then cut the connection.
     *
     * @param head 链表的头节点 The head node of the list
     * @param k 旋转的次数 Number of rotations
     * @return 旋转后的链表 Rotated list
     */
    public static ListNode rotateRight2(ListNode head, int k) {
        // 边界情况处理：如果链表为空或者只有一个节点，直接返回原链表
        // Handle edge cases: if the list is empty or has only one node, return the original list
        if (head == null || head.next == null) return head;

        int length = 0;
        ListNode cur = head;

        // 计算链表长度并让尾结点连接到头结点，形成环形链表
        // Calculate the length of the list and connect the tail to the head to form a circular list
        while (cur != null && cur.next != null) {
            cur = cur.next;
            length++;
        }
        cur.next = head;  // 将尾结点连接到头结点 Connect the tail to the head
        length++;

        // 计算断开链表的位置
        // Calculate the position to break the circular list
        int flag = k % length;
        int step = length - flag;

        // 走到断开点，并断开链表
        // Walk to the break point and disconnect the list
        while (step > 0) {
            head = head.next;
            cur = cur.next;
            step--;
        }
        cur.next = null;  // 断开链表 Break the list

        return head;
    }

}
