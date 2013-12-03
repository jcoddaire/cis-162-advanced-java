package examples.exceptions;

/**
 * Demonstrates a simple uncaught exception
 * 
 * @author Matt
 * 
 */
public class UncaughtException
{
    public static void main(String[] args)
    {
        int x = 10;
        int y = 3;

        System.out.println(x / y);

        int z = 0;
        System.out.println(x / z);

        System.out.println("Finished!");
    }
}
