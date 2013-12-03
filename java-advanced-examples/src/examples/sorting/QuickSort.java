package examples.sorting;

/**
 * Demonstrates the quick sort algorithm
 * 
 * @author Matt
 * 
 */
public class QuickSort
{
    /**
     * Sorts an array of items using quick sort
     * 
     * @param items
     */
    @SuppressWarnings({ "rawtypes" })
    public static void sort(Comparable[] items)
    {
        // sort all items
        sort(items, 0, items.length - 1);
    }

    /**
     * Recursive sort
     * 
     * @param items
     *        Items to sort
     * @param min
     *        Minimum item in sort range
     * @param max
     *        Maximum item in sort range
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void sort(Comparable[] items, int min, int max)
    {
        // if the range is 1 or smaller, the range is sorted
        if (max <= min)
            return;

        // move pivot item to the last location in array
        Comparable pivotItem = items[min];
        swap(items, min, max);

        // partition items around pivot item
        int pivotIndex = min;
        for (int i = min; i <= max - 1; ++i)
            // if the current item is less than the pivot item, move the former to the pivot item's current location
            if (items[i].compareTo(pivotItem) < 0)
            {
                swap(items, i, pivotIndex);
                ++pivotIndex;
            }

        // insert pivot item into its correct location
        swap(items, max, pivotIndex);

        // sort each subset of items
        sort(items, min, pivotIndex - 1);
        sort(items, pivotIndex + 1, max);
    }

    /**
     * Swaps two items in an array
     * 
     * @param items
     *        Array of item
     * @param index1
     *        First item
     * @param index2
     *        Second item
     */
    @SuppressWarnings("rawtypes")
    private static void swap(Comparable[] items, int index1, int index2)
    {
        Comparable temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }
}
