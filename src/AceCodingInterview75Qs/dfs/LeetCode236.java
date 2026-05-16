package AceCodingInterview75Qs.dfs;

/*
 *
 *
 *
236. Lowest Common Ancestor of a Binary Tree
Medium

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.HashSet;
import java.util.Set;

public class LeetCode236 {

    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
        tree2.left = new TreeNode(3);
        LeetCode236 example = new LeetCode236();

//        System.out.println(example.lowestCommonAncestor(tree));
    }


    // dfs回溯。递归在递的过程中寻找p和q，找到一方则立马返回，回溯时已经进行完寻找过程，判断自己的左右孩子是否找到元素即可。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 向下寻找子节点，寻找到立马返回该节点，返回的是找到的最上层的这个节点
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // dfs遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 回溯过程，此时左右节点已遍历完，当前是作为左右节点的父亲来判断。
        // 若p,q位于子树左右异侧，则当前节点是它们的祖先
        if (left != null && right != null) {
            return root;
        }
        // p,q位于子树同侧，此时找到了一个节点就不会往下找了，另一侧就会返回null
        return left != null ? left : right;
    }

    // 另外也可以使用HashMap记录父亲节点来实现，类似两条链表找交叉，这样需要额外空间开销。
}