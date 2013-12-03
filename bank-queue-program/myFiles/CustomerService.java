package myFiles;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
import time.ClockListener;

/*
 * Contains the customer service portion of the program.
 * 
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class CustomerService extends Observable implements ClockListener
{
	private Customer currentCust;
	private int currentCustWaitTime;
	
	private LinkedList<Customer> customerQueue;
	private LinkedList<Customer> tellerQueue;
	private ParameterHandler parHandle;
	private Random timeGenerator;
	
	private int randomOpenTime, randomCloseTime;
	
	private String direction;
	private String time;
	
	public CustomerService(LinkedList<Customer> customerQueue, LinkedList<Customer> tellerQueue, ParameterHandler parHandle)
	{
		this.customerQueue=customerQueue;
		this.tellerQueue=tellerQueue;
		this.parHandle=parHandle;
		timeGenerator = new Random();
	}
	
	public boolean event(int tick)
	{
		System.out.println("Event Tick from CS");
		if (currentCust!= null){
			System.out.println("CS is busy with some customer "+currentCust);
			//handle our current customer
			if (currentCust instanceof NewCustomer){
				
				if (randomOpenTime == 0){
					//time to kick the customer to the teller queue
					tellerQueue.add(currentCust);
					System.out.println("Added NEW"+currentCust+" to Teller queue");
					direction = ("New customer has been directed to the teller.\n");
					currentCust = null;
					System.out.println("Customer Service is now open!");
					setChanged();
					notifyObservers();
					return true;
				}
				else
				{
					randomOpenTime--;
					return false;
				}
			}
			else{// instance of angry customer
				if (randomCloseTime == 0){
					//time to kick the customer out
					//possibly need code to handle sending statistics data to driver
					System.out.println(currentCust+" has been jettisoned :D");
					direction = ("Angry customer has exited the bank.\n");
					currentCust = null;
					System.out.println("Customer Service is now open!");
					setChanged();
					notifyObservers();
					return true;
				}else{
					randomCloseTime--;
					return false;
				}
			}
		}else if(customerQueue.size() != 0)
		{				
				//pull out customer
				currentCust = customerQueue.removeFirst();
				currentCustWaitTime = currentCust.getWaited();
				if (currentCust instanceof NewCustomer){
					//we pulled a new customer
					System.out.println("New Customer Has been accepted.");
					direction = ("Customer Service has accepted a new customer\n");
					randomOpenTime = (int)(parHandle.get("meanOpenAcctTime") + timeGenerator.nextGaussian() * parHandle.get("varOpenAcctTime")); 
					System.out.println("Expected Wait time: " + randomOpenTime);
					time = ("Expected transaction time: " + randomOpenTime + "\n");
					setChanged();
					notifyObservers();
					return true;
				}
				else
				{
					System.out.println("Angry Customer Has been accepted.");
					direction = ("Customer Service has accepted an angry customer.\n");
					//we pulled an angry customer
					randomCloseTime = (int)(parHandle.get("meanCloseAcctTime") + timeGenerator.nextGaussian() * parHandle.get("varCloseAcctTime")); 
					System.out.println("Expected Wait time: " + randomCloseTime);
					time = ("Expected transaction time: " + randomCloseTime + "\n");
					setChanged();
					notifyObservers();
					return true;
				}
				
		}
		else
			return false;
	}
	
	public int getWaitTime(){
		return currentCustWaitTime;
	}
	
	// this method gets whatever customer is created, makes it a string, and sends it to the GUI when it is called
	public String getDirection()
	{
		return direction;
	}
	public String getTime()
	{
		String temp = time;
		time = "";
		return temp;
	}
}