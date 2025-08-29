package datastructure.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 234. Palindrome Linked List
 * Easy
 *
 * Given the head of a singly linked list, return true if it is a
 * palindrome
 * or false otherwise.
 *
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class LeetCode234 {

    public static void main(String[] args) {

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(1);
//        l2.next.next.next.next = new ListNode(1);

        boolean res = isPalindrome(l2);
        boolean res1 = isPalindrome2(l2);

        System.out.println(res);
        System.out.println(res1);


    }

    /**
     * 快慢指针找中点，后半段节点入栈，然后重新遍历前半段跟栈pop的元素比较
     * Use slow and fast pointers to find the middle of the list,
     * push the second half onto a stack, then compare the first half to the stack's popped elements
     *
     * @param head 链表的头节点
     * @return 返回是否为回文链表
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 快慢指针，如果链表为奇数，则slow位于正中间，如果为偶数则位于后半段的开头；count始终为length/2
        // Use slow and fast pointers, if the list has an odd number of nodes,
        // slow will be at the center, if even, slow will be at the beginning of the second half; count tracks half the length.
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        Stack<Integer> stack = new Stack<>();

        // 快指针到达尾端时，慢指针刚好位于链表中点
        // Fast pointer moves twice as fast, so when it reaches the end, slow will be at the midpoint
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }

        // 后半段元素入栈
        // Push all second-half elements onto the stack
        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
            count++;
        }

        slow = head;
        int half = count / 2;

        // 遍历前半段节点，跟栈中弹出的元素比较
        // Traverse the first half of the list, compare each node's value with popped stack elements
        for (int i = 0; i < half; i++) {
            int temp = stack.pop();
            if (temp != slow.val) return false;
            slow = slow.next;
        }

        return true;
    }


    /**
     * 链表转为数组判断是否回文
     * Convert the linked list to an array and check if it's a palindrome
     *
     * @param head 链表的头节点
     * @return 返回是否为回文链表
     */
    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode cur = head;
        List<Integer> list = new ArrayList<>();

        // 将链表元素全部转移到数组中
        // Convert all elements of the linked list to an array
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        int left = 0;
        int right = list.size() - 1;

        // 双指针向中间遍历，判断数组是否为回文
        // Use two pointers to check if the array is a palindrome
        while (left < right) {
            if (list.get(left) != list.get(right)) return false;
            left++;
            right--;
        }

        return true;
    }


    /**
     * 反转后半段元素来进行判断，空间复杂度仅需O(1)，不额外占用空间
     * Reverse the second half of the list to check for palindrome,
     * achieving O(1) space complexity.
     *
     * @param head 链表的头节点 / The head node of the linked list
     * @return 是否是回文链表 / Whether the linked list is a palindrome
     */
    public static boolean isPalindrome2(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回true
        // Base case: If the list is empty or contains only one node, it's a palindrome.
        if (head == null || head.next == null) return true;

        ListNode slow = head;
        ListNode fast = head;
        int count = 0;

        // 快慢指针，如果链表为奇数则slow位于正中间，偶数则位于后半段开头
        // Fast and slow pointers to find the middle of the list.
        // When fast reaches the end, slow will be at the midpoint.
        // 'count' stores half the length of the list.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }

        // prev不需要通过.next后移，所以可以初始化为null
        // Initialize 'prev' to null as it will hold the reversed list's head.
        // 反转后半段链表，执行完后prev位于最后一个节点，即为反转后的head
        // Reverse the second half of the linked list. After this, 'prev' will point to the new head.
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // 将前半段链表和后半段反转后的链表比较
        // Compare the first half of the list with the reversed second half.
        // 这个循环正好执行'count'次，相当于链表的一半
        // The loop runs exactly 'count' times, which is half the length of the list.
        for (int i = 0; i < count; i++) {
            if (head.val != prev.val) return false;  // 如果值不相等，说明不是回文
            // If values don't match, it's not a palindrome.
            head = head.next;  // 移动到前半段的下一个节点 / Move forward in the first half
            prev = prev.next;  // 移动到后半段反转链表的下一个节点 / Move forward in the reversed second half
        }

        // 如果所有节点值都匹配，则链表是回文的
        // If all nodes match, the list is a palindrome.
        return true;
    }


}
