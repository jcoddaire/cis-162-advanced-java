package bank;
/**
 * This class creates the Checking account
 * The core files are inherited from the parent class, account
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class CheckingAccount extends Account
{
	private static final long serialVersionUID = 1L;
	
	private boolean overdraftProtect;
	private double overdraftLimit;
	private double overdraftFee;
	private NumberFormat f;
	private String state;
	/*********************************************************************************************
    This constructor creates the basic functionality for the Checking account
    *********************************************************************************************/
	public CheckingAccount(Customer cust, String number, double balance, GregorianCalendar dateOpened, boolean overdraftProtect, double overdraftLimit, double overdraftFee)
	{
		super(number, dateOpened, balance, cust);
		this.overdraftProtect=overdraftProtect;
		this.overdraftLimit=overdraftLimit;
		this.overdraftFee=overdraftFee;
	}

	public Double getLimit()
	{
		return overdraftLimit;
	}
	@Override
	// prints out the details for the lower text area on the main GUI
	public String details() {
		
		f = NumberFormat.getCurrencyInstance();
		
		Date newDate = dateOpened.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(newDate);
		if (overdraftProtect == true)
			state = "Yes";
		else
			state = "No";
		return "Checking Account Opened: " + dateString + "\nOverdraft Protection: " + state + 
		"\nOverdraft Limit: " + f.format(overdraftLimit) + "\nOverdraft Fee: " + f.format(overdraftFee) + "\n\n" + "Owner: " + owner + "\nSSN: " + owner.getSsn() + 
		"\nAddress: " +owner.getAddress()+", "+owner.getCity()+", "+owner.getState()+" "+owner.getZip() + "\nPhone: " + owner.getPhone();

	}
	public String getType()
	{
		return "CheckingAccount";
	}
}
