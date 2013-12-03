package examples.recursion;

/**
 * Demonstration of indirect recursion
 * 
 * @author gerberma
 * 
 */
public class Indirect
{
    public static void main(String[] args)
    {
        infiniteRecursion1(); // beware:  infinite recursion...comment this line out if you want to run the next line!

        finiteRecursion1(5);
    }

    private static void infiniteRecursion1()
    {
        System.out.println("Hello 1");

        infiniteRecursion2();
    }

    private static void infiniteRecursion2()
    {
        System.out.println("Hello 2");

        infiniteRecursion1();
    }

    private static void finiteRecursion1(int n)
    {
        System.out.println(n);

        finiteRecursion2(n);
    }

    private static void finiteRecursion2(int n)
    {
        if (n <= 0)
        {
            System.out.println("Done!");
            return;
        }

        System.out.println(n);

        finiteRecursion1(n - 1);
    }
}
