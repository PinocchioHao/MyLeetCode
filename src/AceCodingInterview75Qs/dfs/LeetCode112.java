package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

112. Path Sum

Easy

Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000


 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

public class LeetCode112 {



    public static void main(String[] args) {
//        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);

        LeetCode112 example = new LeetCode112();
        System.out.println(example.hasPathSum(tree, 1));
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return dfs(root, 0, targetSum);
    }

    // 自顶向下dfs，进入一层就累加当前路径的值
    boolean dfs(TreeNode node, int curSum, int targetSum){
        // 遍历到空节点返回false
        if (node == null) return false;
        // 当前节点非空则累加
        curSum += node.val;
        // 当前为叶子节点则判断当前路径的累加值是否为target
        if (node!=null && node.left==null&&node.right==null) {
            return curSum == targetSum;
        }
        // 如果不是叶子节点则继续带着当前累加值往左右孩子递归，左右孩子其中找到一条符合即可
        return dfs(node.left, curSum, targetSum) || dfs(node.right, curSum, targetSum);
    }



    // 自顶向下，用减法
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        // 空树没有路径
        if(root == null) return false;
        // 叶子节点则判断值
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 否则继续向下探索
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);
    }


}