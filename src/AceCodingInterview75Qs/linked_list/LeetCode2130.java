package AceCodingInterview75Qs.linked_list;

/*
 *
 *
 *

2130. Maximum Twin Sum of a Linked List

Medium

In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.



Example 1:


Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.
Example 2:


Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
Example 3:


Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.


Constraints:

The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105


 *
 *
 */

import datastructure.linkedlist.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode2130 {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(12);
        l1.next.next.next.next = new ListNode(67);
        l1.next.next.next.next.next = new ListNode(3);
        LeetCode2130 example = new LeetCode2130();

        System.out.println(example.pairSum(l1));
        System.out.println(example.pairSum1(l1));
    }

    // 快慢指针，快指针遍历完慢指针恰好在中间，前半部分入栈，跟后半部分相加，思路简单但是会造成额外的开销
    public int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        Deque<Integer> deque = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        while (fast != null) {
            deque.addLast(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        while (slow != null) {
            max = Math.max(max, slow.val + deque.removeLast());
            slow = slow.next;
        }
        return max;
    }

    // 快慢指针，找到中点，前半段做反转处理，然后就可以从中间往两头遍历了
    public int pairSum1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int max = Integer.MIN_VALUE;
        // 反转前半段链表的中间变量
        ListNode pre = null;
        while (fast != null) {
            // 中间变量记录下一个状态
            ListNode fastNext = fast.next.next;
            ListNode slowNext = slow.next;
            // 反转
            slow.next = pre;
            // 进入下一步
            pre = slow;
            slow = slowNext;
            fast = fastNext;
        }
        // 剩下的一半，slow处于后半段的头，pre处于反转后的前半段的头，同时遍历相加
        while (slow != null) {
            max = Math.max(max, slow.val + pre.val);
            slow = slow.next;
            pre = pre.next;
        }
        return max;
    }


}