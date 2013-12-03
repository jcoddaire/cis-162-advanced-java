package examples.collections;

/**
 * Demonstrates the operation of a dynamic array
 * 
 * @author gerberma
 * 
 */
public class DynamicArrayProgram
{
    private static int[] array;
    private static int nextAdd;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        array = new int[2];
        nextAdd = 0;

        for (int i = 0; i < 20; ++i)
            add(i);
    }

    /**
     * Adds a value to the end of the array
     * 
     * @param value
     */
    private static void add(int value)
    {
        if (nextAdd >= array.length)
        {
            System.out.println("Reallocating");

            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; ++i)
                newArray[i] = array[i];

            array = newArray;
        }

        array[nextAdd++] = value;

        print(array);
    }

    /**
     * Prints an array
     * 
     * @param array
     */
    private static void print(int[] array)
    {
        for (int i = 0; i < array.length; ++i)
            System.out.print((i > 0 ? "," : "") + array[i]);

        System.out.println();
    }
}
