package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree
 * Easy
 * 
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example 1:
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * 
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 */

public class LeetCode111 {

    // 全局变量
    static int minDepth = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Integer[] arr = {2,null,3,null,4,null,5,null,6};

        TreeNode tree = TreeNodeUtils.createBinaryTreeByArray(arr);

        System.out.println(minDepth(tree));
        System.out.println(minDepth1(tree));
        System.out.println(minDepth2(tree));

    }


    /**
     * 只有一个子节点，则当前最低高度为1+另一个子节点最低高度；
     * 有两个子节点，则当前节点最低高度为左右子节点最低高度+1
     * If there is only one child node, the current minimum height is 1 + the minimum height of the other child node;
     * If there are two child nodes, the current minimum height is the minimum height of the left and right child nodes + 1
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 注意：只有一个子节点的情况需要单独考虑！
        // Note: The case of having only one child node needs to be considered separately!
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        // 有两个子节点的情况
        // The case of having two child nodes
        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);
        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }

    /**
     * 定义全局变量，dfs递归遍历树，在向下递的过程中找最小深度
     * Define a global variable, use DFS to recursively traverse the tree, and find the minimum depth during the descent
     * @param root
     * @return
     */
    public static int minDepth1(TreeNode root) {
        if (root == null) return 0;
        // 注意root非空，初始高度为1
        // Note: root is not null, initial height is 1
        minDepthHelper(root, 1);
        return minDepth;
    }

    /**
     * 记录深度dfs遍历树，并且更新最小深度全局变量，附带剪枝操作
     * Record depth, DFS traverse the tree, and update the global variable for minimum depth, with pruning
     * @param root
     * @param depth
     */
    public static void minDepthHelper(TreeNode root, int depth) {
        if (root == null) return;

        // 当前节点如果是叶子节点，则更新最小深度 -- 在递的过程就要计算深度
        // If the current node is a leaf node, update the minimum depth -- calculate depth during the descent
        if (root.left == null && root.right == null) {
            minDepth = Math.min(depth, minDepth);
        }
        // 剪枝操作：找到最小深度后无需再继续遍历，直接返回
        // Pruning: No need to continue traversal after finding the minimum depth, directly return
        if (depth >= minDepth) {
            return;
        }

        // 否则继续向下递归
        // Otherwise, continue to recurse downwards
        minDepthHelper(root.left, depth + 1);
        minDepthHelper(root.right, depth + 1);
    }

    /**
     * 广搜 - 搜到第一个叶子节点就返回深度
     * Breadth-First Search (BFS) - Return depth upon finding the first leaf node
     */
    public static int minDepth2(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 非空节点初始高度为1
        // Initial height for non-null node is 1
        int depth = 1;
        while (!queue.isEmpty()) {
            // 当前层节点数
            // Number of nodes at the current level
            int levelNodeNum = queue.size();
            for (int i = 0; i < levelNodeNum; i++) {
                TreeNode node = queue.poll();
                // 找到叶节点直接返回此时高度为最低高度
                // Return the current depth as the minimum depth upon finding a leaf node
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 遍历完一层后深度递增
            // Increment depth after traversing one level
            depth++;
        }
        return depth;
    }



}