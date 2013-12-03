package examples.abstractClass;

/**
 * Abstract base class for all account types
 * 
 * @author Matt
 *
 */
public abstract class AbstractAccount
{
    private String _firstName;
    private String _lastName;
    private int _id;
    private double _balance;
    
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
     * Withdraws money from account
     * 
     * @param amount
     * @return Account balance
     */
    public double withdrawFrom(double amount)
    {
        _balance -= amount;

        return _balance;
    }

    /**
     * Deposits money into account
     * 
     * @param amount
     * @return Account balance
     */
    public double depositInto(double amount)
    {
        _balance += amount;

        return _balance;
    }
    
    /**
     * Any sub-classes must implement this method in order for everything to compile
     */
    public abstract void doSomething();
}
