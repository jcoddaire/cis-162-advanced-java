package examples.sorting;

/**
 * Demonstrates the merge sort algorithm
 * 
 * @author Matt
 * 
 */
public class MergeSort
{
    /**
     * Sorts an array of items using merge sort
     * 
     * @param items
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void sort(Comparable[] items)
    {
        // base case:  length of 0 or 1
        if (items.length <= 1)
            return;

        // get left/right sub-arrays
        Comparable[] left = getLeftHalf(items);
        Comparable[] right = getRightHalf(items);

        // sort left/right sub-arrays
        sort(left);
        sort(right);

        // merge left/right sub-arrays into main array
        merge(left, right, items);
    }

    /**
     * Get the left half of an array
     * 
     * @param items
     * @return
     */
    public static Comparable[] getLeftHalf(Comparable[] items)
    {
        // find middle of array
        int middle = items.length / 2;

        // create left sub-array
        Comparable[] left = new Comparable[middle];
        for (int i = 0; i < left.length; ++i)
            left[i] = items[i];

        return left;
    }

    /**
     * Gets the right half of an array
     * 
     * @param items
     * @return
     */
    public static Comparable[] getRightHalf(Comparable[] items)
    {
        // find middle of array
        int middle = items.length / 2;

        // create right sub-array
        Comparable[] right = new Comparable[items.length - middle];
        for (int i = 0; i < right.length; ++i)
            right[i] = items[middle + i];

        return right;
    }

    /**
     * Merges two arrays into a single array
     * 
     * @param array1
     * @param array2
     * @param mergedArray
     */
    public static void merge(Comparable[] array1, Comparable[] array2, Comparable[] mergedArray)
    {
        // combine sub-arrays
        int leftIndex = 0;
        int rightIndex = 0;
        int insertIndex = 0;
        while (leftIndex < array1.length && rightIndex < array2.length)
            if (array1[leftIndex].compareTo(array2[rightIndex]) < 0)
                mergedArray[insertIndex++] = array1[leftIndex++];
            else
                mergedArray[insertIndex++] = array2[rightIndex++];

        // copy remaining items from sub-arrays into the original array (only one of the sub-arrays will have items left in it)
        for (int i = leftIndex; i < array1.length; ++i)
            mergedArray[insertIndex++] = array1[i];

        for (int i = rightIndex; i < array2.length; ++i)
            mergedArray[insertIndex++] = array2[i];
    }
}
