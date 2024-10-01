package linkedlist;

/**
 * 2. Add Two Numbers
 24. Swap Nodes in Pairs
 Medium

 Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

 Example 1:

 Input: head = [1,2,3,4]

 Output: [2,1,4,3]

 Explanation:

 Example 2:

 Input: head = []

 Output: []

 Example 3:

 Input: head = [1]

 Output: [1]

 Example 4:

 Input: head = [1,2,3]

 Output: [2,1,3]

 Constraints:

 The number of nodes in the list is in the range [0, 100].
 0 <= Node.val <= 100
 */
public class LeetCode24 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(5);
        ListNode rlt = swapPairs(l1);
        ListNode rlt2 = swapPairs1(l2);
        System.out.println(rlt);
    }

    /**
     * 记录当前节点的前一节点和后一节点，注意转换顺序
     * Keep track of the previous and next nodes of the current node, ensuring correct swapping order
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        // 创建一个虚拟节点，方便处理头节点
        // Create a dummy node to simplify edge case handling for the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 初始化 prev 指向虚拟节点，cur 指向头节点，注意prev需要跟随cur的移动随时维护，在外层定义
        // Initialize prev to the dummy node and cur to the head node.
        // Note: prev needs to move along with cur and maintain its reference, so it's defined outside the loop.
        ListNode cur = head;
        ListNode prev = dummy;

        // 只要当前节点及其下一个节点存在，则可以进行交换
        // Swap as long as cur and cur.next are not null (valid pairs for swapping)
        while (cur != null && cur.next != null) {
            // 当前节点的下一个节点
            // The next node after cur
            ListNode next = cur.next;
            // 将当前节点指向 next 的下一个节点，跳过 next
            // Set cur.next to next.next to skip over the next node
            cur.next = next.next;
            // 将 next 的 next 指向当前节点，完成交换
            // Set next.next to cur to complete the swap between cur and next
            next.next = cur;
            // 将前一个节点的 next 指向 next，使前一个节点与交换后的部分连上
            // Link prev.next to next, connecting the previous node to the swapped pair
            prev.next = next;
            // 本次交换操作完毕，以下代码为进入下一次循环的准备
            // Prepare for the next iteration after completing the swap
            // prev 移动到当前节点，准备进行下一对的交换
            // Move prev to cur, preparing for the next pair swap
            prev = cur;
            // cur 移动到下一个节点，进行下一轮交换
            // Move cur to the next node (which is two nodes ahead) for the next swap iteration
            cur = cur.next;
        }
        // 返回新的链表头节点
        // Return the new head node (dummy.next), as the list might have been updated
        return dummy.next;
    }

    /**
     * 递归解法 - 先执行递操作到链表末端，再将末端交换的结果往前给前方的归操作的next相连
     * Recursive solution - First, recursively traverse to the end of the list, then connect the swapped result back to the previous nodes.
     * @param head
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        // 基本情况：如果链表为空或只有一个节点，直接返回 head
        // Base case: If the list is empty or has only one node, return head as is
        if (head == null || head.next == null) {
            return head;
        } else {
            // 记录当前节点和下一个节点
            // Keep track of the current node (first) and the next node (second)
            ListNode first = head;                // 当前节点
            ListNode second = first.next;         // 下一个节点

            // 递 - 将当前节点的 next 指向递归后的两个位置之后交换的节点
            // Recursive step - Set first.next to the result of the recursive call after swapping the next pair
            first.next = swapPairs1(first.next.next);
            // 交换后，将第二个节点的 next 指向当前节点
            // After swapping, point second.next to first to complete the swap
            second.next = first;

            // 归 - 交换之后 second 就在前面，将 second 返回给递归上层（前两个节点）的 next 相连
            // Returning second connects the swapped pair back to the previous nodes in the recursion
            return second;  // Return the new head node (which is second after swapping)
        }
    }

}