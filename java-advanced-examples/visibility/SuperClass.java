package examples.visibility;

/**
 * Simple super-class
 * @author gerberma
 *
 */
public class SuperClass
{
    private int _x; // only available inside the class
    
    /**
     * Constructor
     * @param x
     */
    public SuperClass(int x)
    {
        _x = x;
    }
    
    /**
     * Prints the value of _x - available everywhere
     */
    public void printX()
    {
        System.out.println(_x);
    }
    
    /**
     * Only available from the current class, any inheriting classes, and any class in the same package (i.e., examples.visibility) 
     */
    protected void doSomethingElse()
    {
        
    }
}
