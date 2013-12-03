package examples.sorting;

/**
 * Bubble sort algorithm
 * 
 * @author gerberma
 * 
 */
public class BubbleSort
{
    /**
     * Sorts an array of items using bubble sort
     * 
     * @param items
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void sort(Comparable[] items)
    {
        // track whether we swap any of the items
        boolean itemsSwapped;
        do
        {
            itemsSwapped = false;

            // check each item against its successor
            for (int i = 0; i < items.length - 1; ++i)
                // if the current item is larger than its successor...swap the two items
                if (items[i].compareTo(items[i + 1]) > 0)
                {
                    Comparable temp = items[i];
                    items[i] = items[i + 1];
                    items[i + 1] = temp;

                    itemsSwapped = true;
                }
        } while (itemsSwapped);
    }
}
