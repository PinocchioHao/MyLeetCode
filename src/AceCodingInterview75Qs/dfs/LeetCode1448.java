package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

1448. Count Good Nodes in Binary Tree

Medium

Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.



Example 1:



Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
Example 2:



Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.


Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].


 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.*;

public class LeetCode1448 {

    // 用于方法二的全局计数器
    int globalCnt = 0;

    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left = new TreeNode(3);
        LeetCode1448 example = new LeetCode1448();

        System.out.println(example.goodNodes(tree));
    }


    public int goodNodes(TreeNode root) {
        // 题目规定根节点永远是 good node，初始最大值设为根节点值即可
        return dfsAggregation(root, root.val);
    }

    /**
     * 思路一：自底向上汇总 (Bottom-up Aggregation)
     * 核心逻辑：当前节点是否为好节点 + 左子树的好节点数 + 右子树的好节点数。
     * 这种方式不依赖外部变量，逻辑闭环，是递归最优雅的写法。
     *
     * @param root   当前遍历到的节点
     * @param maxVal 从根节点到当前节点路径上的最大值
     * @return 以当前节点为根的子树中“好节点”的总数
     */
    public int dfsAggregation(TreeNode root, int maxVal) {
        // 1. 递归终止条件
        if (root == null) return 0;

        int currentIsGood = 0;
        // 2. 判断当前节点是否为“好节点”
        if (root.val >= maxVal) {
            currentIsGood = 1;
            // 3. 更新路径最大值：下潜到子节点时，带着更新后的最大值
            maxVal = root.val;
        }

        // 4. 递归计算左右子树，并把结果汇总给上一层
        // 注意：这里的 maxVal 会随方法栈自动回溯，每次都是当前这条线路的最大值，不会影响右子树的判断
        int leftGoodNodes = dfsAggregation(root.left, maxVal);
        int rightGoodNodes = dfsAggregation(root.right, maxVal);
        // 往上归的时候把结果给上一层
        return currentIsGood + leftGoodNodes + rightGoodNodes;
    }

    /**
     * 思路二：自顶向下统计 (Top-down Counting)
     * 核心逻辑：通过一个外部变量记录状态，遍历过程中只要发现符合条件的节点，就给计数器 +1。
     * 这种方式更符合“带着任务去旅行”的思维。
     *
     * @param root   当前遍历到的节点
     * @param maxVal 路径上的最大值
     */
    public void dfsCounting(TreeNode root, int maxVal) {
        // 1. 递归终止条件
        if (root == null) return;

        // 2. 如果当前节点满足条件，直接修改全局变量
        if (root.val >= maxVal) {
            globalCnt++;
            // 更新路径最大值
            maxVal = root.val;
        }

        // 3. 继续向下探测，不需要关心返回值
        dfsCounting(root.left, maxVal);
        dfsCounting(root.right, maxVal);
    }

}