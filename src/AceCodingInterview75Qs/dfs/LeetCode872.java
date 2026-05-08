package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

872. Leaf-Similar Trees

Medium

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.



Example 1:


Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true
Example 2:


Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false


Constraints:

The number of nodes in each tree will be in the range [1, 200].
Both of the given trees will have values in the range [0, 200].


 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode872 {


    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left = new TreeNode(3);
        LeetCode872 example = new LeetCode872();

        System.out.println(example.leafSimilar(tree, tree2));
    }

    // dfs遍历到叶子记录值到list中，比较list即可
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        getLeavesVal(root1, l1);
        getLeavesVal(root2, l2);
        return l1.equals(l2);
    }

    public void getLeavesVal(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        getLeavesVal(root.left, res);
        getLeavesVal(root.right, res);
    }

}