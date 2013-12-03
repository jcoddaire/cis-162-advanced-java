package examples.exceptions;

/**
 * Demonstrates unchecked exceptions
 * 
 * @author Matt
 * 
 */
public class UncheckedException
{
    public static void main(String[] args)
    {
        System.out.println(getNumber(0));
        System.out.println(getNumber(1));
        System.out.println(getNumber(2));
        System.out.println(getNumber(3));
    }

    private static int getNumber(int index)
    {
        int[] numbers = new int[] { 2, 1, 0 };

        return numbers[index];
    }
}
