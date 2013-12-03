package examples.searching;

/**
 * Simple linear searching
 * 
 * @author Matt
 * 
 */
public class LinearSearch
{
    /**
     * Static search method
     * 
     * @param items
     *        Items to search
     * @param item
     *        Target item
     * @return Index of target in items
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static int Search(Comparable[] items, Comparable item)
    {
        // search item by item for target
        for (int i = 0; i < items.length; ++i)
            if (items[i].compareTo(item) == 0)
                return i;

        return -1;
    }
}
