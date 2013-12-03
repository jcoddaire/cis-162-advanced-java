package examples.methodOverriding;

/**
 * Base class for all account classes
 * 
 * @author gerberma
 * 
 */
public class Account
{
    private String _firstName;
    private String _lastName;
    private int _id;
    private double _balance;

    public int getID()
    {
        return _id;
    }

    public double getBalance()
    {
        return _balance;
    }

    /**
     * Constructor
     * 
     * @param firstName
     * @param lastName
     * @param id
     */
    public Account(String firstName, String lastName, int id)
    {
        _firstName = firstName;
        _lastName = lastName;
        _id = id;
        _balance = 0;
    }

    /**
     * Overrides the toString method provided by Object
     */
    public String toString()
    {
        return _firstName + " " + _lastName + ":  " + _balance;
    }

    /**
     * Overrides the equals method provided by Object - compares the IDs of the accounts
     */
    public boolean equals(Object o)
    {
        // make sure the object passed is actually an account
        if (o instanceof Account)
        {
            // cast to account - this is safe because we checked for it above
            Account acct = (Account) o;
            
            // compare account IDs
            if (_id == acct._id)
                return true;
        }

        return false;
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
