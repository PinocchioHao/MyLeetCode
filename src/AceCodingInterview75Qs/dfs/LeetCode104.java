package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

104. Maximum Depth of Binary Tree

Medium

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


 *
 *
 */

import datastructure.linkedlist.ListNode;
import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode104 {


    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
//        tree2.left = new TreeNode(3);
        LeetCode104 example = new LeetCode104();

        System.out.println(example.maxDepth(tree));
    }

    // 自底向上，自己的高度取决于子孩子的最大高度+1，先跑到最底层，最后解递归时depth+1
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }


    public int maxDepth1(TreeNode root) {
        return dfs(root,0);
    }

    // 自顶向下，带着状态递归，每向下一层就把depth+1传给孩子，找到叶子就把depth报出来
    public int dfs(TreeNode root, int depth){
        if (root==null) return depth;
        return Math.max(dfs(root.left,depth + 1), dfs(root.right, depth +1));
    }

}