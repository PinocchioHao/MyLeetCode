package AceCodingInterview75Qs.binary_search_tree;

/*
 *
 *
 *
700. Search in a Binary Search Tree
Easy

You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.



Example 1:


Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]
Example 2:


Input: root = [4,2,7,1,3], val = 5
Output: []


Constraints:

The number of nodes in the tree is in the range [1, 5000].
1 <= Node.val <= 107
root is a binary search tree.
1 <= val <= 107
 *
 *
 */

import datastructure.tree.TreeNode;

public class LeetCode700 {



    public static void main(String[] args) {
//        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);

        LeetCode700 example = new LeetCode700();
        System.out.println(example.searchBST(tree, 1));
    }

    // 利用BST特性，小于当前节点则往左搜索，大于则往右搜索，找到则直接返回节点。
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(val == root.val) return root;
        if(val < root.val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

}