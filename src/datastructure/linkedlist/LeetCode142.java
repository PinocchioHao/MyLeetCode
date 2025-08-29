package datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 142. Linked List Cycle II
 Medium

 Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

 There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

 Do not modify the linked list.

 Example 1:

 Input: head = [3,2,0,-4], pos = 1
 Output: tail connects to node index 1
 Explanation: There is a cycle in the linked list, where tail connects to the second node.
 Example 2:


 Input: head = [1,2], pos = 0
 Output: tail connects to node index 0
 Explanation: There is a cycle in the linked list, where tail connects to the first node.
 Example 3:


 Input: head = [1], pos = -1
 Output: no cycle
 Explanation: There is no cycle in the linked list.


 Constraints:

 The number of the nodes in the list is in the range [0, 104].
 -105 <= Node.val <= 105
 pos is -1 or a valid index in the linked-list.


 Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class LeetCode142 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = l1.next;

        ListNode res = detectCycle(l1);

        System.out.println();


    }


    /**
     * 双指针法：检测链表中的环并返回环的起始节点
     * Two-pointer approach: Detect a cycle in a linked list and return the node where the cycle begins.
     *
     * 快慢指针一定会在环中相遇。假设链表头到环起点的距离为k，相遇点到链表头的距离为m。
     * The fast and slow pointers are guaranteed to meet inside the cycle. Let the distance from the head
     * to the cycle's start be k, and the distance from the meeting point to the head be m.
     *
     * 当两个指针相遇时，慢指针已经走了m步，快指针走了2m步。
     * Since the fast pointer moves twice as fast as the slow one, when they meet,
     * the slow pointer has traveled m steps, and the fast pointer has traveled 2m steps.
     *
     * 因为快指针比慢指针多走了一整圈的环长，所以环长为m。此时，慢指针距离环起点的距离是k ---  m - (m - k) = k。
     * The fast pointer has traveled one extra full cycle, so the circle is m steps,
     * at this point,the slow pointer is k steps away from the start of the cycle.
     *
     * 接下来，重置快指针为链表头，两个指针每次向后移动一步，最终会在环起点相遇。
     * Reset the fast pointer to the head of the list, and move both pointers one step at a time.
     * They will meet at the start of the cycle after k steps.
     *
     * @param head 链表头节点 - The head node of the linked list
     * @return 如果有环，返回环的起始节点；否则返回null - If a cycle exists, return the starting node of the cycle, otherwise return null.
     */
    public static ListNode detectCycle(ListNode head) {
        // 初始化快慢指针
        // Initialize the slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;

        // 如果链表为空或链表只有一个节点且没有环，直接返回null
        // If the list is empty or contains only one node, there is no cycle.
        if (fast == null || fast.next == null) {
            return null;
        }

        // 快慢指针移动，直到相遇或快指针到达链表末尾
        // Move the fast and slow pointers until they meet or the fast pointer reaches the end of the list.
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 慢指针每次走一步 - Slow pointer moves one step at a time
            fast = fast.next.next;   // 快指针每次走两步 - Fast pointer moves two steps at a time

            // 快慢指针相遇，说明存在环
            // If the fast and slow pointers meet, there is a cycle in the list
            if (slow == fast) {
                // 将快指针重置到链表头，两指针同时一步步前进
                // Reset the fast pointer to the head, and move both pointers one step at a time
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                // 相遇处即为环的起点
                // The point where they meet is the start of the cycle
                return slow;
            }
        }

        // 没有环，返回null
        // If no cycle is found, return null
        return null;
    }


    /**
     * 哈希表法：使用哈希表存储已访问的节点
     * Hashing method: Use a hash table to store visited nodes.
     *
     * 时间复杂度: O(n) - 需要遍历整个链表
     * Time Complexity: O(n) - We need to traverse the entire list.
     *
     * 空间复杂度: O(n) - 需要额外的空间来存储节点
     * Space Complexity: O(n) - Additional space to store visited nodes.
     *
     * @param head 链表头节点 - The head node of the linked list
     * @return 如果有环，返回环的起始节点；否则返回null - If a cycle exists, return the starting node of the cycle, otherwise return null.
     */
    public static ListNode detectCycle1(ListNode head) {
        // 使用哈希表来存储已访问过的节点
        // Use a hash set to store visited nodes
        Set<ListNode> visited = new HashSet<>();

        // 遍历链表
        while (head != null) {
            // 如果当前节点已经在哈希表中，说明存在环，返回该节点
            // If the current node is already in the set, a cycle exists, return this node
            if (visited.contains(head)) {
                return head;
            }
            // 否则，将当前节点加入哈希表
            // Otherwise, add the current node to the set
            visited.add(head);
            head = head.next;
        }

        // 如果遍历完链表没有找到环，返回null
        // If the entire list is traversed with no cycle, return null
        return null;
    }


    /**
     * 该方法不能通过用例，只是能检测环在哪个位置，除此之外可以通过给链表增加visited字段等来标识，原理跟HashSet类似
     *
     * 破坏链表结构法：通过修改链表结构检测环
     * Destructive method: Detect a cycle by modifying the list structure.
     *
     * 时间复杂度: O(n) - 需要遍历整个链表
     * Time Complexity: O(n) - We need to traverse the entire list.
     *
     * 空间复杂度: O(1) - 不需要额外的空间存储
     * Space Complexity: O(1) - No additional space is needed.
     *
     * @param head 链表头节点 - The head node of the linked list
     * @return 如果有环，返回环的起始节点；否则返回null - If a cycle exists, return the starting node of the cycle, otherwise return null.
     */
    public static ListNode detectCycleByModifyingStructure(ListNode head) {
        // 遍历链表
        while (head != null) {
            // 如果当前节点的next指针指向自己，说明检测到环
            // If the next pointer points to the node itself, a cycle is detected
            if (head.next == head) {
                return head;
            }
            // 暂存下一个节点，并将当前节点的next指针指向自己
            // Temporarily store the next node and point the current node's next to itself
            ListNode nextNode = head.next;
            head.next = head;
            head = nextNode;
        }

        // 如果遍历完链表没有找到环，返回null
        // If no cycle is found, return null
        return null;
    }




}
