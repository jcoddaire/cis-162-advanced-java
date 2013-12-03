package examples.sorting;

/**
 * Selection sort algorithm
 * 
 * @author Matt
 * 
 */
public class SelectionSort
{
    /**
     * Sorts a list of comparable items
     * 
     * @param items
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void sort(Comparable[] items)
    {
        // find correct number for each position
        for (int position = 0; position < items.length - 1; ++position)
        {
            // find smallest location between the current position and the end
            int locationOfSmallest = position;
            for (int candidateLocation = position + 1; candidateLocation < items.length; ++candidateLocation)
            {
                // update location of smallest item if we found a smaller one
                Comparable currentSmallest = items[locationOfSmallest];
                Comparable candidateSmallest = items[candidateLocation];
                if (candidateSmallest.compareTo(currentSmallest) < 0)
                    locationOfSmallest = candidateLocation;
            }

            // swap current position with location of smallest item
            Comparable temp = items[position];
            items[position] = items[locationOfSmallest];
            items[locationOfSmallest] = temp;
        }
    }
}
