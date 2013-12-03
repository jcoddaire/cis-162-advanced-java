package examples.collections.set;

import examples.collections.singlyLinkedList.List;

/**
 * Simple implementation of a hash-based set
 * 
 * @author gerberma
 * 
 * @param <T>
 */
public class HashSet<T extends Comparable<T>>
{
    private List<T>[] lists;

    /**
     * Gets the average number of items per list in this hash set
     * 
     * @return
     */
    public float getAverageListSize()
    {
        int numItems = 0;
        int numLists = 0;
        for (List<T> list : lists)
            if (list != null)
            {
                numItems += list.size();

                ++numLists;
            }

        return numItems / (float) numLists;
    }

    /**
     * Constructor
     * 
     * @param size
     *            Size of internal list array
     */
    @SuppressWarnings("unchecked")
    public HashSet(int size)
    {
        lists = new List[size];
    }

    /**
     * Checks whether an item is in this set
     * 
     * @param item
     * @return
     */
    public boolean contains(T item)
    {
        // get index of linked list
        int hashCode = item.hashCode();
        int index = Math.abs(hashCode) % lists.length;

        // get list that item would be in
        List<T> list = lists[index];

        // if there is no list, the item is not present
        if (list == null)
            return false;
        else
            // check if item is in list
            return list.contains(item);
    }

    /**
     * Adds an item to this set
     * 
     * @param item
     * @return True if item was added and false if it already existed
     */
    public boolean add(T item)
    {
        // get index of linked list
        int hashCode = item.hashCode();
        int index = Math.abs(hashCode) % lists.length;

        // create new list if needed
        if (lists[index] == null)
            lists[index] = new List<T>();

        // get list and check for item
        List<T> list = lists[index];
        if (list.contains(item))
            // return false if item is already present
            return false;
        else
        {
            // add item and return true if item is not already present
            list.add(item);
            return true;
        }
    }

    /**
     * Removes an item from this set
     * 
     * @param item
     * @return
     */
    public boolean remove(T item)
    {
        // get index of linked list
        int hashCode = item.hashCode();
        int index = Math.abs(hashCode) % lists.length;

        // make sure a list exists...if it doesn't, the item is not in this set
        if (lists[index] == null)
            return false;

        // look through linked list
        return lists[index].remove(item);
    }
}
