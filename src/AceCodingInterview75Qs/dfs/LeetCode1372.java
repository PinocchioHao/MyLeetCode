package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *
1372. Longest ZigZag Path in a Binary Tree
Medium

You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.



Example 1:


Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
Example 2:


Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
Example 3:

Input: root = [1]
Output: 0


Constraints:

The number of nodes in the tree is in the range [1, 5 * 104].
1 <= Node.val <= 100
 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

public class LeetCode1372 {

    int maxPath = 0;
    int maxPath1 = 0;

    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left = new TreeNode(3);
        LeetCode1372 example = new LeetCode1372();

        System.out.println(example.longestZigZag(tree));
    }

    // 暴力解法，遍历所有节点，以该节点为根向下进行zigzag遍历
    public int longestZigZag(TreeNode root) {
        if (root == null)
            return 0;
        // 找出当前节点向左和向右最大的zigzag长度
        int rootMax = Math.max(findPathLen(root, true), findPathLen(root, false));
        // 更新最长zigzag长度
        maxPath = Math.max(rootMax, maxPath);
        // 递归遍历所有节点
        longestZigZag(root.left);
        longestZigZag(root.right);
        return maxPath;
    }

    // 找出以当前节点+方向的zigzag长度
    private int findPathLen(TreeNode node, boolean goLeft) {
        // 空节点长度为0
        if (node == null)
            return 0;
        // cnt记录遍历节点数
        int cnt = 0;
        while (node != null) {
            // 锯齿遍历
            if (goLeft) {
                node = node.left;
            } else{
                node = node.right;
            }
            // 每次遍历完长度递增，方向反转
            cnt++;
            goLeft = !goLeft;
        }
        // 路径长度为节点数-1
        return cnt - 1;
    }


    // 状态标记法，只dfs遍历一遍树
    public int longestZigZag1(TreeNode root){
        // 树为空返回0
        if(root == null) return 0;
        // dfs遍历树，由于是根节点出发向左右孩子，此时路径长初始为1
        dfs(root.left, true, 1);
        dfs(root.right, false, 1);
        return maxPath1;
    }

    public void dfs(TreeNode node, boolean goLeft, int len){
        if(node == null) return;
        // 非空更新最长路径
        maxPath1 = Math.max(maxPath1, len);
        // 递归zigzag遍历
        if (goLeft) {
            // 如果进入这个节点是向左走的，那么递归向右走能延续这个长度，len+1
            dfs(node.right, false, len + 1);
            // 此时向左右则不符合原来zigzag规律，自己开辟一条新路径计算长度
            dfs(node.left, true, 1);
        } else {
            // 进入这个节点的是向右走的，此时向左右能延续zigzag，向右走则新开路径
            dfs(node.left, true, len + 1);
            dfs(node.right, false, 1);
        }
    }


}