package datastructure.tree;

/**
 104. Maximum Depth of Binary Tree

 Given the root of a binary tree, return its maximum depth.

 A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Example 1:


 Input: root = [3,9,20,null,null,15,7]
 Output: 3
 Example 2:

 Input: root = [1,null,2]
 Output: 2

 Constraints:

 The number of nodes in the tree is in the range [0, 104].
 -100 <= Node.val <= 100
 */

public class LeetCode104 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();

        System.out.println(maxDepth(tree));
    }

    /**
     * Recursive formula: The maximum height of the current node = MaxDepth(left subtree height, right subtree height) + 1
     * Termination condition: The height is 0 when the current node is null
     * @param root the root node of the binary tree
     * @return the maximum depth of the binary tree
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }



}
