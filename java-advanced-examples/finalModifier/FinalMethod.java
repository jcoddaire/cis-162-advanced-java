package examples.finalModifier;

/**
 * Standard class containing a method that cannot be overridden
 * 
 * @author Matt
 * 
 */
public class FinalMethod
{
    /**
     * Constructor
     * 
     */
    public FinalMethod()
    {
    }

    /**
     * This method cannot be overridden in a child class
     */
    public final void method1()
    {
        // do something
    }
}
