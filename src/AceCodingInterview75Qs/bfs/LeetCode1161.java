package AceCodingInterview75Qs.bfs;

/*
 *
 *
 *
1161. Maximum Level Sum of a Binary Tree
Medium

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.


Example 1:

Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation:
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2


Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105

 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode1161 {



    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();

        LeetCode1161 example = new LeetCode1161();
        System.out.println(example.maxLevelSum(tree));
    }

    // BFS。按行累加取最大值。
    public int maxLevelSum(TreeNode root) {
        int curLevel = 0;
        int maxLevel = 0;
        int maxSum = Integer.MIN_VALUE;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            // for外层控制树的行数以及每行遍历多少个节点。
            curLevel++;
            int levelSum = 0;
            int size = deque.size();
            // 遍历这层元素
            for (int i = 0; i < size; i++){
                TreeNode node = deque.removeFirst();
                levelSum += node.val;
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            if (levelSum > maxSum){
                maxSum = levelSum;
                maxLevel = curLevel;
            }
        }
        return maxLevel;
    }


}