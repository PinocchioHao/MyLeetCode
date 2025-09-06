package datastructure.tree;

import java.util.*;

/**
 * 102. Binary Tree Level Order Traversal
 * Medium
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */

public class LeetCode102 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();

        System.out.println(levelOrder(tree));
        System.out.println(levelOrder1(tree));

    }

    /**
     * bfs
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        // The result list, each sublist stores the values of one level
        List<List<Integer>> rlt = new ArrayList();
        if (root == null) return rlt; // If the tree is empty, return empty list

        // Use a queue to perform BFS (breadth-first search)
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // Process nodes level by level
        while (!q.isEmpty()) {
            // The number of nodes in the current level
            int levelTreeNum = q.size();
            // A temporary list to store values of this level
            List<Integer> levelTreeVal = new ArrayList<>();

            // Traverse all nodes of the current level
            for (int i = 0; i < levelTreeNum; i++) {
                TreeNode node = q.poll(); // Get and remove the head of the queue
                if (node != null) {
                    levelTreeVal.add(node.val); // Add node value to this level
                    // Add children to queue for the next level
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
            // Add this level's values into the result
            rlt.add(levelTreeVal);
        }
        return rlt;
    }

    /**
     * dfs
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> rlt = new ArrayList();
        if (root == null) return rlt;
        int level = 0;
        dfs(root, level, rlt);
        return rlt;
    }


    public static void dfs(TreeNode root, int level, List<List<Integer>> rlt) {
        if (root == null) return;

        // If visiting this level for the first time, create a new list
        if (rlt.size() == level) {
            List<Integer> levelTreeVal = new ArrayList<>();
            rlt.add(levelTreeVal);
        }

        // Add current node value to its level list
        rlt.get(level).add(root.val);

        // DFS left and right child
        dfs(root.left, level + 1, rlt);
        dfs(root.right, level + 1, rlt);
    }
}