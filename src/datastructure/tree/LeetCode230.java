package datastructure.tree;

/*
 * 
 * 
 *
230. Kth Smallest Element in a BST
Medium

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.


Example 1:

Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 * 
 * 
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode230 {


    public static void main(String[] args) {
        Integer[] arr = {3, 1, 4, null, 2};
        TreeNode tree = TreeNodeUtils.createBinaryTreeByArray(arr);


        System.out.println(kthSmallest(tree, 1));
    }


	/**
	 * In-order traversal of a binary search tree (BST) to get elements in ascending order.
	 * The k-th smallest element is the (k-1)-th element in the zero-indexed list.
	 *
	 * @param root The root node of the BST.
	 * @param k The position (1-based) of the smallest element to find.
	 * @return The k-th smallest element in the BST.
	 */
	public static int kthSmallest(TreeNode root, int k) {
		List<Integer> list = new ArrayList<>();
		midTraversal(root, list);
		// The problem guarantees that k is always less than or equal to the size of the list.
		return list.get(k - 1);
	}

	/**
	 * Helper method to perform in-order traversal of the BST.
	 * The elements are added to the list in ascending order.
	 *
	 * @param root The current node in the BST.
	 * @param list The list to store the elements in ascending order.
	 */
	private static void midTraversal(TreeNode root, List<Integer> list) {
		if (root == null) return;
		midTraversal(root.left, list); // Traverse the left subtree
		list.add(root.val); // Visit the root
		midTraversal(root.right, list); // Traverse the right subtree
	}


}
