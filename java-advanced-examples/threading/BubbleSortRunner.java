package examples.threading;

import examples.sorting.BubbleSort;

/**
 * Represents a bubble sort thread - awfully slow!
 * 
 * @author gerberma
 * 
 */
public class BubbleSortRunner implements Runnable
{
    private Comparable[] _items;

    /**
     * Constructor
     * 
     * @param items
     */
    public BubbleSortRunner(Comparable[] items)
    {
        _items = items;
    }

    /**
     * Sorts the given items using bubble sort
     */
    @Override
    public void run()
    {
        BubbleSort.sort(_items);
    }
}
