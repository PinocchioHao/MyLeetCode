package datastructure.linkedlist;

import java.util.HashMap;

/**
 * 138. Copy List with Random Pointer
 * Medium
 *
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 *
 * Example 1:
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 * Constraints:
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 */
public class LeetCode138 {

    public static void main(String[] args) {

        Node l2 = new Node(1);
        l2.next = new Node(2);
//        l2.next.next = new Node(3);
//        l2.next.next.next = new Node(4);

        l2.random = l2.next;
        l2.next.random = l2.next;


        Node res = copyRandomList1(l2);

        System.out.println();


    }


    /**
     * 复制随机链表 - 复制包含随机指针的链表
     * 首先检查链表头是否为空，如果为空则直接返回null
     * 创建一个新节点作为新的链表的头节点
     * 使用两个HashMap，一个用于存储旧节点的随机指针，另一个用于将旧节点映射到新节点
     * 通过遍历旧链表构建新链表，并更新映射
     *
     * Copy a linked list with random pointers - Copy a linked list that includes random pointers
     * First, check if the head of the list is null; if so, return null
     * Create a new node as the head of the new list
     * Use two HashMaps: one to store the random pointers of old nodes, and another to map old nodes to new nodes
     * Traverse the old list to construct the new list and update the mappings
     *
     * @param head 原链表的头节点 / Head of the original linked list
     * @return 复制后的链表头节点 / Head of the copied linked list
     */
    public static Node copyRandomList(Node head) {
        // 检查链表头是否为空 / Check if the head of the list is null
        if (head == null) return null; // 如果为空，直接返回null / Return null if it's null

        // 创建新节点作为新链表的头节点 / Create a new node as the head of the new list
        Node newRandowList = new Node(head.val);
        Node dummy = new Node(0); // 创建一个虚拟头节点，用于方便处理
        dummy.next = newRandowList; // 将虚拟头节点的next指向新链表的头节点
        Node point2 = newRandowList; // 用于遍历新链表

        // 使用HashMap来存储旧节点的随机指针和映射 / Use HashMaps to store the old nodes' random pointers and mappings
        HashMap<Integer, Node> oldRandomMap = new HashMap<>(); // 存储旧节点的随机指针
        HashMap<Node, Node> old2NewMap = new HashMap<>(); // 存储旧节点到新节点的映射
        int i = 0;
        // 注意边界条件！！！
        // 将第一个节点的随机指针添加到映射中 / Add the random pointer of the first node to the mapping
        oldRandomMap.put(i, head.random);
        old2NewMap.put(head, newRandowList); // 将旧节点映射到新节点

        // 遍历旧链表，构建新链表并更新映射 / Traverse the old list to build the new list and update the mappings
        while (head.next != null) {
            newRandowList.next = new Node(head.next.val); // 创建新节点并连接
            oldRandomMap.put(++i, head.next.random); // 更新旧节点的随机指针映射
            old2NewMap.put(head.next, newRandowList.next); // 更新旧节点到新节点的映射
            head = head.next; // 移动到下一个旧节点
            newRandowList = newRandowList.next; // 移动到下一个新节点
        }

        // 更新新链表的随机指针 / Update the random pointers of the new list
        int j = 0;
        while (point2 != null) { // 遍历新链表
            Node newRandow = old2NewMap.get(oldRandomMap.get(j)); // 获取新节点的随机指针
            point2.random = newRandow; // 更新新节点的随机指针
            point2 = point2.next; // 移动到下一个新节点
            j++; // 更新计数器
        }

        // 返回新链表的头节点 / Return the head of the new list
        return dummy.next;
    }


    /**
     * HashSet改进版 - 只需要一个HashMap<Node, Node> 来存储新老节点对应关系即可
     * Improved version using HashSet - Only one HashMap<Node, Node> is needed to store the mapping of old and new nodes.
     * 第一步创建所有节点，第二步遍历所有节点，补充next和random值即可
     * First, create all nodes; second, traverse all nodes to set the next and random pointers.
     *
     * @param head 链表的头节点
     * @return 复制链表的头节点
     * @return The head node of the copied list.
     */
    public static Node copyRandomList1(Node head) {
        // 如果链表为空，返回null
        // Return null if the list is empty
        if (head == null) return null;

        Node cur = head;
        // 创建一个HashMap来存储旧节点到新节点的映射
        // Create a HashMap to store the mapping from old nodes to new nodes
        HashMap<Node, Node> oldtoNewMap = new HashMap<>();

        // 创建相应节点
        // Create corresponding nodes
        while (cur != null) {
            oldtoNewMap.put(cur, new Node(cur.val)); // 将旧节点和新节点的映射关系存入HashMap
            cur = cur.next; // 移动到下一个节点
        }

        cur = head; // 重新指向链表的头节点

        // 连接next和random
        // Connect the next and random pointers
        while (cur != null) {
            Node newRandow = oldtoNewMap.get(cur); // 获取当前节点对应的新节点
            newRandow.next = oldtoNewMap.get(cur.next); // 设置next指针
            newRandow.random = oldtoNewMap.get(cur.random); // 设置random指针
            cur = cur.next; // 移动到下一个节点
        }

        // 返回复制链表的头节点
        // Return the head node of the copied list
        return oldtoNewMap.get(head);
    }


}
