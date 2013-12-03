package examples.collections;

/**
 * Demonstrates basic array operations
 * 
 * @author Matt
 * 
 */
public class ArrayProgram
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        byte[] array = new byte[5];

        // write
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;

        // read
        System.out.println(array[1]);

        // insert at index
        print(array);
        insertAt(array, (byte)3, 1);
        print(array);
        insertAt(array, (byte)4, 1);
        print(array);

        // remove at index
        removeAt(array, 1);
        print(array);
    }

    /**
     * Inserts a value at a specified location
     * 
     * @param array
     * @param value
     * @param position
     */
    private static void insertAt(byte[] array, byte value, int position)
    {
        for (int i = array.length - 1; i > position; --i)
            array[i] = array[i - 1];

        array[position] = value;
    }

    /**
     * Removes a value at a specified location
     * 
     * @param array
     * @param index
     */
    private static void removeAt(byte[] array, int index)
    {
        for (int i = index; i < array.length - 1; ++i)
            array[i] = array[i + 1];

        array[array.length - 1] = 0;
    }

    /**
     * Prints an array
     * 
     * @param array
     */
    private static void print(byte[] array)
    {
        for (int i = 0; i < array.length; ++i)
            System.out.print((i > 0 ? "," : "") + array[i]);

        System.out.println();
    }
}
