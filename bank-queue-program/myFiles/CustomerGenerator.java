package myFiles;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import time.Clock;
import time.ClockListener;

/*
 * This class generates the customers.
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class CustomerGenerator extends Observable implements ClockListener {
	private float randomCustomer;
	private int nextTime;

	private float busyChance, newChance, angryChance;

	private Random customerGenerator;
	private String output;

	private ParameterHandler parHandle;
	private String update;

	private boolean busyAction;

	// creates the queues
	private LinkedList<Customer> serviceQueue;
	private LinkedList<Customer> tellerQueue;

	private Clock clock;

	@SuppressWarnings("unused")
	private boolean hasGenerated;
	
	private int nextTimevar;

	private Observer gui;

	public CustomerGenerator(ParameterHandler parHandle,
			LinkedList<Customer> serviceQueue,
			LinkedList<Customer> tellerQueue, Clock clock, Observer gui) {
		this.parHandle = parHandle;
		busyChance = parHandle.get("busyCustomerPercentage") / 100F;
		newChance = parHandle.get("newCustomerPercentage") / 100F;
		angryChance = parHandle.get("dissatisfiedPercentage") / 100F;

		// creates the LinkedLists that the customers are stored in. THE
		// "Queues"
		this.serviceQueue = serviceQueue;
		this.tellerQueue = tellerQueue;
		this.clock = clock;
		customerGenerator = new Random();
		nextTimevar = 0;
		nextTime = 0;
		hasGenerated = false;
		this.gui = gui;
	}

	// actually generates the customers
	public boolean event(int time) {
		System.out.println("Event Tick from cus generator");
		if (nextTime == 0) {
			nextTimevar = (int) (parHandle.get("meanCustomerArrivalTime") + customerGenerator.nextGaussian()* parHandle.get("varCustomerArrivalTime"));
			nextTime = time  + Math.abs(nextTimevar);// ensures it is always positive
			return false;
		} else {
			if (time == nextTime) {
				// generate customer
				randomCustomer = customerGenerator.nextFloat();
				hasGenerated = true;
				// determines which type of customer has been generated
				if (randomCustomer < angryChance) {
					AngryCustomer customer = new AngryCustomer();
					System.out.println("Angry customer generated.");
					clock.register(customer);
					output = "An angry customer has entered the bank.\n";
					update = "angry";
					serviceQueue.add(customer);
					System.out.println("Angry customer added to serviceQueue.");
				} else if (randomCustomer < angryChance + newChance) {
					busyAction = true; // default to deposit
					NewCustomer customer = new NewCustomer(busyAction);
					customer.addObserver(gui);
					System.out.println("New customer generated.");
					clock.register(customer);
					output = "A new customer has entered the bank.\n";
					update = "new";
					serviceQueue.add(customer);
					System.out.println("New customer added to serviceQueue.");
				} else if (randomCustomer < angryChance + newChance
						+ busyChance) {
					int randomToleranceTime = (int) (parHandle.get("meanBusyCustWaitTolerance") + customerGenerator.nextGaussian() * parHandle.get("varBusyCustWaitTolerance")); 
					// generates a random number between 100-140
					int positiveToleranceTime = java.lang.Math.abs(randomToleranceTime);// makes the tolerance time always positive
					
					// determines the account action (Deposit/ withdraw)
					float randomAction = customerGenerator.nextFloat();
					busyAction = false; // default to withdraw
					if (randomAction < (parHandle.get("depositPercentage") / 100F))
						busyAction = true; // true == deposit

					// creates the customer
					BusyCustomer customer = new BusyCustomer(positiveToleranceTime, busyAction,tellerQueue);
					customer.addObserver(gui);
					System.out.println("Busy customer generated.");
					clock.register(customer);
					// allows the update to be sent to the GUI
					output = "A busy customer has entered the bank.\n";
					update = "busy";
					tellerQueue.add(customer);
					System.out.println("Busy customer added to tellerQueue.");
				} else {
					// generates a random number between 270 and 330... to be
					// used with the customer's tolerance time
					int tolerance = (int) (parHandle.get("meanRegCustWaitTolerance") + customerGenerator.nextGaussian()	* parHandle.get("varRegCustWaitTolerance"));
					int positiveTolerance = java.lang.Math.abs(tolerance);// makes it always positive.

					// determines the account action (Deposit/ withdraw)
					float randomAction = customerGenerator.nextFloat();
					busyAction = false; // default to withdraw
					if (randomAction < (parHandle.get("depositPercentage") / 100F))
						busyAction = true; // true == deposit

					// creates the customer
					RegularCustomer customer = new RegularCustomer(positiveTolerance,busyAction, tellerQueue);
					customer.addObserver(gui);
					System.out.println("Reg customer generated.");
					clock.register(customer);
					// allows the update to be sent to the GUI
					output = "A regular customer has entered the bank.\n";
					update = "regular";
					tellerQueue.add(customer);
					System.out.println("Reg customer added to tellerQueue.");
				}
				nextTime = 0;
				setChanged();
				notifyObservers(output);
				return true;
			} else {
				return false;
			}
		}
	}

	public String getUpdate() {
		String temp = update;
		update = "";
		return temp;
	}

	//public boolean hasGenerated() {
	//	return hasGenerated;
	//}
}