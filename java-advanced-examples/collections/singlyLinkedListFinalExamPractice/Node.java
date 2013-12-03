package examples.collections.singlyLinkedListFinalExamPractice;

/**
 * Represents a node in a linked list
 * 
 * @author Matt
 * 
 */
public class Node<T extends Comparable<T>>
{
    private T value;
    private Node<T> next;

    /**
     * Gets the value of this node
     * 
     * @return
     */
    public T getValue()
    {
        return value;
    }

    /**
     * Gets the node that follows the current one
     * 
     * @return
     */
    public Node<T> getNext()
    {
        return next;
    }

    /**
     * Sets the node that follows the current one
     * 
     * @param next
     */
    public void setNext(Node<T> next)
    {
        this.next = next;
    }

    /**
     * Constructor
     * 
     * @param value
     */
    public Node(T value)
    {
        this.value = value;
        next = null;
    }

    /**
     * Gets the value of the current node
     */
    public String toString()
    {
        return value.toString();
    }

    /**
     * Checks whether this node equals another node
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o)
    {
        if (!(o instanceof Node<?>))
            return false;

        Node<T> node = (Node<T>) o;

        return value.equals(node.value);
    }
}
