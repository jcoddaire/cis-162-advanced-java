package examples.interfaces;

/**
 * Represents a checking account - this is a bad design because effort is duplicated across classes.
 * A better design is given in the abstractClass example.
 * 
 * @author Matt
 * 
 */
public class CheckingAccount implements Account
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
    public CheckingAccount(String firstName, String lastName, int id)
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
}
