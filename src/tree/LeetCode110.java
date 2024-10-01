package tree;

/**
 * 110. Balanced Binary Tree
 * Easy
 * Given a binary tree, determine if it is height-balanced.
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * <p>
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: root = []
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */

public class LeetCode110 {
    public static void main(String[] args) {

        Integer[] arr = {1, 2, null, 3, null, 4, null, 5};

        TreeNode tree = TreeNodeUtils.createBinaryTreeByArray(arr);

        System.out.println(isBalanced(tree));
        System.out.println(isBalanced1(tree));

    }


    /**
     * Determines if a binary tree is balanced.
     * A tree is balanced if the height difference between the left and right subtrees is no more than 1.
     *
     * @param root The root of the binary tree.
     * @return True if the tree is balanced, false otherwise.
     */
    public static boolean isBalanced(TreeNode root) {
        // If the tree is empty, it's considered balanced.
        if (root == null) return true;

        // Get the heights of the left and right subtrees.
        int leftHeight = getHeightAndCheckIsBalanced(root.left);
        int rightHeight = getHeightAndCheckIsBalanced(root.right);

        // If either subtree is unbalanced (height = -1), the whole tree is unbalanced.
        if (leftHeight == -1 || rightHeight == -1) return false;

        // If the height difference between left and right subtrees is more than 1, the tree is unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) return false;

        // If none of the above conditions were met, the tree is balanced.
        return true;
    }

    /**
     * Computes the height of a subtree and checks if it's balanced at the same time.
     * If the subtree is unbalanced, return -1 to stop further calculations (pruning).
     * This is more efficient than computing height separately and then checking balance.
     *
     * @param node The root node of the subtree.
     * @return The height of the subtree if balanced, -1 if unbalanced.
     */
    private static int getHeightAndCheckIsBalanced(TreeNode node) {
        // Base case: if the node is null, the height is 0.
        if (node == null) return 0;

        // Recursively compute the height of the left subtree.
        int leftHeight = getHeightAndCheckIsBalanced(node.left);
        // Recursively compute the height of the right subtree.
        int rightHeight = getHeightAndCheckIsBalanced(node.right);

        // If the height difference between left and right subtrees is more than 1, return -1 to indicate unbalance.
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // If either subtree is already determined as unbalanced, propagate the -1 upwards.
        if (leftHeight == -1 || rightHeight == -1) return -1;

        // Otherwise, return the current height, which is the maximum of the left and right subtree heights plus 1.
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Another version of checking if a binary tree is balanced.
     * In this version, we compute the heights of left and right subtrees first, then recursively check if both are balanced.
     *
     * @param root The root of the binary tree.
     * @return True if the tree is balanced, false otherwise.
     */
    public static boolean isBalanced1(TreeNode root) {
        // If the tree is empty, it's balanced.
        if (root == null) return true;

        // Get the heights of the left and right subtrees.
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // If the height difference between left and right subtrees is more than 1, the tree is unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) return false;

        // Recursively check if the left and right subtrees are balanced.
        return isBalanced1(root.left) && isBalanced1(root.right);
    }

    /**
     * Computes the height of a subtree.
     * This method is used in the second version of the balanced tree check (isBalanced1),
     * which calculates the heights separately from checking balance.
     *
     * @param node The root node of the subtree.
     * @return The height of the subtree.
     */
    private static int getHeight(TreeNode node) {
        // Base case: if the node is null, the height is 0.
        if (node == null) return 0;

        // Recursively compute the height of the left subtree.
        int leftHeight = getHeight(node.left);
        // Recursively compute the height of the right subtree.
        int rightHeight = getHeight(node.right);

        // Return the current height, which is the maximum of the left and right subtree heights plus 1.
        return Math.max(leftHeight, rightHeight) + 1;
    }


}