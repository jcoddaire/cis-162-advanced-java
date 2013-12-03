package myFiles;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import time.ClockListener;

/*
 * Contains the teller portion of the program.
 * At any time, a teller can be either idle or serving a customer.
 * 
 * Include an Instance Variable of type Customer to determine wheather a particular teller is
 * currently serving a customer or is idle. 
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class Teller extends Observable implements ClockListener{

	// creates a reference to the two queues.
	private LinkedList<Customer> tellerQueue;
	private ParameterHandler parHandle;
	
	// creates a new random time generator and a boolean. THe boolean will track wheather the teller is open or busy.
	private Random timeGenerator;
	
	private Customer currentCust;
	private int currentCustWaitTime;
	
	private int randomDeposit, randomWithdraw;
	private String transactionType;
	private String update;
	
	public Teller(LinkedList<Customer> tellerQueue, ParameterHandler parHandle)
	{
		this.tellerQueue=tellerQueue;
		this.parHandle=parHandle;
		
		timeGenerator = new Random();
	}

	public boolean event(int time) {
		
		System.out.println("Event Tick from Teller");
		
		if (currentCust!= null){
			System.out.println("Teller is busy with some customer "+currentCust);
			//handle our current customer
			if (currentCust instanceof NewCustomer){
				
				if (randomDeposit == 0){
					//time to kick the customer out of the bank
					System.out.println("A new customer has been removed from the bank.\n");
					update = ("A new customer has been removed from the bank.\n");
					currentCust = null;
					System.out.println("Teller is now open!");
					setChanged();
					notifyObservers();
					return true;
				}
				else
				{
					randomDeposit--;
					return false;
				}
			}
			else{
				if(currentCust instanceof RegularCustomer)
				{	
					boolean transaction = ((RegularCustomer) currentCust).getTransaction();
					if(transaction == true)//deposit
					{
						System.out.println("Teller " + this + " is busy serving "+currentCust);
						if (randomDeposit == 0)
						{
							//time to kick the customer out
							//possibly need code to handle sending statistics data to driver
							update = ("A regular customer has left the bank.\n");
							currentCust = null;
							System.out.println(currentCust+" See you next time!");
							System.out.println("Teller is now open!");
							setChanged();
							notifyObservers();
							return true;
						}
						else
						{
							randomDeposit--;
							return false;
						}
					}
					else
					{
						System.out.println("Teller " + this + " is busy serving"+currentCust);
						if (randomWithdraw == 0)
						{
							//time to kick the customer out
							//possibly need code to handle sending statistics data to driver
							currentCust = null;
							update = ("A regular customer has left the bank.\n");
							System.out.println(currentCust+" See you next time!");
							System.out.println("Teller is now open!");
							setChanged();
							notifyObservers();
							return true;
						}
						else
						{
							randomWithdraw--;
							return false;
						}
					}
					
				}
				else//it is going to be a busy customer.
				{
					boolean transaction = ((BusyCustomer) currentCust).getTransaction();
					if(transaction == true)//deposit
					{
						System.out.println("Teller "+this+" is serving BUSY"+currentCust);
						if (randomDeposit == 0)
						{
							//time to kick the customer out
							update = ("A busy customer has been removed from the bank.\n");
							currentCust = null;
							System.out.println(currentCust+" See you next time, BUSY!");
							System.out.println("Teller is now open!");
							setChanged();
							notifyObservers();
							return true;
						}
						else
						{
							randomDeposit--;
							return false;
						}
					}
					else
					{
						System.out.println("Teller "+this+" is serving BUSY"+currentCust);
						if (randomWithdraw == 0)
						{
							//time to kick the customer out
							//possibly need code to handle sending statistics data to driver
							update = ("A busy customer has been removed from the bank.\n");
							currentCust = null;
							System.out.println(currentCust+" See you next time, BUSY! GTFO!");
							System.out.println("Teller is now open!");
							setChanged();
							notifyObservers();
							return true;
						}
						else
						{
							randomWithdraw--;
							return false;
						}
					}
				}
			}
		}
		else{ if(tellerQueue.size() != 0)
			{
				//pull out customer
				currentCust = tellerQueue.removeFirst();
				currentCustWaitTime = currentCust.getWaited();
				if (currentCust instanceof NewCustomer){
					//we pulled a new customer
					System.out.println("New Customer has been accepted into TELLER.");
					randomDeposit = (int)(parHandle.get("meanDepositTime") + timeGenerator.nextGaussian() * parHandle.get("varDepositTime")); 
					System.out.println("Expected Wait time: " + randomDeposit);
					update = ("Teller accepted a new customer!\nExpected transaction time: " + randomDeposit + "\n");
					setChanged();
					notifyObservers();
					return true;
				}
				else
					if(currentCust instanceof RegularCustomer)
					{
						// prevents the customers from having the time expire while they are being serviced
						RegularCustomer cust = (RegularCustomer) currentCust;
						cust.setWaitTolerance(1000);
						
						boolean transaction =((RegularCustomer) currentCust).getTransaction();
							if (transaction == true)
							{
								transactionType = "deposit.";
							}
							else
							{
								transactionType = "withdraw.";
							}
						System.out.println("Regular Customer has been accepted.");
						System.out.println("Transaction Type is " + transactionType);
						//we pulled an angry customer
						if (transaction == true)
						{
							// perform deposit functionality
							randomDeposit = (int)(parHandle.get("meanDepositTime") + timeGenerator.nextGaussian() * parHandle.get("varDepositTime"));
							System.out.println("Expected Wait time: " + randomDeposit);
							update = ("Teller accepted a regular customer!\nExpected wait time: " + randomDeposit + "\n");
						}
						else //perform withdraw functionality
						{
							randomWithdraw = (int)(parHandle.get("meanWithdrawTime") + timeGenerator.nextGaussian() * parHandle.get("varWithdrawTime"));
							System.out.println("Expected Wait time: " + randomWithdraw);
							update = ("Teller accepted a regular customer!\nExpected wait time: " + randomWithdraw + "\n");
						}
						setChanged();
						notifyObservers();
						return true;
					}
					else//we pulled a busy customer
					{
						// prevents the customers from having the time expire while they are being serviced
						BusyCustomer cust = (BusyCustomer) currentCust;
						cust.setWaitTolerance(1000);
						
						boolean transaction =((BusyCustomer) currentCust).getTransaction();
							if (transaction == true)
							{
								transactionType = "deposit.";
							}
							else
							{
								transactionType = "withdraw.";
							}
						System.out.println("Busy Customer has been accepted.");
						System.out.println("Transaction Type is " + transactionType);
						//we pulled an angry customer
						if (transaction == true)
						{
							// perform deposit functionality
							randomDeposit = (int)(parHandle.get("meanDepositTime") + timeGenerator.nextGaussian() * parHandle.get("varDepositTime"));
							System.out.println("Expected Wait time: " + randomDeposit);
							update = ("Teller accepted a busy customer!\nExpected wait time: " + randomDeposit + "\n");
						}
						else //perform withdraw functionality
						{
							randomWithdraw = (int)(parHandle.get("meanWithdrawTime") + timeGenerator.nextGaussian() * parHandle.get("varWithdrawTime"));
							System.out.println("Expected Wait time: " + randomWithdraw);
							update = ("Teller accepted a busy customer!\nExpected wait time: " + randomWithdraw + "\n");
						}
						setChanged();
						notifyObservers();
						return true;
					}
				}
			else
				return false;
				
		}			
	}
	
	public int getWaitTime(){
		return currentCustWaitTime;
	}
	
	public String getUpdate()
	{
		return update;
	}
}