package linkedlist;

/**
 21. Merge Two Sorted Lists
 Easy

 You are given the heads of two sorted linked lists list1 and list2.

 Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

 Return the head of the merged linked list.

 Example 1:

 Input: list1 = [1,2,4], list2 = [1,3,4]
 Output: [1,1,2,3,4,4]
 Example 2:

 Input: list1 = [], list2 = []
 Output: []
 Example 3:

 Input: list1 = [], list2 = [0]
 Output: [0]

 Constraints:

 The number of nodes in both lists is in the range [0, 50].
 -100 <= Node.val <= 100
 Both list1 and list2 are sorted in non-decreasing order.
 */
public class LeetCode21 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);


        ListNode res = mergeTwoLists2(l1,l2);

        System.out.println();


    }

    /**
     * 常规解法：遍历两个链表，创建dummy和curr进行连接操作
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        // 创建虚拟头节点，简化边界情况处理
        // Create a dummy node to simplify edge case handling
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode curr = dummy;

        // 遍历两个链表，比较每个节点的值，将较小的节点加入结果链表
        // Traverse both lists and append the smaller node to the result list
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            // 移动当前指针 Move the pointer forward
            curr = curr.next;
        }

        // 当一个链表遍历完时，将剩下的部分直接链接到结果链表
        // If one of the lists is exhausted, attach the rest of the other list
        curr.next = (list1 != null) ? list1 : list2;
        // 返回合并后的链表头部 Return the head of the merged list
        return dummy.next;
    }


    /**
     * 插入法：取头较小的链表为基链，取头节点较大的为插入链，遍历插入链表，比较元素并插入基链。
     * Merge two sorted linked lists using insertion method.
     * We use the list with the smaller head value as the base list, and the other as the list to insert.
     * @param list1 链表1 (ListNode)
     * @param list2 链表2 (ListNode)
     * @return 合并后的链表 (Merged linked list)
     */
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        // 如果list1为空，则返回list2，因为没有可以合并的节点
        // If list1 is null, return list2, as there’s nothing to merge.
        if (list1 == null) {
            return list2;
        }

        // 如果list2为空，则返回list1，因为没有可以合并的节点
        // If list2 is null, return list1, as there’s nothing to merge.
        if (list2 == null) {
            return list1;
        }

        // 确定基链和插入链，基链是两个链表头节点中较小的那个，插入链是较大的那个
        // Determine the base list and the insert list.
        // Base list is the one with the smaller head value, and insert list is the other.
        ListNode base = list1.val < list2.val ? list1 : list2;
        ListNode insert = list1.val < list2.val ? list2 : list1;

        // 保存基链的初始头节点，作为最终返回值
        // Save the original head of the base list as it will be the result to return.
        ListNode res = base;

        // 遍历插入链，直到插入链为空
        // Traverse through the insert list until there are no more nodes to insert.
        while (insert != null) {
            // 若基链的下一个节点的值大于插入链的当前节点值 就把插入链的当前节点插入到基链当前节点之后
            // 若基链下一节点为空，说明基链表走到尾部，那么此时插入链表剩余的值比基链表的值大，也将基链表的指针指向插入链表
            // If the next node of the base list is null (end of the base list) or
            // the next node's value is greater than the current insert node value, perform the insertion.
            if (base.next == null || base.next.val > insert.val) {
                // 临时保存基链的下一个节点，以便完成插入后恢复链表结构
                // Temporarily store base's next node for restoring structure after insertion.
                ListNode baseNext = base.next;
                // 临时保存插入链的下一个节点，方便插入后继续遍历插入链
                // Temporarily store insert's next node for moving to the next insertion.
                ListNode insertNext = insert.next;

                // 将基链的下一个节点设置为插入链的当前节点，完成插入操作
                // Set base.next to insert, completing the insertion.
                base.next = insert;
                // 插入节点的next指向基链原本的下一个节点
                // Link insert.next to the node that was originally next to base.
                insert.next = baseNext;

                // 插入链的当前节点指针移向下一个节点，准备下一次插入
                // Move insert to the next node in its list for the next insertion.
                insert = insertNext;
            }
            // 否则，base移动到下一个节点继续比较
            // Else, simply move base to its next node without insertion.
            else {
                base = base.next;
            }
        }
        // 返回合并后的链表头节点
        // Return the head of the merged list (base list).
        return res;
    }


    /**
     * 递归法合并两个有序链表
     * @param list1 第一个有序链表
     * @param list2 第二个有序链表
     * @return 合并后的有序链表
     */
    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        // 如果list1为空，则返回list2，因为没有可以合并的节点
        // If list1 is null, return list2, as there’s nothing to merge.
        if (list1 == null) {
            return list2; // 返回list2，表示合并完成
        }

        // 如果list2为空，则返回list1，因为没有可以合并的节点
        // If list2 is null, return list1, as there’s nothing to merge.
        if (list2 == null) {
            return list1; // 返回list1，表示合并完成
        }

        // 比较两个链表当前节点的值
        // Compare the values of the current nodes of list1 and list2
        if (list1.val < list2.val) {
            // 如果list1的当前节点值小于list2的当前节点值
            // If the value of the current node in list1 is less than that in list2
            // 将list1的下一个节点与list2合并，并将合并后的结果链接到list1的当前节点
            // Set the next of list1 to the result of merging the next node of list1 and list2
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1; // 返回list1作为合并后的链表的头节点
        } else {
            // 如果list2的当前节点值小于或等于list1的当前节点值
            // If the value of the current node in list2 is less than or equal to that in list1
            // 将list2的下一个节点与list1合并，并将合并后的结果链接到list2的当前节点
            // Set the next of list2 to the result of merging list2 and the next node of list1
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2; // 返回list2作为合并后的链表的头节点
        }
    }




}
