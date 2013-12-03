package myFiles;

/*
 * This class contains the details of New Customers
 * It inherits from the abstract class Customer.
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class NewCustomer extends Customer{

	private boolean accAction;
	
	public NewCustomer(boolean accAction) 
	{
		// these customers have no timing restrictions.
		super();
		this.accAction=accAction;
	}
	public boolean getTransaction()
	{
		return accAction;
	}
	@Override
	public boolean event(int time) {
		// TODO Auto-generated method stub
		setWaited(getWaited() + 1);
		return false;
	}

}
