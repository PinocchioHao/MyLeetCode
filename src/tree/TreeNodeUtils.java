package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNodeUtils {
    public static TreeNode initTree() {
        // Creating the root node
        TreeNode root = new TreeNode(1);

        // Creating left and right children of the root
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // Creating children for the left child of the root
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Creating children for the right child of the root
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Now the tree looks like this:
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7

        System.out.println("Binary tree created for testing.");

        return root;
    }

    public static TreeNode createBinaryTreeByArray(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }


    /**
     * when a recursion is executed entirely( no matter which layer of function call),
     * a whole tree( as a root of a subtree in this layer) is already traversed
     * The process of the recursion is similar to a stack
     *
     * 递归 -- 向下的过程 - “递”，处理完嵌套向下后网上层返回 - “归”
     *
     * @param root
     */
    public static void preOrderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        // firstly print root value, then execute recursion
        System.out.print(root.val + " ");
        preOrderTraversalRec(root.left);
        preOrderTraversalRec(root.right);
    }

    public static void midOrderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        // firstly recus left, then print the value
        midOrderTraversalRec(root.left);
        System.out.print(root.val + " ");
        midOrderTraversalRec(root.right);
    }

    public static void postOrderTraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversalRec(root.left);
        postOrderTraversalRec(root.right);
        // only when left and right recursion is executed, print value
        System.out.print(root.val + " ");
    }


    /**
     * 使用栈进行前序遍历 - 中序遍历同模板
     *
     * @param root
     */
    public static void preOrderTraversalIte(TreeNode root) {
        // 栈用于存根节点
        Stack<TreeNode> rootStack = new Stack<>();
        // 遍历游标指向当前节点
        TreeNode curr = root;
        // 当前节点非空，且根节点栈非空时持续遍历
        // 因为遍历到最后一个节点时，此时它的左右子树都为空，并且根节点栈也都pop完了，为空
        while (curr != null || !rootStack.isEmpty()) {
            // 遍历到最底层
            while (curr != null) {
                // 找到结果，输出
                System.out.print(curr.val + " ");
                // 当前根节点入栈，便于后续往上层走
                rootStack.push(curr);
                // 继续往左孩子遍历
                curr = curr.left;
            }
            // curr为空，并且栈非空，说明左孩子遍历完了，往右走
            // 该判断是冗余判断，可移除
            if (!rootStack.isEmpty()) {
                // 每当栈弹出，则这层树左孩子和本身都已经遍历完了，只剩右边还没遍历
                curr = rootStack.pop();
                // 右孩子作为根节点再进行一次遍历
                curr = curr.right;
            }
        }
    }

    /**
     * 使用栈进行二叉树的前序遍历（先访问根节点，再访问左子树，最后访问右子树）
     * This method performs an iterative pre-order traversal of a binary tree using a stack.
     * In pre-order traversal, we visit the root node first, followed by the left subtree, and then the right subtree.
     *
     * 具体步骤如下：
     * 1. 如果当前节点为空，则直接返回，不做任何操作。
     * 2. 使用栈来模拟递归调用，先将根节点压入栈中。
     * 3. 当栈不为空时，取出栈顶节点，访问并打印该节点值。
     * 4. 因为前序遍历是先访问左子树，再访问右子树，为了确保遍历顺序正确，先将右子节点压入栈，再将左子节点压入栈。
     * 5. 重复上述步骤，直到栈为空。
     *
     * Detailed steps:
     * 1. If the current node is null, return immediately without any action.
     * 2. Use a stack to simulate the recursion. First, push the root node onto the stack.
     * 3. While the stack is not empty, pop the top node from the stack, print its value.
     * 4. Since pre-order traversal visits the left subtree before the right subtree, push the right child first and then the left child.
     * This ensures that the left child is processed first when the stack is popped next.
     * 5. Repeat the above steps until the stack is empty.
     *
     * @param root 二叉树的根节点 The root node of the binary tree
     */
    public static void preOrderTraversalIte1(TreeNode root) {
        // 若根节点为空，直接返回
        // If the root is null, return immediately
        if (root == null) {
            return;
        }

        // 栈用于模拟递归调用
        // Stack is used to simulate the recursion
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 先压入根节点便于操作
        stack.push(cur);

        // 当栈不为空时，执行遍历
        // Iterate while the stack is not empty
        while (!stack.isEmpty()) {
            // 取出栈顶节点，并打印其值
            // Pop the top node from the stack and print its value
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            // 因为前序遍历需要先访问左子树再访问右子树，先将右子节点压入栈
            // Push the right child first because we want to visit the left child before the right child
            if (node.right != null) {
                stack.push(node.right);
            }

            // 然后将左子节点压入栈，这样可以确保左子树先被访问
            // Then push the left child so that it is processed next
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    public static void midOrderTraversalIte(TreeNode root) {
        // 栈用于存根节点
        Stack<TreeNode> rootStack = new Stack<>();
        // 遍历游标指向当前节点
        TreeNode curr = root;
        // 当前节点非空，且根节点栈非空时持续遍历
        // 因为遍历到最后一个节点时，此时它的左右子树都为空，并且根节点栈也都pop完了，为空
        while (curr != null || !rootStack.isEmpty()) {
            // 遍历到最底层
            while (curr != null) {
                // 当前根节点入栈，便于后续往上层走
                rootStack.push(curr);
                // 继续往左孩子遍历
                curr = curr.left;
            }
            // curr为空，并且栈非空，说明左孩子遍历完了，往右走
            // 该判断是冗余判断，可移除
            if (!rootStack.isEmpty()) {
                // 每当栈弹出，则这层树左孩子和本身都已经遍历完了，只剩右边还没遍历
                curr = rootStack.pop();
                // 找到结果，输出 -- 与前序不同，中序是在栈弹出的时候找到元素，输出
                System.out.print(curr.val + " ");
                // 右孩子作为根节点再进行一次遍历
                curr = curr.right;
            }
        }
    }

    /**
     * 后序遍历：框架还是跟上面一样，往树的左下角走，但是左边遍历完还需要遍历右边
     * 左->上->右   到当前节点时，要判断右孩子是否存在并且已经被访问过，如果已经输出过右孩子，则可以输出自己
     *
     * 1
     * / \
     * 2   3
     * / \ / \
     * 4  5 6  7
     * 按这个树结构来遍历，相关执行流程为
     *
     * 1. **初始状态**：
     * - `curr = 1`, `rootStack = []`, `lastVisitNode = null`
     * - 当前节点是根节点1，栈为空，`lastVisitNode` 初始化为 `null`。
     *
     * 2. **遍历左子树**：
     * - 入栈：1, 2, 4
     * - 将节点1、2、4依次入栈，直到左子树为空。
     *
     * 3. **处理节点 4**：
     * - 出栈：4，输出：4，`lastVisitNode = 4`
     * - 节点4没有子节点，直接出栈并输出，更新 `lastVisitNode` 为4。
     *
     * 4. **处理节点 2 的右子树**：
     * - 入栈：5
     * - 节点2的右子节点5入栈。
     *
     * 5. **处理节点 5**：
     * - 出栈：5，输出：5，`lastVisitNode = 5`
     * - 节点5没有子节点，直接出栈并输出，更新 `lastVisitNode` 为5。
     *
     * 6. **处理节点 2**：
     * - 出栈：2，输出：2，`lastVisitNode = 2`
     * - 节点2的左右子节点都已访问，出栈并输出，更新 `lastVisitNode` 为2。
     *
     * 7. **处理节点 1 的右子树**：
     * - 入栈：3, 6
     * - 节点1的右子节点3及其左子节点6依次入栈。
     *
     * 8. **处理节点 6**：
     * - 出栈：6，输出：6，`lastVisitNode = 6`
     * - 节点6没有子节点，直接出栈并输出，更新 `lastVisitNode` 为6。
     *
     * 9. **处理节点 3 的右子树**：
     * - 入栈：7
     * - 节点3的右子节点7入栈。
     *
     * 10. **处理节点 7**：
     * - 出栈：7，输出：7，`lastVisitNode = 7`
     * - 节点7没有子节点，直接出栈并输出，更新 `lastVisitNode` 为7。
     *
     * 11. **处理节点 3**：
     * - 出栈：3，输出：3，`lastVisitNode = 3`
     * - 节点3的左右子节点都已访问，出栈并输出，更新 `lastVisitNode` 为3。
     *
     * 12. **处理节点 1**：
     * - 出栈：1，输出：1，`lastVisitNode = 1`
     * - 节点1的左右子节点都已访问，出栈并输出，更新 `lastVisitNode` 为1。
     *
     * @param root
     */
    public static void postOrderTraversalIte(TreeNode root) {
        // 栈用于存根节点
        Stack<TreeNode> rootStack = new Stack<>();
        // 遍历游标指向当前节点
        TreeNode curr = root;
        // 记录最后遍历的右节点
        TreeNode lastVisitNode = root;

        // 当前节点非空，且根节点栈非空时持续遍历
        // 因为遍历到最后一个节点时，此时它的左右子树都为空，并且根节点栈也都pop完了，为空
        while (curr != null || !rootStack.isEmpty()) {
            // 遍历到最底层，沿左子树方向根节点入栈
            while (curr != null) {
                // 当前根节点入栈，便于后续往上层走
                rootStack.push(curr);
                // 继续往左孩子遍历
                curr = curr.left;
            }

            // 以下代码处理树的右边部分
            // 根据当前递归顺序，一定是先到左孩子最根处，然后pop出上一层根节点，然后再往后孩子判断
            // 判断当前节点是否可以输出的逻辑：左右孩子都为空，那么为叶子节点，可以输出；移到上一层根节点时，若右孩子已被访问输出，那么就可以输出当前节点

            // 查看当前节点，curr被指向最后进栈的根节点
            curr = rootStack.peek();
            // 如果右孩子存在，并且未被访问过，则往右走，继续入栈
            if (curr.right != null && curr.right != lastVisitNode) {
                curr = curr.right;
            }
            // 否则，右子树为空或者被访问过（此处是关键，只有当前节点右子树被访问过或为空才能输出当前节点），则输出当前节点（因为代码模板保证了左子树部分先被访问输出了）
            else {
                System.out.print(curr.val + " ");
                lastVisitNode = curr;
                // 游标置空，防止重复访问左子树，如果不置为null，下一次循环它又会被push进栈！（注意这个模板遍历树的前提就是到此处，当前遍历节点是null，上面的curr为栈顶元素只是方便用作中间变量进行特殊处理）
                curr = null;
                // 当前节点出栈，进入上一层根节点
                rootStack.pop();
            }


            // 如果当前节点左右孩子都为空，或者是左孩子为空，右孩子已访问，则可以输出当前节点，栈弹出当前节点
//            if (curr.right == null || curr.right == lastVisitNode) {
//                System.out.println(curr.val);
//                lastVisitNode = curr;
//                // 游标置空，方便下一次访问
//                curr = null;
//                rootStack.pop();
//            }
//            // 否则继续向右遍历
//            else {
//                curr = curr.right;
//            }

        }
    }

    /**
     * 将根节点入队列，入队的时候就输出，然后再将左右孩子入队，这样就达到了根节点输出并且孩子从左到右输出值。
     *
     * @param root
     */
    public static void bfsTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 先加入根节点
        queue.add(root);
        // 当队列非空时进行遍历，最终遍历完队列会空
        while (!queue.isEmpty()) {
            // 获取当前层的节点数量并遍历当前层的所有节点
            // 不用获取当前层节点数去掉这段for循环也可以实现bfs
            // 但是考虑到某些特定场景可能需要对当前层数据进行记录或特点操作，所以遍历当前层节点更为稳妥
            int levelNodeNum = queue.size();
            for (int i = 0; i < levelNodeNum; i++) {
                // poll是delete的高级用法，避免了null情况
                TreeNode node = queue.poll();
                // 取出时立马输出
                System.out.print(node.val + " ");
                // 队列先进先出，所以先左再右，入栈后就输出
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }


}
