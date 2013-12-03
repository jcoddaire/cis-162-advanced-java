package examples.sorting;

/**
 * Insertion sort algorithm
 * 
 * @author gerberma
 * 
 */
public class InsertionSort
{
    /**
     * Sorts a list of comparable items
     * 
     * @param items
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void sort(Comparable[] items)
    {
        /*
         * the first element constitutes a sorted list (a list with one thing in it is sorted. thus,
         * we need only insert subsequent items into the list. start by inserting the second element...
         */
        for (int position = 1; position < items.length; ++position)
        {
            // get item to insert into the list
            Comparable itemToInsert = items[position];

            // find the position at which the item should be inserted
            int insertAt = position;

            // moving right-to-left in the array, continue to check if the item to insert is less than its left neighbor
            while (insertAt >= 1 && itemToInsert.compareTo(items[insertAt - 1]) < 0)
            {
                // if so, shift the left neighbor to the right to make room for the item to insert, which will go to the left
                items[insertAt] = items[insertAt - 1];

                // move left to next position
                --insertAt;
            }

            // place item at the found location
            items[insertAt] = itemToInsert;
        }
    }
}
