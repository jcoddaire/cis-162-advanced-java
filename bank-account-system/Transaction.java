package bank;
/**
 * This class sets the standard for a transaction object and contains
 * methods that are commonly used with transaction objects
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.GregorianCalendar;

public class Transaction implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private TransactionType type;
	private java.util.GregorianCalendar date;
	private double amount;
	/*********************************************************************************************
    This constructor creates the basic Transaction object
    *********************************************************************************************/
	public Transaction (TransactionType type, java.util.GregorianCalendar date, double amount)
	{
		this.type=type;
		this.date=date;
		this.amount=amount;
	}
	public GregorianCalendar getDate()
	{
		return date;
	}
	public String transactionConverter()
	{
		String converted = "Type: " + type.name() + " Date: " + DateFormat.getDateInstance().format(date.getTime()) + " Amount: " + NumberFormat.getCurrencyInstance().format(amount)+ "\n";
		return converted;
	}
}
