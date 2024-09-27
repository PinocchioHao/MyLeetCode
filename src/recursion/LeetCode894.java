package recursion;

import java.util.ArrayList;
import java.util.List;

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
     * 思路：只有奇数才能构成满二叉树，而一个元素为N的满二叉树它的子节点也为两个满二叉树，元素N1和N2满足：N1、N2均为奇数，N1+N2+1 = N，且N1和N2要遍历N以内所有符合该条件的值。
     * 再构造二叉树，如果N=1则构造该节点；若N>1，则分别从左到右，从1、3、5...开始构造。
     * 递归公式：for(i为0~N的奇数) root.left = trees(i)  root.right = trees(n-i-1) ,
     *
     * @param n
     * @return
     */
    public static List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        // 偶数不能构成满二叉树
        if (n % 2 == 0) return res;
        // n为1时可以创建当前节点
        if(n == 1){
            res.add(new TreeNode(0));
            return res;
        }
        // 递归创建节点，左子树取i，则右子树取n-i-i
        for (int i = 1; i < n; i+=2) {
            List<TreeNode> leftNodes = allPossibleFBT(i);
            List<TreeNode> rightNodes = allPossibleFBT(n-i-1);
            // 根据确定的左右子树，生成对应的满二叉树
            for (TreeNode leftNode : leftNodes){
                for (TreeNode rightNode : rightNodes){
                    TreeNode node = new TreeNode(0);
                    node.left = leftNode;
                    node.right = rightNode;
                    // 当前节点构造完毕
                    res.add(node);
                }
            }
        }
        return res;
    }

}
