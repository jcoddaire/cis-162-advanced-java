package examples.exceptions;

/**
 * Demonstration of the stack trace
 * 
 * @author Matt
 * 
 */
public class StackTrace
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        method1();
    }

    private static void method1()
    {
        method2();
    }

    private static void method2()
    {
        method3();
    }

    private static void method3()
    {
        System.out.println(1 / 0);
    }
}
