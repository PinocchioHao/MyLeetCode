package AceCodingInterview75Qs.linked_list;

/*
 *
 *
 *

2095. Delete the Middle Node of a Linked List

Medium

You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.


Example 1:


Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.
Example 2:


Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.
Example 3:


Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.


Constraints:

The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105


 *
 *
 */

import datastructure.linkedlist.ListNode;

public class LeetCode2095 {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        LeetCode2095 example = new LeetCode2095();
        example.deleteMiddle(l1);
        System.out.println("------");
    }

    // 快慢指针，快指针遍历完慢指针恰好在中间，把慢指针前一个元素的next接到后一个即可
    public ListNode deleteMiddle(ListNode head) {
        // 只有一个元素，则直接删除
        if (head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = new ListNode();
        pre.next = head;
        while (fast != null && fast.next != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow位于中间，把前一个元素的next指向后一个元素即可
        pre.next = slow.next;
        return head;
    }


}