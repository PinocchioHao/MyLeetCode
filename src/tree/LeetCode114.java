package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 114. Flatten Binary Tree to Linked List
 * Medium
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class LeetCode114 {


    public static void main(String[] args) {

        TreeNode root = TreeNodeUtils.initTree();
        flatten1(root);
        System.out.println();

    }


    /**
     * 注意本题必须通过修改每一个node来实现，而不能通过新建树来赋值实现
     * 前序遍历，将节点加入list中，然后遍历list，将左子树填充为null，右子树填充为下一节点
     *
     * Note that in this problem, you must modify each node directly,
     * rather than creating a new tree to store the values.
     * Preorder traversal is used to add nodes to a list, and then
     * the list is used to update the original tree. The left child of
     * each node is set to null, and the right child is set to the next node in the list.
     *
     * @param root 根节点 // root node of the tree
     */
    public static void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        flattenHelper(root, list);

        // 遍历生成的list，将树变成扁平化的单链表
        // Traverse the generated list and flatten the tree into a single linked list
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode node = list.get(i);
            node.left = null;  // 将左子树设置为null // Set the left child to null
            node.right = list.get(i + 1);  // 右子树连接到下一个节点 // Set the right child to the next node
        }
    }

    /**
     * 递归辅助函数，使用前序遍历将节点加入列表中
     * Helper function to perform preorder traversal and add nodes to the list
     *
     * @param root 当前节点 // Current node
     * @param list 节点列表 // List to store nodes
     */
    public static void flattenHelper(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);  // 添加当前节点 // Add the current node
        flattenHelper(root.left, list);  // 遍历左子树 // Traverse the left subtree
        flattenHelper(root.right, list);  // 遍历右子树 // Traverse the right subtree
    }


    /**
     * 栈 - 使用栈来展开二叉树
     * 首先检查根节点是否为空，若不为空则将其压入栈中
     * 通过循环处理栈中的节点，先处理右子树，然后处理左子树
     * 最后将每个节点的左子树设为null，并将右子树指向下一个要处理的节点
     *
     * Stack - Flatten the binary tree using a stack
     * First, check if the root node is null. If it's not, push it onto the stack
     * Use a loop to process the nodes in the stack, handling the right subtree first and then the left subtree
     * Finally, set each node's left child to null, and point the right child to the next node to be processed
     *
     * @param root 二叉树的根节点 / Root of the binary tree
     */
    public static void flatten1(TreeNode root) {
        // 检查根节点是否为空 / Check if the root node is null
        if (root == null) return; // 如果为空直接返回 / Return if it's null

        // 创建一个栈用于存储节点 / Create a stack to store nodes
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 将根节点压入栈中 / Push the root node onto the stack

        // 当栈不为空时，循环处理节点 / While the stack is not empty, process the nodes
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop(); // 弹出栈顶节点 / Pop the top node from the stack

            // 先压右子树，再压左子树，确保左子树先展开 / Push the right child first, then the left child to ensure the left subtree is processed first
            if (cur.right != null) stack.push(cur.right); // 如果右子树不为空，则压入栈中 / If the right subtree is not null, push it onto the stack
            if (cur.left != null) stack.push(cur.left); // 如果左子树不为空，则压入栈中 / If the left subtree is not null, push it onto the stack

            // 当前节点的左子树设为null，右子树指向下一个要处理的节点 / Set the left child of the current node to null, and the right child to the next node to be processed
            cur.left = null; // 左子树置为null / Set the left child to null
            cur.right = stack.isEmpty() ? null : stack.peek(); // 如果栈为空右子树置为null，否则设为栈顶节点 / If the stack is empty, set the right child to null; otherwise, set it to the top node of the stack
        }
    }








}
