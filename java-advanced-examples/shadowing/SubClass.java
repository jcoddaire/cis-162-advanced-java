package examples.shadowing;

/**
 * Simple sub-class that has a shadow variable
 * @author Matt
 *
 */
public class SubClass extends SuperClass
{
    // this variable is available outside the class
    protected int _x;
    
    /**
     * Constructor
     * @param x
     */
    public SubClass(int x)
    {
        super(x);
        
        _x = x + 1;
    }
}
