package examples.collections.singlyLinkedList;

import java.util.Random;

/**
 * Demonstrates a singly-linked list. WARNING: this class does not do error checking on
 * the list length bounds. It is very easy to break!
 * 
 * @author Matt
 * 
 */
public class List<T extends Comparable<T>>
{
    private Node<T> firstNode;

    /**
     * Gets the size of this list
     * 
     * @return
     */
    public int size()
    {
        if (firstNode == null)
            return -1;

        int size = 0;
        Node<T> current = firstNode;
        while (current != null)
        {
            ++size;
            current = current.getNext();
        }

        return size;
    }

    /**
     * Constructor
     */
    public List()
    {
        firstNode = null;
    }

    /**
     * Adds a value to the end of this list
     * 
     * @param value
     */
    public void add(T value)
    {
        // construct the node to add to this list
        Node<T> newNode = new Node<T>(value);

        // check for empty list, in which case the new node should become the first node
        if (firstNode == null)
            firstNode = newNode;
        else
        {
            // find end of list
            Node<T> lastNode = firstNode;
            while (lastNode.getNext() != null)
                lastNode = lastNode.getNext();

            // add new node
            lastNode.setNext(newNode);
        }
    }

    /**
     * Gets a node at a specific index
     * 
     * @param index
     * @return
     */
    public Node<T> getAt(int index)
    {
        Node<T> node = firstNode;

        // move "index" times through the list, watching out for the end
        for (int i = 0; i < index && node != null; ++i)
            node = node.getNext();

        return node;
    }

    /**
     * Inserts a value at a specific index
     * 
     * @param value
     * @param index
     */
    public void insertAt(T value, int index)
    {
        insertAt(new Node<T>(value), index);
    }

    /**
     * Inserts a node at a specific index
     * 
     * @param value
     * @param index
     */
    private void insertAt(Node<T> newNode, int index)
    {
        // check for insert at beginning of list
        if (index == 0)
        {
            // insert node
            newNode.setNext(firstNode);

            // update start to new node
            firstNode = newNode;
        }
        else
        {
            // get the node that comes before the node being inserted
            Node<T> previousNode = getAt(index - 1);

            // attach new node
            newNode.setNext(previousNode.getNext());

            // attach new node
            previousNode.setNext(newNode);
        }
    }

    /**
     * Removes a node at a specific index
     * 
     * @param index
     */
    public void removeAt(int index)
    {
        // remove first item by pointing to next item
        if (index == 0)
        {
            // get the second node
            Node<T> secondNode = firstNode.getNext();

            // first node now points to nothing
            firstNode.setNext(null);

            // the new first node is the old second node
            firstNode = secondNode;
        }
        else
        {
            // get node that precedes the one we want to remove
            Node<T> previousNode = getAt(index - 1);

            // get the node we want to remove
            Node<T> nodeToRemove = previousNode.getNext();

            // cut out node to remove
            previousNode.setNext(nodeToRemove.getNext());

            // the removed node should not point to anything
            nodeToRemove.setNext(null);
        }
    }

    /**
     * Checks whether this list contains an item
     * 
     * @param item
     * @return
     */
    public boolean contains(T item)
    {
        return indexOf(item) != -1;
    }

    /**
     * Gets the index of an item within the list
     * 
     * @param item
     * @return
     */
    public int indexOf(T item)
    {
        int index = 0;
        Node<T> current = firstNode;
        while (current != null && !current.getValue().equals(item))
        {
            current = current.getNext();
            index++;
        }

        if (current == null)
            return -1;
        else
            return index;
    }

    /**
     * Removes all occurrences of an item from this list
     * 
     * @param item
     * @return True if something was removed and false otherwise
     */
    public boolean remove(T item)
    {
        int index;
        boolean removedSomething = false;
        while ((index = indexOf(item)) != -1)
        {
            removeAt(index);
            removedSomething = true;
        }

        return removedSomething;
    }

    /**
     * Gets the string for this list
     */
    public String toString()
    {
        String s = "";
        Node<T> currentNode = firstNode;
        while (currentNode != null)
        {
            s += (!s.equals("") ? "," : "") + currentNode;

            currentNode = currentNode.getNext();
        }

        return s;
    }

    /**
     * Performs selection sort on the current linked list
     */
    public void selectionSort()
    {
        
    }

    /**
     * Swaps two nodes in the current linked list. That is, if the list looks like this:
     * 
     *           (a)               (b)
     * -----    -----    -----    -----
     * | 1 | -- | 3 | -- | 4 | -- | 2 |   
     * -----    -----    -----    -----
     * 
     * and you call swap passing nodes (a) and (b), the list will then look like this:
     * 
     *           (b)               (a)
     * -----    -----    -----    -----
     * | 1 | -- | 2 | -- | 4 | -- | 3 |   
     * -----    -----    -----    -----
     * 
     * @param node1
     * @param node2
     */
    private void swap(Node<T> node1, Node<T> node2)
    {
        // get indexes of the nodes
        int node1Index = indexOf(node1);
        int node2Index = indexOf(node2);

        // swapping the same node for itself does nothing
        if (node1Index == node2Index)
            return;

        // swap the other way if node1 is after node2
        if (node1Index > node2Index)
            swap(node2, node1);
        else
        {
            // remove nodes
            removeAt(node1Index);
            removeAt(node2Index - 1);

            // re-insert in swapped order
            insertAt(node2, node1Index);
            insertAt(node1, node2Index);
        }
    }

    /**
     * Gets index of a node in this linked list
     * 
     * @param node
     * @return
     */
    private int indexOf(Node<T> node)
    {
        int index = 0;
        Node<T> current = firstNode;
        while (current != null && current != node)
        {
            current = current.getNext();
            index++;
        }

        if (current == null)
            return -1;
        else
            return index;
    }

    /**
     * Tests the selectionSort method for List objects
     * 
     * @throws Exception
     */
    public static void testSelectionSort() throws Exception
    {
        // add some items
        Random r = new Random();
        int sorted = 0;
        while (true)
        {
            // get linked list of 100 random numbers in range [0,100] 
            List<Integer> list = new List<Integer>();
            for (int i = 0; i < 100; ++i)
                list.add(r.nextInt(101));

            // sort numbers
            list.selectionSort();

            // make sure list is sorted
            Node<Integer> currNode = list.firstNode;
            while (currNode != null)
            {
                // make sure the next node's value comes after the current node's value, or the two values are equal
                Node<Integer> nextNode = currNode.getNext();
                if (nextNode != null && currNode.getValue().compareTo(nextNode.getValue()) > 0)
                    throw new Exception("List was not sorted correctly");
                
                // move to next node
                currNode = nextNode;
            }

            if ((++sorted % 10000) == 0)
                System.out.println("Sorted " + sorted + " lists successfully");
        }
    }
}
