package examples.sorting;

import java.util.Random;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // generate some random numbers
        Random random = new Random();
        Integer[] numbers = new Integer[50000];
        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt(100);

        Integer[] toSort;
        long start, end;
        
        // run quick sort
        toSort = numbers.clone();
        System.out.print("Running Quicksort...");
        start = System.currentTimeMillis();
        QuickSort.sort(toSort);
        end = System.currentTimeMillis();
        System.out.println("done in " + ((end - start) / 1000F) + " seconds.");
        
        // run merge sort
        toSort = numbers.clone();
        System.out.print("Running merge sort...");
        start = System.currentTimeMillis();
        MergeSort.sort(toSort);
        end = System.currentTimeMillis();
        System.out.println("done in " + ((end - start) / 1000F) + " seconds.");

        // run insertion sort
        toSort = numbers.clone();
        System.out.print("Running insertion sort...");
        start = System.currentTimeMillis();
        InsertionSort.sort(toSort);
        end = System.currentTimeMillis();
        System.out.println("done in " + ((end - start) / 1000F) + " seconds.");

        // run selection sort
        toSort = numbers.clone();
        System.out.print("Running selection sort...");
        start = System.currentTimeMillis();
        SelectionSort.sort(toSort);
        end = System.currentTimeMillis();
        System.out.println("done in " + ((end - start) / 1000F) + " seconds.");

        // run bubble sort
        toSort = numbers.clone();
        System.out.print("Running bubble sort...");
        start = System.currentTimeMillis();
        BubbleSort.sort(toSort);
        end = System.currentTimeMillis();
        System.out.println("done in " + ((end - start) / 1000F) + " seconds.");
    }
}
