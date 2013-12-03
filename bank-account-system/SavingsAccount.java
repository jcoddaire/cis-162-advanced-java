package bank;
/**
 * This class creates the outline for the Savings Account object.
 * It gets some methods from the account class.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class SavingsAccount extends Account
{
	private static final long serialVersionUID = 1L;
	
	private double minimumBalance;
	private double interestRate;
	private NumberFormat f;
	/*********************************************************************************************
    This constructor creates the basic functionality of the Savings Account Object
    *********************************************************************************************/
	public SavingsAccount(Customer owner, String number, double balance, GregorianCalendar dateOpened, double minimumBalance, double interestRate)
	{
		super(number, dateOpened, balance, owner);
		this.minimumBalance=minimumBalance;
		this.interestRate=interestRate;
	}
	
	public String details()
	{
		f = NumberFormat.getCurrencyInstance();
		
		Date newDate = dateOpened.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(newDate);
		
		return "Savings Account Opened: " + dateString + "\nMinimum Balance: " + f.format(minimumBalance) + 
				"\nInterest Rate: " + f.format(interestRate) + "\n\n" + "Owner: " + owner + "\nSSN: " + owner.getSsn() + 
				"\nAddress: " +owner.getAddress()+", "+owner.getCity()+", "+owner.getState()+" "+owner.getZip() + "\nPhone: " + owner.getPhone();
	}
	public String getType()
	{
		return "SavingsAccount";
	}
	public double getSavingsBalance(){
		return minimumBalance;
	}
}