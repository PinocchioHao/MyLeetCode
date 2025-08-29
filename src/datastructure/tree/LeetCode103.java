package datastructure.tree;

import java.util.*;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Medium
 * 
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 * 
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * 
 * Input: root = []
 * Output: []
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */

public class LeetCode103 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();

        List<List<Integer>> res = zigzagLevelOrder(tree);
        System.out.println(res);

    }


    /**
     * BFS approach, where odd levels are printed in regular order,
     * and even levels are printed in reverse order.
     * @param root
     * @return List of values in zigzag level order
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // Always remember to handle special cases first! Check for null root.
        if (root == null) return result;

        // Level tracker: even levels in normal order, odd levels in reverse order.
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> numsOfEachLevel = new ArrayList<>();
            // Always store queue.size() in a variable before starting the loop,
            // otherwise, the size will change as we manipulate the queue during iteration.
            int levelNodeNum = queue.size();

            // Traverse each level
            for (int i = 0; i < levelNodeNum; i++) {
                TreeNode node = queue.poll();
                numsOfEachLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // Reverse for odd levels
            // The inefficient way would be to manually reverse the array (commented out below)
//        List<Integer> zigzagNums = new ArrayList<>();
//        if (level % 2 == 1) {
//            for (int i = numsOfEachLevel.size()-1; i >=0 ; i--) {
//                zigzagNums.add(numsOfEachLevel.get(i));
//            }
//            result.add(zigzagNums);
//        }else {
//            result.add(numsOfEachLevel);
//        }

            // Optimized way: use the utility function to reverse the list for odd levels.
            if (level % 2 == 1) {
                Collections.reverse(numsOfEachLevel);
            }
            result.add(numsOfEachLevel);
            level++;  // Move to the next level.
        }

        return result;
    }

    /**
     * Optimized version: BFS with Deque (double-ended queue) for zigzag level order.
     *
     * @param root Root node of the binary tree
     * @return List of values in zigzag level order
     */
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // Always remember to handle special cases first! Check for null root.
        if (root == null) return result;

        // Variable to track direction: true for left-to-right, false for right-to-left.
        boolean leftToRight = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Deque allows efficient addition from both ends.
            Deque<Integer> numsOfEachLevel = new LinkedList<>();
            // Always store queue.size() in a variable before starting the loop,
            // otherwise, the size will change as we manipulate the queue during iteration.
            int levelNodeNum = queue.size();

            // Traverse each level
            for (int i = 0; i < levelNodeNum; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    numsOfEachLevel.addLast(node.val);  // Add to the end for left-to-right.
                } else {
                    numsOfEachLevel.addFirst(node.val); // Add to the front for right-to-left.
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // add result
            result.add(new ArrayList<>(numsOfEachLevel));
            // Switch direction for the next level.
            leftToRight = !leftToRight;
        }

        return result;
    }




}