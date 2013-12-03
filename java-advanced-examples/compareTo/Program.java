package examples.compareTo;

/**
 * Simple demo of the compareTo method
 * 
 * @author gerberma
 *
 */
public class Program
{
    public static void main(String[] args)
    {
        Integer x = 3;
        Integer y = 7;
        
        // x is less than y
        System.out.println(x.compareTo(y));
        
        // y is greater than x
        System.out.println(y.compareTo(x));
        
        // x equals x
        System.out.println(x.compareTo(x));
    }
}
