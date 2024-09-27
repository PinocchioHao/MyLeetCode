package recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 101. Symmetric Tree

 Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

 Example 1:

 Input: root = [1,2,2,3,4,4,3]
 Output: true
 Example 2:

 Input: root = [1,2,2,null,3,null,3]
 Output: false

 */

public class LeetCode101 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
//        tree2.left = new TreeNode(3);

        System.out.println(isSymmetric(tree));

    }


    public static boolean isSymmetric(TreeNode root) {
        // If the tree is empty, it is symmetric
        if (root == null) return true;
        // Use a helper function to compare the left and right subtrees
        return isSymmetricRecHelper(root.left, root.right);
    }

    public static boolean isSymmetricRecHelper(TreeNode left, TreeNode right) {
        // If both subtrees are empty, they are symmetric
        if (left == null && right == null) return true;
        // If one subtree is empty and the other is not, they are not symmetric
        if (left == null || right == null) return false;
        // If the values of the current nodes are different, they are not symmetric
        if (left.val != right.val) return false;
        // Recursively check the left subtree of the left node and the right subtree of the right node,
        // and the right subtree of the left node and the left subtree of the right node
        return isSymmetricRecHelper(left.left, right.right) && isSymmetricRecHelper(left.right, right.left);
    }


    /**
     * Iteration solution
     * We can use the characteristics of queue
     * push, extract, compare two items at once,
     * as the queue is sequential, we can compare left-right and right-left in order
     * @param root
     * @return
     */
    public static boolean isSymmetric1(TreeNode root) {
        // If the tree is empty, it is symmetric
        if (root == null) return true;

        // Initialize a queue to hold pairs of nodes to be compared
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            // Extract two nodes to compare
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // If both nodes are null, continue to the next pair
            if (left == null && right == null) continue;
            // If one of the nodes is null or their values are not equal, the tree is not symmetric
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            // Add the children of the nodes to the queue in the correct order
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }

        // If all pairs of nodes are symmetric, return true
        return true;
    }






}