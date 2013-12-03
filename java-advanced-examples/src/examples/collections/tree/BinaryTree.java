package examples.collections.tree;

/**
 * Simple binary tree
 * 
 * @author gerberma
 * 
 * @param <T>
 */
public class BinaryTree<T>
{
    private T value;
    private BinaryTree<T> leftSubtree;
    private BinaryTree<T> rightSubtree;

    /**
     * Sets the left subtree
     * 
     * @param tree
     */
    public void setLeftSubtree(BinaryTree<T> tree)
    {
        leftSubtree = tree;
    }

    /**
     * Sets the right subtree
     * 
     * @param tree
     */
    public void setRightSubtree(BinaryTree<T> tree)
    {
        rightSubtree = tree;
    }

    /**
     * Constructor
     * 
     * @param value
     */
    public BinaryTree(T value)
    {
        this.value = value;
    }

    /**
     * In-order traversal
     */
    public void traverseInOrder()
    {
        if (leftSubtree != null)
            leftSubtree.traverseInOrder();

        System.out.println(value);

        if (rightSubtree != null)
            rightSubtree.traverseInOrder();
    }

    /**
     * Pre-order traversal
     */
    public void traversePreOrder()
    {
        System.out.println(value);

        if (leftSubtree != null)
            leftSubtree.traversePreOrder();

        if (rightSubtree != null)
            rightSubtree.traversePreOrder();
    }

    /**
     * Post-order traversal
     */
    public void traversePostOrder()
    {
        if (leftSubtree != null)
            leftSubtree.traversePostOrder();

        if (rightSubtree != null)
            rightSubtree.traversePostOrder();

        System.out.println(value);
    }
}
