package linkedlist;

import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;

/**
 160. Intersection of Two Linked Lists
 Easy

 Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

 For example, the following two linked lists begin to intersect at node c1:


 The test cases are generated such that there are no cycles anywhere in the entire linked structure.

 Note that the linked lists must retain their original structure after the function returns.

 Custom Judge:

 The inputs to the judge are given as follows (your program is not given these inputs):

 intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 listA - The first linked list.
 listB - The second linked list.
 skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.



 Example 1:


 Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 Output: Intersected at '8'
 Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 - Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
 Example 2:


 Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 Output: Intersected at '2'
 Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 Example 3:


 Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 Output: No intersection
 Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 Explanation: The two lists do not intersect, so return null.


 Constraints:

 The number of nodes of listA is in the m.
 The number of nodes of listB is in the n.
 1 <= m, n <= 3 * 104
 1 <= Node.val <= 105
 0 <= skipA < m
 0 <= skipB < n
 intersectVal is 0 if listA and listB do not intersect.
 intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.


 Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */
public class LeetCode160 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);


        ListNode l2 = l1.next;
//        l2.next = l1.next;

        ListNode res = getIntersectionNode2(l1, l2);

        System.out.println();


    }


    /**
     * 哈希表 - 遍历ListA保存所有节点，随后遍历ListB，如果找到相等则立马输出该点即为交点
     * Hash Table - Traverse ListA and store all its nodes, then traverse ListB. If a matching node is found, return it as the intersection point.
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 使用HashSet存储链表A的节点
        // Use HashSet to store all nodes from ListA
        HashSet<ListNode> set = new HashSet<>();

        // 将链表A的所有节点添加到HashSet中
        // Add all nodes from ListA to the HashSet
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        // 遍历链表B，找到与链表A相交的节点
        // Traverse ListB to find the first intersecting node with ListA
        while (headB != null) {
            if (set.contains(headB)) {
                // 第一个相交节点
                // First intersecting node found
                return headB;
            }
            headB = headB.next;
        }

        // 如果没有相交节点，返回null
        // Return null if no intersection is found
        return null;
    }

    /**
     * 双指针法 - p1,p2先分别遍历headA,headB，当遍历到结尾后，又去遍历另一方，那么下一次二者就会在交点相交
     * Two-pointer method - p1 and p2 initially traverse ListA and ListB separately. Once they reach the end, they switch to the other list.
     * Eventually, they will meet at the intersection point if there is one.
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // 如果有任意一个链表为空，直接返回null
        // If either ListA or ListB is null, return null immediately
        if (headA == null || headB == null) return null;

        ListNode p1 = headA;
        ListNode p2 = headB;

        // 使用双指针法，p1和p2会遍历完两个链表并最终相遇在交点或null
        // Using the two-pointer method, p1 and p2 will traverse both lists and eventually meet at the intersection point or null
        while (p1 != p2) {
            // 当p1走到null时，跳到headB的头；否则继续向前走
            // When p1 reaches the end of ListA, jump to the head of ListB; otherwise, continue forward
            p1 = (p1 == null) ? headB : p1.next;

            // 当p2走到null时，跳到headA的头；否则继续向前走
            // When p2 reaches the end of ListB, jump to the head of ListA; otherwise, continue forward
            p2 = (p2 == null) ? headA : p2.next;
        }

        // 最终，p1和p2要么相遇在交点，要么都为null
        // Eventually, p1 and p2 will either meet at the intersection point or both be null
        return p1;
    }

    /**
     * 暴力法，遍历A和B所有节点，找到第一个相等节点
     * Brute-force approach: Traverse all nodes in ListA and ListB to find the first equal node
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // 如果有任意一个链表为空，直接返回null
        // If either ListA or ListB is null, return null immediately
        if (headA == null || headB == null) return null;

        // 遍历链表A
        // Traverse ListA
        while (headA != null) {
            // 需要额外创建一个节点指向链表B的头节点，如果用headB的话遍历一次就到链表末尾了
            // An additional node needs to be created to point to the head node of linked list B.
            // If headB is used, traversing once will reach the end of the linked list
            ListNode b = headB;
            // 遍历链表B
            // Traverse ListB
            while (b != null) {
                // 如果找到相同的节点，返回该节点
                // If a matching node is found, return that node
                if (headA == b) {
                    return headA;
                }
                b = b.next; // 移动到链表B的下一个节点
            }
            headA = headA.next; // 移动到链表A的下一个节点
        }
        // 如果没有找到相交节点，返回null
        // Return null if no intersection node is found
        return null;
    }


}
