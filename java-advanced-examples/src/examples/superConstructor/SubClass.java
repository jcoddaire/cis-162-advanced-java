package examples.superConstructor;

/**
 * Simple sub-class
 * 
 * @author Matt
 *
 */
public class SubClass extends SuperClass
{
    private int _y;
    
    /**
     * Constructor
     * @param y
     */
    public SubClass(int y)
    {     
        super(y);  // if this is commented out, the default super() will be called, but this won't work because such a constructor does not exist in the super-class!
        
        // assign instance variable
        _y = y + 1;
    }
}
