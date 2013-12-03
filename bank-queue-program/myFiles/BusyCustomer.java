package myFiles;

import java.util.LinkedList;


/*
 * This class specifies the details of a Busy Customer.
 * It inherits methods from the abstract class Customer
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class BusyCustomer extends Customer {

	@SuppressWarnings("unused")
	private int tolerance;
	private boolean accountAction;
	private LinkedList <Customer> tellerQueue;
	
	public BusyCustomer(int tolerance, boolean accountAction, LinkedList <Customer> tellerQueue) 
	{
		super();
		this.tolerance = tolerance;
		this.accountAction = accountAction;
		this.tellerQueue=tellerQueue;
	}

	public boolean getTransaction()
	{
		return accountAction;
	}
	@Override
	public boolean event(int time) {
		setWaited(getWaited() + 1);
		if (getWaited() >= getWaitTolerance())//time to leave THIS IS PROBABLY THE PROBLEM
		{
			if (tellerQueue.remove(this) == true)
			{
			System.out.println ("A Regular customer ran out of patience and left.");
			setChanged();
			notifyObservers();
			return true;
			}
			else 
				return false;
		}else
		return false;
	}
}