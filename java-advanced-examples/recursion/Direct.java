package examples.recursion;

/**
 * Demonstration of direct recursion
 * 
 * @author gerberma
 * 
 */
public class Direct
{
    public static void main(String[] args)
    {
        infiniteRecursion(); // beware:  infinite recursion...comment this line out if you want to run the next line!

        finiteRecursion(5);
    }

    private static void infiniteRecursion()
    {
        System.out.println("Hello!");

        infiniteRecursion();
    }

    private static void finiteRecursion(int n)
    {
        if (n <= 0)
        {
            System.out.println("Done!");
            return;
        }

        System.out.println(n);

        finiteRecursion(n - 1);

        System.out.println(n);
    }
}
