package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

112. Path Sum II

Medium

Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000

 *
 *
 */

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode113 {



    public static void main(String[] args) {
//        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);

        LeetCode113 example = new LeetCode113();
        System.out.println(example.pathSum(tree, 3));
    }



    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        dfs(root, targetSum, curPath, res);
        return res;
    }

    /**
     * dfs+回溯
     * list记录当前走过的路径，每到一个节点，把它加进路径list。
     * 如果是叶子节点且和正好抵消，就把当前的 path 复制一份存入结果集。
     * 在返回父节点之前，要把自己从 path 里删掉，不能影响下一条路的探测。
      */
    void dfs(TreeNode node, int targetSum, List<Integer> curPath, List<List<Integer>> res){
        // 空节点直接返回
        if (node == null) return;
        // 非空节点每访问到就把当前值记录到curPath数组中
        curPath.add(node.val);
        // 叶节点则判断和值
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                // 回溯法记得存数据的时候要new一个新的
                // 这里如果res.add(curPath)，add进去的是引用，如果对数据进行后续操作也会同步操作到res结果集中，最后会是一个空数组
                res.add(new ArrayList<>(curPath));
            }
        } else {
            // 否则递归遍历左右孩子节点
            dfs(node.left, targetSum - node.val, curPath, res);
            dfs(node.right, targetSum - node.val, curPath, res);
        }
        // 回溯操作 -- 这里是遍历完当前节点，退出到上一层的父节点，需要把自己从curPath中删除
        curPath.removeLast();

    }



}