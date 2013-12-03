package examples.searching;

/**
 * Simple binary search algorithm
 * 
 * @author Matt
 * 
 */
public class BinarySearch
{
    /**
     * Searches a list of items. Assumes the items are sorted.
     * 
     * @param items
     *        Items to search
     * @param item
     *        Target item
     * @return Index of item in array
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static int Search(Comparable[] items, Comparable item)
    {
        // maintain the range in which we're searching
        int min = 0;
        int max = items.length - 1;

        // search until range is no longer properly defined
        while (min <= max)
        {
            // find middle of range
            int mid = (min + max) / 2;

            // check item
            int comparison = item.compareTo(items[mid]);

            // if we found the target, return its index
            if (comparison == 0)
                return mid;
            // the target is somewhere in the "left" half
            else if (comparison < 0)
                max = mid - 1;
            // the target is somewhere in the "right" half
            else
                min = mid + 1;
        }

        return -1;
    }
}
