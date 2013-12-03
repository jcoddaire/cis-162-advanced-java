package examples.collections.singlyLinkedListFinalExamPractice;

import java.util.Random;

import Question1.Node;

/**
 * For practice for the final, implement the selection sort using this class. This class is slightly 
 * different from the one used in Homework 3. This class does not contain the getAt method. Without
 * this method, you are forced to find a different way to traverse the linked list (a standard
 * for-loop will not work).
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
            Node<T> previousNode = firstNode;

            // move "index" times through the list, watching out for the end
            for (int i = 0; i < index - 1; ++i)
                previousNode = previousNode.getNext();

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
            Node<T> previousNode = firstNode;

            // move "index" times through the list, watching out for the end
            for (int i = 0; i < index - 1; ++i)
                previousNode = previousNode.getNext();

            // get the node we want to remove
            Node<T> nodeToRemove = previousNode.getNext();

            // cut out node to remove
            previousNode.setNext(nodeToRemove.getNext());

            // the removed node should not point to anything
            nodeToRemove.setNext(null);
        }
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
     * Performs selection sort on the current linked list
     */
    public void selectionSort()
    {
	    // find smallest location between the current position and the end
        Node<T> position = firstNode;
       // System.out.println("First node assigned.");
        while (position.getNext() != null)
        {
        //	System.out.println("Entered FIRST while");
        	 Node<T> smallest = position;
        	 Node<T> cantidate = smallest.getNext();
             
        	// System.out.println("Entering Second While");
             while (cantidate != null)
             {
            	// System.out.println("Comparing");
            	// update location of smallest item if we found a smaller one
                 if (cantidate.getValue().compareTo(smallest.getValue()) < 0)
                 {
              	   smallest = cantidate;
              	// System.out.println("Swap successful");
                 }
                 cantidate = cantidate.getNext();
             }
             
             swap(position,smallest);
             position = smallest.getNext();
           //  System.out.println("Position incremented.");
        
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
