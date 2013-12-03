package examples;

import java.util.ArrayList;

/**
 * Demonstrates basic debugging practices
 * 
 * @author Matt
 *
 */
public class Debugger
{

    /**
     * Main program
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        int x = getInteger1();
        int y = getInteger2();
        int z = x + y;
        System.out.println(z);
    }
    
    /**
     * Returns 1
     * @return 1
     */
    private static int getInteger1()
    {
        return 1;
    }
    
    /**
     * Returns the value of getInteger3
     * @return the value of getInteger3
     */
    private static int getInteger2()
    {
        return getInteger3();
    }
    
    /**
     * Returns 3
     * @return 3
     */
    private static int getInteger3()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 100; ++i)
            list.add(i);

        // set a breakpoint on this line and inspect the content of the local variable "list"
        return list.get(3);
    }
}
