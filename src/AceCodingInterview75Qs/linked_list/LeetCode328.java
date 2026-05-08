package AceCodingInterview75Qs.linked_list;

/*
 *
 *
 *

328. Odd Even Linked List

Medium

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.



Example 1:


Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:


Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]


Constraints:

The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106


 *
 *
 */

import datastructure.linkedlist.ListNode;

public class LeetCode328 {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        LeetCode328 example = new LeetCode328();
        example.oddEvenList(l1);
        System.out.println("------");
    }

    //
    public ListNode oddEvenList(ListNode head) {
        // 0个或1个元素直接返回
        if (head == null || head.next == null) return head;
        // 初始化一个记录奇数节点，一个记录偶数节点
        ListNode odd = head;
        ListNode even = head.next;
        // 记录even的头，even在遍历过程中会移到尾端，因此需要记录头
        ListNode dummy = new ListNode();
        dummy.next = even;
        // 遍历以及对next进行操作
        while (odd.next != null && even.next != null) {
            odd.next = even.next;
            even.next = odd.next.next;
            odd = odd.next;
            even = even.next;
        }
        // odd 此时处于最后一个元素，把它的next跟even的头相连
        odd.next = dummy.next;
        return head;
    }

}