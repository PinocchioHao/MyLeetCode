package recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * Medium
 * <p>
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 * <p>
 * Follow-up:
 * <p>
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */

public class LeetCode117 {
    public static void main(String[] args) {
        // Create individual nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(7);

        // Link nodes to form a tree
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

//        connect(node1);

        connect1(node1);

        System.out.println();
    }


    /**
     * bfs -- 层序遍历连接每层的各个节点，最后一个节点置空
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                Node node = queue.poll();
                if (i == levelNum - 1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);

            }
        }
        return root;
    }

    /**
     * Linked List Method - Note that root traverses the current level,
     * while dummy and cur are at the next level.
     * @param root
     * @return
     */
    public static Node connect1(Node root) {
        // Root node
        Node head = root;

        while (root != null) {
            // New sentinel node for each level
            Node dummy = new Node(-1);
            // Pointer for the current level, used to connect nodes at the next level
            Node cur = dummy;

            // Traverse all nodes at the current level
            while (root != null) {
                if (root.left != null) {
                    // Connect the left child, note that this operation affects both cur and dummy.next at first
                    cur.next = root.left;
                    // Move cur to root.left
                    cur = cur.next;
                }
                if (root.right != null) {
                    // Connect the right child
                    cur.next = root.right;
                    // Move cur to root.right
                    cur = cur.next;
                }
                // Move to the next node at the current level
                root = root.next;
            }

            // Move root to the first node of the next level
            root = dummy.next;
        }
        // Return the root node
        return head;
    }

    /**
     * Improved Linked List Method - Avoid creating a new sentinel node for each level to save overhead.
     * @param root
     * @return
     */
    public static Node connect2(Node root) {
        // Sentinel node, used to point to the first node of each level
        Node dummy = new Node(-1);
        // Pointer for the current level, used to connect nodes at the next level
        Node cur = dummy;
        // Root node
        Node head = root;

        while (root != null) {
            // Traverse all nodes at the current level
            if (root.left != null) {
                // Connect the left child, note that this operation affects both cur and dummy.next at first
                cur.next = root.left;
                // Move cur to root.left
                cur = cur.next;
            }
            if (root.right != null) {
                // Connect the right child
                cur.next = root.right;
                // Move cur to root.right
                cur = cur.next;
            }
            // Move to the next node at the current level
            root = root.next;

            // Current level traversal is complete
            if (root == null) {
                // Since cur initially equals dummy, and dummy.next was set during cur.next
                // Reset pointer, prepare to connect nodes at the next level
                cur = dummy;
                // Move to the first node of the next level
                root = dummy.next;
                // Clear the next pointer of the sentinel node
                dummy.next = null;
            }
        }
        // Return the root node
        return head;
    }





}
