package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View
 * Medium
 * <p>
 * Given the of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.root
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 * <p>
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range .[0, 100]
 * -100 <= Node.val <= 100
 */

public class LeetCode199 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left.right.left = new TreeNode(11);

        System.out.println(rightSideView(tree2));
        System.out.println(rightSideView1(tree2));

    }


    /**
     * Depth-First Search (DFS) approach.
     * Traverse to the right child first to capture the rightmost nodes at each level.
     * Track the current level of the root, and if the current level is greater than the size of the result list,
     * add the node's value to the result list (since it's the rightmost node at that level).
     *
     * @param root The root of the binary tree.
     * @return A list of integers representing the values seen from the right side view of the binary tree.
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        // Start recursive DFS traversal
        doRecursion(root, res, 0);
        return res;
    }

    /**
     * Recursive helper function for DFS.
     * It checks if the current node should be added to the result list
     * (i.e., if it’s the first node encountered at that level, which will be the rightmost node).
     *
     * @param root      The current node in the binary tree.
     * @param res       The list storing the rightmost values at each level.
     * @param currLevel The current level in the binary tree.
     */
    private static void doRecursion(TreeNode root, List<Integer> res, int currLevel) {
        if (root == null) return;

        // If we're at a new level (i.e., the first time visiting this level), add the node's value.
        if (currLevel >= res.size()) {
            res.add(root.val);
        }

        // For the right-side view, we traverse the right subtree first.
        doRecursion(root.right, res, currLevel + 1);

        // Then traverse the left subtree.
        doRecursion(root.left, res, currLevel + 1);
    }


    /**
     * Breadth-First Search (BFS) approach.
     * For each level of the tree, the algorithm finds the rightmost value and adds it to the result list.
     *
     * @param root The root of the binary tree.
     * @return A list of integers representing the values seen from the right side view of the binary tree.
     */
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // If the tree is empty, return an empty list.
        if (root == null) return res;

        // Initialize a queue for BFS traversal, starting with the root node.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Perform BFS level by level.
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level.
            int levelNodeNum = queue.size();

            // Traverse all nodes at this level.
            for (int i = 0; i < levelNodeNum; i++) {
                TreeNode node = queue.poll();

                // If it's the last node of the current level, add it to the result list (rightmost node).
                if (i == levelNodeNum - 1) {
                    res.add(node.val);
                }

                // Continue BFS by adding the left and right children to the queue.
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }


}
