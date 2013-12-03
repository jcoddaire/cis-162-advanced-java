package examples;

/**
 * Demonstration of the bytecode viewer - you will need to install the plugin:
 *   
 *   http://andrei.gmxhome.de/bytecode/index.html
 *   
 * @author gerberma
 *
 */
public class Bytecode
{
    protected int _x;
    
    /**
     * @param args
     */
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
        int x = 2;
        int y = 3;
        int z = x + y;

        if (z == (x + y))
            System.out.println("Equal");
        else
            System.out.println("Not equal");

        int a = 0;
        for (int i = x; i < y; ++i)
            a += i;
    }
}
