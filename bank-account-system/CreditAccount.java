package bank;
/**
 * This sets the defaults for all Credit accounts.
 * It inherits some methods from the parent class, account.
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class CreditAccount extends Account
{
	private static final long serialVersionUID = 1L;
	
	private double creditLimit;
	private double interestRate;
	private NumberFormat f;
	/*********************************************************************************************
    This constructor creates the basic functionality of the Credit Account object
    *********************************************************************************************/
	public CreditAccount(Customer owner, String number, double balance, GregorianCalendar dateOpened, double creditLimit, double interestRate) 
	{
		super(number, dateOpened, balance, owner);
		this.creditLimit =creditLimit;
		this.interestRate=interestRate;
	}

	public double getLimit()
	{
		return creditLimit;
	}
	@Override
	// this gets sent to the south TextArea on the main GUI
	public String details() {

		f = NumberFormat.getCurrencyInstance();
		
		Date newDate = dateOpened.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(newDate);
		
		return "Credit Account Opened: " + dateString + "\nCredit Limit: " + f.format(creditLimit) + 
		"\nInterest Rate: " + f.format(interestRate) + "\n\n" + "Owner: " + owner + "\nSSN: " + owner.getSsn() + 
		"\nAddress: " +owner.getAddress()+", "+owner.getCity()+", "+owner.getState()+" "+owner.getZip() + "\nPhone: " + owner.getPhone();

	}
	public String getType()
	{
		return "CreditAccount";
	}
}
