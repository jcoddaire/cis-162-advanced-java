package examples.equals;

/**
 * Account class
 * 
 * @author Matt
 * 
 */
public class Account
{
    private int _id;

    /**
     * Constructor
     * 
     * @param id
     */
    public Account(int id)
    {
        _id = id;
    }

    /**
     * Returns true if the current account equals another object
     */
    public boolean equals(Object o)
    {
        if (o instanceof Account)
        {
            Account acct = (Account) o;

            return _id == acct._id;
        }

        return false;
    }
}
