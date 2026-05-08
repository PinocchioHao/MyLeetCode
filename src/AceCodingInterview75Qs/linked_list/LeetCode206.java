package AceCodingInterview75Qs.linked_list;

/*
 *
 *
 *

206. Reverse Linked List

Easy

Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?


 *
 *
 */

import datastructure.linkedlist.ListNode;

public class LeetCode206 {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        LeetCode206 example = new LeetCode206();
        example.reverseList(l1);
        System.out.println("------");
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        // 注意pre初始化为null，如果pre初始化为正常的head前一个元素的话会形成环
        // 因为pre相当于只是一个临时变量，所以干净就好
        ListNode pre = null;
        while (cur != null) {
            // 交换节点
            ListNode nextNode = cur.next;
            cur.next = pre;
            // 移到下一步
            pre = cur;
            cur = nextNode;
        }
        // 最后一步执行完，cur == null, pre就是最后一个元素
        return pre;
    }

}