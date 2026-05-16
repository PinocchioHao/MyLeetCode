package AceCodingInterview75Qs.binary_search_tree;

/*
 *
 *
 *
450. Delete Node in a BST
Medium

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.


Example 1:

Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:

Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
Example 3:

Input: root = [], key = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105


Follow up: Could you solve it with time complexity O(height of tree)?
 *
 *
 */

import datastructure.tree.TreeNode;

public class LeetCode450 {


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(3);
        tree.right = new TreeNode(6);
        tree.left.left = new TreeNode(2);
        tree.left.right = new TreeNode(4);
        tree.right.right = new TreeNode(7);


        LeetCode450 example = new LeetCode450();

        TreeNode node = example.findMin(tree);

        System.out.println(example.deleteNode(tree, 3));


    }

    // BFS删除节点。删除一个节点后BST会动态平衡，找右子树最小（或左子树最大）元素来补位，然后剩下的那一边进行动态平衡调整
    // 需要处理子树之间的关系，所以需要带返回值的dfs，注意父子节点的连接关系。
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base Case: 没找到要删除的节点，直接返回 null
        if (root == null) return null;
        // 第一阶段：寻找目标节点
        if (key < root.val) {
            // 目标在左边，去左子树删，删完把新左子树接回来
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // 目标在右边，去右子树删，删完把新右子树接回来
            root.right = deleteNode(root.right, key);
        } else {
            // 第二阶段：找到了目标节点 (root.val == key)，开始执行删除

            // 情况 1 & 2：左孩子为空，或者左右都为空
            // 直接把右孩子返回给父节点（如果是叶子，root.right 就是 null，刚好符合）
            if (root.left == null) return root.right;
            // 情况 2：右孩子为空
            // 直接把左孩子返回给父节点
            if (root.right == null) return root.left;
            // 情况 3：左右孩子都有
            // 找到右子树的最小值（后继节点）来顶替自己
            TreeNode minNode = findMin(root.right);
            // 替换数值
            root.val = minNode.val;
            // 完美的闭环：去右子树里，把那个刚刚用来顶替的替换节点给删掉
            root.right = deleteNode(root.right, minNode.val);
        }
        // 回溯返回整理好之后的当前节点给上一层
        return root;
    }

    // 找右边最小元素的工具函数
    public TreeNode findMin(TreeNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }


}