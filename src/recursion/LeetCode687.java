package recursion;

/**
 687. Longest Univalue Path
 Medium

 Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.

 The length of the path between two nodes is represented by the number of edges between them.

 Example 1:

 Input: root = [5,4,5,1,1,null,5]
 Output: 2
 Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 Example 2:


 Input: root = [1,4,5,4,4,null,5]
 Output: 2
 Explanation: The shown image shows that the longest path of the same value (i.e. 4).


 Constraints:

 The number of nodes in the tree is in the range [0, 104].
 -1000 <= Node.val <= 1000
 The depth of the tree will not exceed 1000.
 */

public class LeetCode687 {


    static int maxPath;

    public static void main(String[] args) {

        Integer [] arr = {1,4,5,4,4,null,5};
        TreeNode root = TreeNodeUtils.createBinaryTreeByArray(arr);


        System.out.println(longestUnivaluePath(root));

    }


    public static int longestUnivaluePath(TreeNode root) {
        // If the tree is empty, the longest univalue path is 0
        if (root == null) return 0;

        // Helper method to calculate the longest univalue path
        findMaxsSnglePathHelper(root);

        // Return the maximum single side path length found during the traversal
        return maxPath;
    }

    /**
     * This method returns the maximum   single side   univalue path for the current node.
     * It calculates the univalue path for both left and right subtrees and updates the global
     * maximum path that connects through the current node.
     *
     * @param root The current node of the binary tree.
     * @return The length of the longest single side univalue path for the current node.
     */
    public static int findMaxsSnglePathHelper(TreeNode root) {
        // Base case: If the node is null, the univalue path is 0
        if (root == null) return 0;

        // First, calculate the longest univalue paths for both left and right subtrees.
        // Note: We calculate this before checking the conditions so that maxPath is updated
        // for every node, avoiding cases where it might be skipped if conditions are placed within 'if' statements.
        int left = findMaxsSnglePathHelper(root.left);
        int right = findMaxsSnglePathHelper(root.right);

        // Initialize left and right connection lengths to 0.
        // These will store the lengths of the longest univalue paths connecting the current node
        // to its left or right child if they have the same value.
        int leftLink = 0;
        int rightLink = 0;

        // Check if the left child has the same value as the current node.
        // If true, the leftLink extends the left subtree's univalue path by 1.
        if (root.left != null && root.val == root.left.val) {
            leftLink = left + 1;
        }

        // Check if the right child has the same value as the current node.
        // If true, the rightLink extends the right subtree's univalue path by 1.
        if (root.right != null && root.val == root.right.val) {
            rightLink = right + 1;
        }

        // Update the global maximum path by considering the combined path through the current node.
        maxPath = Math.max(maxPath, leftLink + rightLink);

        // Return the maximum single side univalue path (either left or right) for the current node.
        return Math.max(leftLink, rightLink);
    }





}
