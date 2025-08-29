package datastructure.tree;

/**
 * 116. Populating Next Right Pointers in Each Node
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * 
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 * 
 * Input: root = []
 * Output: []
 */

public class LeetCode116 {
    public static void main(String[] args) {
        // Create individual nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        // Link nodes to form a tree
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        connect1(node1);

        System.out.println();
    }


    /**
     * Notice that the tree is perfect binary tree!
     * Recursively connect nodes starting from the root's children level.
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root == null) return null;
        if (root.left == null) return root;
        if (root.right == null) return root;
        doRecursion(root.left, root.right);
        return root;
    }


    private static void doRecursion(Node left, Node right) {
        if (left == null || right == null) return;

        // Connect the left node's next to the right node
        left.next = right;

        // 这段冗余了，因为doRecursion(left.right, right.left);这个递归就可以实现
//        if (left.right != null) {
//            left.right.next = right.left;
//        }

        // Connect the children of the left and right nodes
        doRecursion(left.left, left.right);
        doRecursion(right.left, right.right);

        // Connect the right child of the left node to the left child of the right node
        doRecursion(left.right, right.left);
    }


    /**
     * Recursively connect nodes starting from the root level.
     * @param root the root node of the binary tree
     * @return the root node with updated next pointers
     */
    public static Node connect1(Node root) {
        // No processing needed for these cases
        if (root == null || root.left == null || root.right == null) return root;
        // Connect the left child to the right child
        root.left.next = root.right;
        // If the root has a sibling, they are connected with the next pointer when it is executed here,
        // then connect the right child to the left child of the next sibling
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        // Continue recursion for left and right children
        connect1(root.left);
        connect1(root.right);
        return root;
    }



}
