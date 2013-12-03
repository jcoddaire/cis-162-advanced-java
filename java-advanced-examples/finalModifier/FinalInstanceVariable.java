package examples.finalModifier;

/**
 * Class that contains an instance variable that cannot be modified
 * 
 * @author Matt
 *
 */
public class FinalInstanceVariable
{
    protected final int _x;
    
    /**
     * Constructor
     * @param x
     */
    public FinalInstanceVariable(int x)
    {
        _x = x;
    }
}
