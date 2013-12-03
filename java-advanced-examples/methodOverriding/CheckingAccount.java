package examples.methodOverriding;

/**
 * Represents a checking account
 * 
 * @author Matt
 * 
 */
public class CheckingAccount extends Account
{
    /**
     * Constructor
     * 
     * @param firstName
     * @param lastName
     * @param id
     */
    public CheckingAccount(String firstName, String lastName, int id)
    {
        super(firstName, lastName, id);
    }  
    
    public double withdrawFrom(double amount)
    {
        super.withdrawFrom(amount);
        
        // perform overdraft protection, etc.
        
        // return balance        
        return getBalance();
    }
}
