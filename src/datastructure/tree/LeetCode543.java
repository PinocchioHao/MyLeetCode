package datastructure.tree;

/**
 * 543. Diameter of Binary Tree
 * Easy
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 */

public class LeetCode543 {


    public static void main(String[] args) {


        Integer [] arr = {1,2};
        TreeNode root = TreeNodeUtils.createBinaryTreeByArray(arr);

        System.out.println(diameterOfBinaryTree(root));

    }

    public static int diameterOfBinaryTree(TreeNode root) {
        // Base case: if the tree is empty, the diameter is 0
        if (root == null) return 0;

        // Call the helper method to calculate the diameter
        return getHeightAndDiameter(root).diameter;
    }

    /**
     * This method calculates the diameter of the tree and returns the current height of the node.
     * Two alternative approaches could be considered:
     * 1. Global variable: This has low overhead but may cause safety concerns (e.g., in multi-threaded environments).
     * 2. Using an int[2] array: This offers better overall performance, though the code readability is somewhat reduced.
     *
     * @param root The root node of the binary tree.
     * @return A Result object containing both the height and the diameter of the tree.
     */
    private static Result getHeightAndDiameter(TreeNode root) {
        // If the node is null, its height and diameter are both 0 (base case)
        if (root == null) {
            return new Result(0, 0);
        }

        // Recursively get the height and diameter of the left subtree
        Result leftResult = getHeightAndDiameter(root.left);

        // Recursively get the height and diameter of the right subtree
        Result rightResult = getHeightAndDiameter(root.right);

        // The height of the current node is the maximum height of the left and right subtrees, plus 1
        int height = Math.max(leftResult.height, rightResult.height) + 1;

        // Calculate the maximum diameter seen so far in the left and right subtrees
        int maxDiameterBefore = Math.max(leftResult.diameter, rightResult.diameter);

        // Calculate the diameter at the current node, which is the height of the left subtree plus the height of the right subtree
        int maxDiameter = Math.max(maxDiameterBefore, leftResult.height + rightResult.height);

        // Return both the height and diameter encapsulated in a Result object
        return new Result(height, maxDiameter);
    }

    /**
     * A helper class to store intermediate results, including the height and the diameter of the tree.
     * This approach incurs higher overhead compared to alternatives like using an int[2] array,
     * but it significantly improves code readability. An optimized approach could use an int[2]
     * array to store specific values (height and diameter) if performance is a critical factor.
     */
    private static class Result {
        int height;
        int diameter;

        // Constructor to initialize the height and diameter values for a node
        Result(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }





}
