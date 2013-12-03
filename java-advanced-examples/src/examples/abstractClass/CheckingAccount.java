package examples.abstractClass;

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

    @Override
    public void doSomething()
    {
        // TODO Auto-generated method stub
    }    
}
