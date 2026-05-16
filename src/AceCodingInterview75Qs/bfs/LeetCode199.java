package AceCodingInterview75Qs.bfs;

/*
 *
 *
 *

199. Binary Tree Right Side View

Medium

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:

Input: root = [1,2,3,null,5,null,4]

Output: [1,3,4]

Explanation:

Example 2:

Input: root = [1,2,3,4,null,null,null,5]

Output: [1,3,4,5]

Explanation:



Example 3:

Input: root = [1,null,3]

Output: [1,3]

Example 4:

Input: root = []

Output: []



Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

 *
 *
 */

import datastructure.tree.TreeNode;
import datastructure.tree.TreeNodeUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode199 {



    public static void main(String[] args) {
        TreeNode tree = TreeNodeUtils.initTree();

        LeetCode199 example = new LeetCode199();
        System.out.println(example.rightSideView(tree));
    }

    // BFS。记录每层个数，遍历到最右边元素则记录结果。
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        // 根节点先入队方便操作
        deque.addLast(root);
        while(!deque.isEmpty()){
            // 注意控制行数必须把这个size固定在外层，因为deque的size是浮动的
            int size = deque.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = deque.removeFirst();
                if (i == size - 1) res.add(cur.val);
                if (cur.left != null) deque.addLast(cur.left);
                if (cur.right!= null) deque.addLast(cur.right);
            }
        }
        return res;
    }



}