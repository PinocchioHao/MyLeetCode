package tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 894. All Possible Full Binary Trees
 * Medium
 *
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Example 1:
 *
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Example 2:
 *
 * Input: n = 3
 * Output: [[0,0,0]]
 *
 * Constraints:
 *
 * 1 <= n <= 20
 */
public class LeetCode894 {

    public static void main(String[] args) {
        List<TreeNode> all = allPossibleFBT(5);

        System.out.println(all.size());
    }

    /**
     * 这道题是一道比较难的迭代+递归组合解法的题，迭代遍历所有满二叉树的可能情况，递归构造满二叉树
     * 思路：只有奇数才能构成满二叉树，而一个元素为N的满二叉树它的子节点也为两个满二叉树，元素N1和N2满足：N1、N2均为奇数，N1+N2+1 = N，且N1和N2要遍历N以内所有符合该条件的值。
     * 再构造二叉树，如果N=1则构造该节点；若N>1，则分别从左到右，从1、3、5...开始构造。
     * 递归公式：for(i为0~N的奇数) root.left = trees(i)  root.right = trees(n-i-1),
     *
     * This problem is a relatively difficult combination of iteration and recursion. We iterate over all possible full binary tree configurations and recursively construct each tree.
     * The idea is that only odd numbers can form a full binary tree, and for a full binary tree with N nodes, its two subtrees are also full binary trees. N1 and N2 satisfy the following: both N1 and N2 are odd, and N1 + N2 + 1 = N. We need to iterate through all such valid combinations for N1 and N2.
     * Then, construct the binary tree. If N = 1, we construct this node; if N > 1, we start from the left, constructing from 1, 3, 5, ..., and so on.
     * Recursive formula: for (i is odd from 0 to N) root.left = trees(i), root.right = trees(n - i - 1).
     *
     * @param n
     * @return
     */
    public static List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        // 偶数不能构成满二叉树
        // Even numbers cannot form a full binary tree.
        if (n % 2 == 0) return res;
        // 递归终止条件：n为1时可以创建当前节点
        // Recursive termination condition: when n equals 1, create the current node.
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        // 遍历左右子树可能得所有情况，左子树取i，则右子树取n-i-1
        // Iterate through all possible configurations of left and right subtrees. For the left subtree, take i, and for the right subtree, take n - i - 1.
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> leftNodes = allPossibleFBT(i);
            List<TreeNode> rightNodes = allPossibleFBT(n - i - 1);
            // 根据确定的左右子树，生成对应的满二叉树
            // Based on the determined left and right subtrees, generate the corresponding full binary tree.
            // 注意这里两层for循环是遍历所有得可能，例如左子树有x种情况，右子树有y种情况，那么总共就有x*y种情况
            // Note that there are two nested for loops here to iterate through all possibilities. For example, if the left subtree has x configurations and the right subtree has y configurations, then there are x * y total configurations.
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode node = new TreeNode(0);
                    node.left = leftNode;
                    node.right = rightNode;
                    // 当前节点构造完毕，将当前的这种组合方式加入结果集
                    // Once the current node is constructed, add this combination to the result set.
                    res.add(node);
                }
            }
        }
        return res;
    }

    /**
     * 使用Memoization（记忆化优化）进行优化：通过HashMap记录已经算出的值，避免递归重复计算，极大降低开销
     * This version uses Memoization (optimization through caching) to reduce redundant calculations by storing already computed results in a HashMap, which greatly reduces overhead.
     *
     * @param n
     * @return
     */
    public static List<TreeNode> allPossibleFBT1(int n) {
        // 记录已经求出的结果，防止递归重复计算
        // Cache already computed results to avoid redundant recursive calculations.
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        return allPossibleFBTHelper(n, map);
    }

    private static List<TreeNode> allPossibleFBTHelper(int n, Map<Integer, List<TreeNode>> map) {
        List<TreeNode> res = new ArrayList<>();
        // 偶数不能构成满二叉树
        // Even numbers cannot form a full binary tree.
        if (n % 2 == 0) return res;
        // map中没有记录n的所有满二叉树，求n的所有满二叉树并记录
        // If n's full binary trees are not cached in the map, compute and cache them.
        if (!map.containsKey(n)) {
            // 递归终止条件：n为1时可以创建当前节点
            // Recursive termination condition: if n equals 1, create the current node.
            if (n == 1) {
                res.add(new TreeNode(0));
            }
            // 否则则遍历所有情况，并递归创建子树，左:1,3,5,7...n   右:n...7,5,3,1，每一种都是不同的情况，需要组合所有情况
            // Otherwise, iterate through all possible configurations and recursively construct subtrees. Left subtree: 1, 3, 5, 7... Right subtree: n, ..., 7, 5, 3, 1. Each combination is unique, and all possibilities need to be combined.
            else {
                // 遍历左右子树可能得所有情况，左子树取i，则右子树取n-i-1
                // Iterate through all possible configurations of left and right subtrees. For the left subtree, take i, and for the right subtree, take n - i - 1.
                for (int i = 1; i < n; i += 2) {
                    List<TreeNode> leftNodes = allPossibleFBTHelper(i, map);
                    List<TreeNode> rightNodes = allPossibleFBTHelper(n - i - 1, map);
                    // 根据确定的左右子树，生成对应的满二叉树
                    // Based on the determined left and right subtrees, generate the corresponding full binary tree.
                    // 注意这里两层for循环是遍历所有得可能，例如左子树有x种情况，右子树有y种情况，那么总共就有x*y种情况
                    // Note that there are two nested for loops here to iterate through all possibilities. For example, if the left subtree has x configurations and the right subtree has y configurations, then there are x * y total configurations.
                    for (TreeNode leftNode : leftNodes) {
                        for (TreeNode rightNode : rightNodes) {
                            TreeNode node = new TreeNode(0);
                            node.left = leftNode;
                            node.right = rightNode;
                            // 当前节点构造完毕，将当前的这种组合方式加入结果集
                            // Once the current node is constructed, add this combination to the result set.
                            res.add(node);
                        }
                    }
                }
            }
            // 执行完遍历递归所有操作后，才能得到n的所有满二叉树的情况
            // After iterating and completing all recursive operations, cache the full binary trees for n.
            map.put(n, res);
        }
        // 返回map中记录的n的满二叉树
        // Return the cached full binary trees for n.
        return map.get(n);
    }

}
