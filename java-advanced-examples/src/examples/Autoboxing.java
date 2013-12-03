package examples;

/**
 * Demonstrates the overhead involved in autoboxing
 * 
 * @author Matt
 *
 */
public class Autoboxing
{
    /**
     * @param args
     */
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
        Bytecode b = new Bytecode();
        b._x = 3;
        int iterations = 1000000000;
        
        // this will take a while due to autoboxing overhead
        System.out.println("Starting to use Integer");
        for(int i = 0; i < iterations; ++i)
        {
            Integer x = i;
            Integer y = i + 1;
        }
        
        System.out.println("Done using Integer");
        
        // this will be quick! (no autoboxing overhead)
        System.out.println("Starting to use int");
        for(int i = 0; i < iterations; ++i)
        {
            int x = i;
            int y = i + 1;
        }
        
        System.out.println("Done using int");
    }
}
