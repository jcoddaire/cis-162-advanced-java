package examples.abstractClass;

/**
 * Represents a savings account
 * 
 * @author Matt
 * 
 */
@SuppressWarnings("unused")
public class SavingsAccount extends AbstractAccount
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

    @Override
    public void doSomething()
    {
        // TODO Auto-generated method stub
        
    }
}
