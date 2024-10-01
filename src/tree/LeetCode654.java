package tree;

/**
 654. Maximum Binary Tree
 Medium

 You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

 Create a root node whose value is the maximum value in nums.
 Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 Return the maximum binary tree built from nums.



 Example 1:


 Input: nums = [3,2,1,6,0,5]
 Output: [6,3,5,null,2,0,null,null,1]
 Explanation: The recursive calls are as follow:
 - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 - Empty array, so no child.
 - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 - Empty array, so no child.
 - Only one element, so child is a node with value 1.
 - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 - Only one element, so child is a node with value 0.
 - Empty array, so no child.
 Example 2:


 Input: nums = [3,2,1]
 Output: [3,null,2,null,1]


 Constraints:

 1 <= nums.length <= 1000
 0 <= nums[i] <= 1000
 All integers in nums are unique.
 */

public class LeetCode654 {


    public static void main(String[] args) {

        int [] arr = {3,2,1,6,0,5};
        TreeNode root = constructMaximumBinaryTree(arr);

        System.out.println();
    }



    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        // If the input array is null or empty, return null as the tree can't be constructed
        if (nums == null || nums.length == 0) return null;

        // Recursively construct the binary tree using the helper function
        return constructHelper(nums, 0, nums.length - 1);
    }

    /**
     * Recursively constructs the subtree using the given subarray defined by the start and end indices.
     *
     * @param nums  The input array from which the tree is constructed.
     * @param start The starting index of the subarray.
     * @param end   The ending index of the subarray.
     * @return      The root node of the constructed subtree.
     */
    private static TreeNode constructHelper(int[] nums, int start, int end) {
        // Base case: If the start index is greater than the end index, return null (no subtree to construct)
        if (start > end) return null;

        // Find the index of the maximum value in the current subarray, and construct the root node with that value
        int maxIdx = findMaxIdx(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIdx]);

        // Recursively construct the left and right subtrees using the subarrays to the left and right of the max value
        root.left = constructHelper(nums, start, maxIdx - 1);
        root.right = constructHelper(nums, maxIdx + 1, end);

        // Return the root of the constructed subtree
        return root;
    }

    /**
     * Finds the index of the maximum value in the subarray defined by the start and end indices.
     *
     * @param nums  The input array.
     * @param start The starting index of the subarray.
     * @param end   The ending index of the subarray.
     * @return      The index of the maximum value in the subarray.
     */
    private static int findMaxIdx(int[] nums, int start, int end) {
        // Initialize variables to track the index and value of the maximum element
        int idx = -1;
        int max = Integer.MIN_VALUE;

        // Traverse the subarray to find the maximum value and its index
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                idx = i;
                max = nums[i];
            }
        }

        // Return the index of the maximum value
        return idx;
    }

}
