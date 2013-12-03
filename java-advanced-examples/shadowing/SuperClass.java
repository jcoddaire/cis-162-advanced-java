package examples.shadowing;

/**
 * Simple super-class
 * @author Matt
 *
 */
public class SuperClass
{
    // this variable is viewable outside the class
    protected int _x;
    
    /**
     * Constructor
     * @param x
     */
    public SuperClass(int x)
    {
        _x = x;
    }
}
