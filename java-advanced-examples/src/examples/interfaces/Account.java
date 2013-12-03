package examples.interfaces;

/**
 * Interface for all account types
 * 
 * @author Matt
 * 
 */
public interface Account
{
    /**
     * Withdraws money from account
     * @param amount
     * @return Account balance
     */
    double withdrawFrom(double amount);

    /**
     * Deposits money into account
     * @param amount
     * @return Account balance
     */
    double depositInto(double amount);
}
