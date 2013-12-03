package examples.collections.tree;

/**
 * Demonstrates different tree traversals
 * 
 * @author gerberma
 * 
 */
public class Program
{
    public static void main(String[] args)
    {
        /*
         * the layout of the following tree is:
         * 
         *              Top node
         *              /      \
         *             /        \
         *     Left child      Right child
         *      /      \        /       \
         *     GC 1    GC 2   GC 3      GC 4
         */
        BinaryTree<String> tree = new BinaryTree<String>("Top node");

        // construct left child
        BinaryTree<String> leftChild = new BinaryTree<String>("Left child");
        leftChild.setLeftSubtree(new BinaryTree<String>("Grandchild 1"));
        leftChild.setRightSubtree(new BinaryTree<String>("Grandchild 2"));

        tree.setLeftSubtree(leftChild);

        // construct right child
        BinaryTree<String> rightChild = new BinaryTree<String>("Right child");
        rightChild.setLeftSubtree(new BinaryTree<String>("Grandchild 3"));
        rightChild.setRightSubtree(new BinaryTree<String>("Grandchild 4"));

        tree.setRightSubtree(rightChild);

        // traverse tree
        System.out.println("Pre-order");
        tree.traversePreOrder();
        System.out.println();
        
        System.out.println("In-order");
        tree.traverseInOrder();
        System.out.println();
        
        System.out.println("Post-order");
        tree.traversePostOrder();
    }
}
