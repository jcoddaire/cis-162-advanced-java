package bank;
/**
 * This class will set the standards for all account objects.
 * It will be abstract as defined in the documentation.
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public abstract class Account implements Serializable
{

	protected String number;
	protected java.util.GregorianCalendar dateOpened;
	protected double balance;
	
	protected Customer owner;
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	//This must be here. It makes sure they have the correct version of your code. 1L means 1 long. 
	private static final long serialVersionUID = 1L;
	
	/*****************************************************************
    The base constructor for all account classes and all child classes
    *****************************************************************/
	public Account (String number, GregorianCalendar dateOpened, double balance, Customer owner)
	{
		this.number=number;
		this.dateOpened=dateOpened;
		this.balance=balance;
		this.owner=owner;
	}
	
	public void transactions(Transaction type)
	{
			transactions.add(type);
	}
	// withdraws money
	public double withdrawFrom(double amount)
	{
		balance = balance - amount;
		return balance;
	}
	
	// deposits money
	public double depositInto(double amount)
	{
		balance = balance+ amount;
		return balance;
	}
	public String toString()
	{
		return "Number: " +number + "; Owner: " +owner +"; Current Balance: "+ balance;
	}
	public abstract String details();
	
	public abstract String getType();
	
	public Customer getOwner(){
		return owner;
	}
	
	public double getBalance() {
		
		return balance;
	}
	public String getNumber(){
		return number;
	}
	public ArrayList<Transaction> getTransactions()
	{
		return transactions;
	}
	public GregorianCalendar getDate()
	{
		return dateOpened;
	}
}
