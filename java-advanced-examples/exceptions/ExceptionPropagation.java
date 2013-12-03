package examples.exceptions;

/**
 * Demonstrates exception propagation
 * 
 * @author Matt
 * 
 */
public class ExceptionPropagation
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            method1();
        }
        catch (ArithmeticException e)
        {
            System.out.println("Caught exception:  " + e);
        }
    }

    private static void method1()
    {
        System.out.println("Starting method1");
        method2();
        System.out.println("Done with method1");
    }

    private static void method2()
    {
        System.out.println("Starting method2");
        method3();
        System.out.println("Done with method2");
    }

    private static void method3()
    {
        System.out.println("Starting method3");
        System.out.println(1 / 0);
        System.out.println("Done with method3");
    }
}
