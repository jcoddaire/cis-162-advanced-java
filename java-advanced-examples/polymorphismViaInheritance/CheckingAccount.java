package examples.polymorphismViaInheritance;

/**
 * Represents a checking account
 * 
 * @author Matt
 * 
 */
public class CheckingAccount extends AbstractAccount
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

    /**
     * Gets a nicely formatted String representation of this account
     */
    public String getNiceString()
    {
        return "(Checking) " + _firstName + " " + _lastName + ":  " + _balance;
    }
}
