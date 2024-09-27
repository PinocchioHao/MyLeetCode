package recursion;

public class TreeNodeTest {
    public static void main(String[] args) {

        // Now the tree looks like this:
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        TreeNode tree = TreeNodeUtils.initTree();

//        System.out.println("----preOrder----");
//        TreeNodeUtils.preOrderTraversalRec(tree);
//
//        System.out.println("----preOrder----");
//        TreeNodeUtils.preOrderTraversalIte(tree);

//        System.out.println("----midOrder----");
//        TreeNodeUtils.midOrderTraversalRec(tree);
//
//        System.out.println("----midOrder----");
//        TreeNodeUtils.midOrderTraversalIte(tree);

//        System.out.println("----postOrder----");
//        TreeNodeUtils.postOrderTraversalRec(tree);
//
//        System.out.println("----postOrder----");
//        TreeNodeUtils.postOrderTraversalIte(tree);

        System.out.println("----bfs----");
        TreeNodeUtils.bfsTraversal(tree);



    }

}
