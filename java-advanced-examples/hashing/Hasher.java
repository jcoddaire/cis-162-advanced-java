package examples.hashing;

/**
 * Demonstrate the generation of custom hash codes
 * 
 * @author gerberma
 * 
 */
public class Hasher
{
    private int number;

    public Hasher(int number)
    {
        this.number = number;
    }

    /**
     * Gets hash code for this object
     */
    public int hashCode()
    {
        return 5;
        
        // are any of the following hash functions better?
        
        //return Math.abs(number);
        //return 0;
        //return number;
    }
}
