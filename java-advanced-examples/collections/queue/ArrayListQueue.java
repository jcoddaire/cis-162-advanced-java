package examples.collections.queue;

import java.util.ArrayList;

/**
 * Queue implemented using ArrayList
 * 
 * @author Matt
 * 
 */
public class ArrayListQueue
{
    private ArrayList queue;

    /**
     * Gets the number of elements in the queue
     * 
     * @return
     */
    public int getSize()
    {
        return queue.size();
    }

    /**
     * Constructor
     */
    public ArrayListQueue()
    {
        queue = new ArrayList();
    }

    /**
     * Enqueue an item
     * 
     * @param item
     */
    public void enqueue(Object item)
    {
        queue.add(item);
    }

    /**
     * Dequeues an item. Returns null if queue is empty.
     * 
     * @return
     */
    public Object dequeue()
    {
        if (queue.size() == 0)
            return null;

        return queue.remove(0);
    }

    /**
     * Peeks at next item without removing it. Returns null if queue is empty.
     * 
     * @return
     */
    public Object peek()
    {
        if (queue.size() == 0)
            return null;

        return queue.get(0);
    }
}
