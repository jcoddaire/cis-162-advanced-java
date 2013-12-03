package examples.methodOverriding;

/**
 * Represents a savings account
 * 
 * @author Matt
 * 
 */
public class SavingsAccount extends Account
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
    public SavingsAccount(String firstName, String lastName, int id, double interestRate)
    {
        super(firstName, lastName, id);

        _interestRate = interestRate;
    }
}
