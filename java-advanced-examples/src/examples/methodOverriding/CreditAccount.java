package examples.methodOverriding;

/**
 * Represents a credit card account
 * 
 * @author Matt
 * 
 */
public class CreditAccount extends Account
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
}
