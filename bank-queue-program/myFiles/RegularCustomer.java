package myFiles;

import java.util.LinkedList;

/*
 * This class gives the details of Regular Customer objects.
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class RegularCustomer extends Customer{
	
	private boolean accAction;
	private LinkedList <Customer> tellerQueue;
	
	public RegularCustomer(int tolerance, boolean accAction, LinkedList <Customer> tellerQueue )
	{
		// do regular transactions
		super();
		this.setWaitTolerance(tolerance);
		this.accAction = accAction;
		this.tellerQueue=tellerQueue;
	}

	@Override
	public boolean event(int time) {
		setWaited(getWaited() + 1);
		
		if (getWaited() >= getWaitTolerance())//time to leave
		{
			//need access to the LinkedList he's waiting in, then remove him.
			
			if (tellerQueue.remove(this) == true)
			{
			System.out.println ("A Regular customer has run out of patience and left.");
			setChanged();
			notifyObservers();
			return true;
			}
			else 
				return false;
		}else
		return false;
	}

	public boolean getTransaction()
	{
		return accAction;
	}

}
