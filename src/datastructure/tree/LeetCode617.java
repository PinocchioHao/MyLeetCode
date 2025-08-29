package datastructure.tree;

/**
 617. Merge Two Binary Trees
 Easy

 You are given two binary trees root1 and root2.

 Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

 Return the merged tree.

 Note: The merging process must start from the root nodes of both trees.

 Example 1:


 Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 Output: [3,4,5,5,4,null,7]
 Example 2:

 Input: root1 = [1], root2 = [1,2]
 Output: [2,2]

 Constraints:

 The number of nodes in both trees is in the range [0, 2000].
 -104 <= Node.val <= 104

 */

public class LeetCode617 {


    public static void main(String[] args) {

        Integer [] arr = {3,2,1,6,0,5};
        TreeNode tree1 = TreeNodeUtils.createBinaryTreeByArray(arr);
        TreeNode tree2 = TreeNodeUtils.createBinaryTreeByArray(arr);

        TreeNode root = mergeTrees(tree1, tree2);

        System.out.println();
    }

    /**
     * 递归合并两个数 - 在相同位置的值相加
     * Recursively merge two trees - add values at the same position
     * @param root1
     * @param root2
     * @return
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 当某一方为空时，返回另一方
        // If one side is null, return the other side
        if(root1 == null) return root2;
        if(root2 == null) return root1;
        // 递 - 向下先构造左右孩子节点
        // Recursively construct left and right child nodes
        TreeNode left = mergeTrees(root1.left, root2.left);
        TreeNode right = mergeTrees(root1.right, root2.right);
        // 归 - 构造根节点
        // Construct the root node
        TreeNode rootNode = new TreeNode(root1.val + root2.val);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }


}
