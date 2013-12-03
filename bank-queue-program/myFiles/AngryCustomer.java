package myFiles;


/*
 * This class specifies the details of an Angry Customer object
 * They have NO timing restrictions
 * They only want to close their account. Nothing else.
 * 
 *  Jacob A. Coddaire
 * 	CIS 163-07
 */
public class AngryCustomer extends Customer{
	
	public AngryCustomer() 
	{
		super();
	}

	@Override
	public boolean event(int time) {
		setWaited(getWaited() + 1);
		return false;
	}

}
