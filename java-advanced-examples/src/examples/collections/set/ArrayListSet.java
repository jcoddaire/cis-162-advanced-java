package examples.collections.set;

import java.util.ArrayList;

/**
 * Set implementation using ArrayList
 * 
 * @author Matt
 * 
 */
public class ArrayListSet<T>
{
    private ArrayList<T> set;

    /**
     * Gets the number of elements in this set
     * 
     * @return
     */
    public int getSize()
    {
        return set.size();
    }

    /**
     * Constructor
     */
    public ArrayListSet()
    {
        set = new ArrayList<T>();
    }

    /**
     * Constructor
     */
    public ArrayListSet(int capacity)
    {
        set = new ArrayList<T>(capacity);
    }

    /**
     * Checks whether an item is in this set
     * 
     * @param item
     * @return
     */
    public boolean contains(T item)
    {
        return set.contains(item);
    }

    /**
     * Adds an item to this set
     * 
     * @param item
     * @return
     */
    public boolean add(T item)
    {
        if (set.contains(item))
            return false;

        set.add(item);

        return true;
    }

    /**
     * Removes an item from this set
     * 
     * @param item
     * @return
     */
    public boolean remove(T item)
    {
        return set.remove(item);
    }
}
