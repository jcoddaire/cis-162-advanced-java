package examples.inheritanceReuse;

/**
 * Represents a savings account - this is a bad design because effort is duplicated across classes.
 * A better design is given in the abstractClass example.
 * 
 * @author Matt
 * 
 */
public class SavingsAccount
{
    private String _firstName;
    private String _lastName;
    private int _id;
    private double _interestRate;
    private double _balance;

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
        _firstName = firstName;
        _lastName = lastName;
        _id = id;
        _interestRate = interestRate;
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
