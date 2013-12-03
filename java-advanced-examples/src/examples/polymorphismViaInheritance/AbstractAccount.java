package examples.polymorphismViaInheritance;

/**
 * Abstract base class for all account types
 * 
 * @author Matt
 *
 */
public abstract class AbstractAccount
{
    protected String _firstName;
    protected String _lastName;
    protected int _id;
    protected double _balance;
    
    /**
     * Constructor
     * 
     * @param firstName
     * @param lastName
     * @param id
     */
    public AbstractAccount(String firstName, String lastName, int id)
    {
        _firstName = firstName;
        _lastName = lastName;
        _id = id;        
        _balance = 0;
    }   
    
    /**
     * Gets a nicely formatted String representation of this account
     * @return
     */
    public abstract String getNiceString();
}
