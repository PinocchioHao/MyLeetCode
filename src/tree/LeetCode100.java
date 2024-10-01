package tree;

import java.util.*;

/**
 * 100. Same Tree
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */

public class LeetCode100 {
    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();
        TreeNode tree2 = TreeNodeUtils.initTree();
//        tree2.left = new TreeNode(3);

        System.out.println(isSameTree(tree, tree2));
        System.out.println(isSameTree1(tree, tree2));
        System.out.println(isSameTree2(tree, tree2));
        System.out.println(isSameTree3(tree, tree2));
    }


    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // all null -> true
        if (p == null && q == null) return true;
        // one is null, one is not null -> false
        if (p == null || q == null) return false;
        // value not equal
        if (p.val != q.val) return false;
        // all not null, compare left to left, right to right, both must be equal
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * recursively traverse the two trees, add the values to two lists, then compare the lists
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        List<Integer> listP = new ArrayList<>();
        List<Integer> listQ = new ArrayList<>();
        traverse(p, listP);
        traverse(q, listQ);
        return listP.equals(listQ);
    }

    private static void traverse(TreeNode node, List<Integer> list) {
        if (node == null) {
            // null nodes need to be added too
            list.add(null);
            return;
        }
        list.add(node.val);
        traverse(node.left, list);
        traverse(node.right, list);

    }


    /**
     * Preorder Traversal with Iteration
     * This method uses a stack to perform an iterative preorder traversal. It compares nodes as it traverses the trees.
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        // Initialize two stacks for iterative traversal of both trees
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        stackP.push(p);
        stackQ.push(q);

        // Continue until both stacks are empty
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();

            // If both nodes are null, continue to the next iteration
            if (nodeP == null && nodeQ == null) {
                continue;
            }
            // If one node is null or values are different, trees are not the same
            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }

            // Push right and left children of both nodes to their respective stacks
            stackP.push(nodeP.right);
            stackP.push(nodeP.left);
            stackQ.push(nodeQ.right);
            stackQ.push(nodeQ.left);
        }

        // If both stacks are empty, trees are the same
        return stackP.isEmpty() && stackQ.isEmpty();
    }


    /**
     * Level Order Traversal with Queue
     * This method uses a queue to perform a level order traversal (breadth-first search). It compares nodes level by level.
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree3(TreeNode p, TreeNode q) {
        // Initialize two queues for level order traversal of both trees
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.add(p);
        queueQ.add(q);

        // Continue until both queues are empty
        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();

            // If both nodes are null, continue to the next iteration
            if (nodeP == null && nodeQ == null) {
                continue;
            }
            // If one node is null or values are different, trees are not the same
            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }

            // Add left and right children of both nodes to their respective queues
            queueP.add(nodeP.left);
            queueP.add(nodeP.right);
            queueQ.add(nodeQ.left);
            queueQ.add(nodeQ.right);
        }

        // If both queues are empty, trees are the same
        return queueP.isEmpty() && queueQ.isEmpty();
    }



}
