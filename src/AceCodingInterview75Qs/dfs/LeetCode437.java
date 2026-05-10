package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *

437. Path Sum III

Medium

Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).



Example 1:


Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3


Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000

 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.HashMap;
import java.util.Map;

public class LeetCode437 {

    // 全局计数器
    int globalCnt = 0;
    int globalCnt1 = 0;

    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left = new TreeNode(3);
        LeetCode437 example = new LeetCode437();

        System.out.println(example.pathSum(tree, 8));
        System.out.println(example.pathSum1(tree, 8));
    }

    // 暴力法，嵌套dfs
    // 遍历所有树节点，以遍历的当前节点为root，向下找到累加为target的子节点
    public int pathSum(TreeNode root, int targetSum) {
        if (root != null) {
            dfs(root, targetSum);
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
        }
        return globalCnt;
    }

    // 递归找路径和，类似112题思路，只不过这里可以是遍历过程中任意节点，再注意targetSum用long防止int超界
    void dfs(TreeNode node, long targetSum) {
        if (node == null) return;
        // 路径中任意一节点都可以判断是否累加到targetSum
        if (node.val == targetSum) globalCnt++;
        // 持续向左右子树递归
        dfs(node.left, targetSum - node.val);
        dfs(node.right, targetSum - node.val);
    }


    public int pathSum1(TreeNode root, int targetSum) {
        // Key: 前缀和, Value: 出现次数
        Map<Long, Integer> prefixSumMap = new HashMap<>();

        // 【重要基准】：前缀和为 0 默认出现 1 次。
        // 理由：如果当前 currSum 正好等于 targetSum，那么 currSum - targetSum = 0
        // 我们需要从 map 里能查到这个 0。
        prefixSumMap.put(0L, 1);
        dfs1(root, 0L, targetSum, prefixSumMap);
        return globalCnt1;
    }

    // 前缀和+回溯法
    // 若 前缀和2-前缀和1 == targetSum，则说明找得到连续和值为targetSum的这样一条路径
    // 因此可以遍历树，记录遍历到当前点的所有路径和，看是否存在currSum - target这么长的路径（由于记录了所有点到当前的，所以符合的可能会有多个）
    void dfs1(TreeNode node, long currSum, int target, Map<Long, Integer> map) {
        if (node == null) return;
        // 更新当前前缀和
        currSum += node.val;

        // 先查找（看历史记录里有没有能凑成 target 的），如果 currSum 是 18，target 是 8，我们找历史里有没有 10
        int paths = map.getOrDefault(currSum - target, 0);
        globalCnt1 += paths;

        // 后存入，将当前前缀和存入 Map，供子节点使用
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        // 递归左右子树
        dfs1(node.left, currSum, target, map);
        dfs1(node.right, currSum, target, map);

        // 回溯把map中key为当前的前缀和的值-1，相当于删除了这条路径，避免返回父节点遍历另一分支造成污染
        map.put(currSum, map.get(currSum) - 1);
    }


}




