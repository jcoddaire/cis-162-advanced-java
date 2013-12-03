package examples.polymorphismViaInheritance;

/**
 * Represents a credit card account
 * 
 * @author Matt
 * 
 */
public class CreditAccount extends AbstractAccount
{
    private double _interestRate;

    /**
     * Constructor
     * 
     * @param firstName
     * @param lastName
     * @param id
     * @param interestRate
     */
    public CreditAccount(String firstName, String lastName, int id, double interestRate)
    {
        super(firstName, lastName, id);

        _interestRate = interestRate;
    }

    /**
     * Gets a nicely formatted String representation of this account
     */
    public String getNiceString()
    {
        return "(Credit) " + _firstName + " " + _lastName + ":  " + _balance;
    }
}
