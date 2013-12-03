package myFiles;

import java.util.LinkedList;
import time.Clock;

/*
 * This class is the Driver class, and it should run the entire program.
 * This class should test how the model components work together.
 * 
 * Specifically, the driver class should:
 * 		Instantiate the Master Clock... DONE
 * 		Instantiate the two queue's... DONE
 * 		Instantiate the customer Generator ... DONE
 * 			attach the two queues to it... DONE
 * 		instantiate the customer service and the tellers to incoming and outgoing queues
 * 		Set up all the components to receive clock ticks from the master clock.
 * 
 *  Jacob A. Coddaire
 *  CIS 163-07
 */
public class Driver {
	Clock clock;
	ParameterHandler parHandle;
	GUI gui;
	CustomerService service;
	CustomerGenerator generator;
	LinkedList<Customer> customerQueue;
	LinkedList<Customer> tellerQueue;
	
	public Driver(){
		
		clock = new Clock();
		
		// creates a new paramaterHandler
		parHandle = new ParameterHandler();
		
		customerQueue = new LinkedList<Customer>();
		tellerQueue = new LinkedList<Customer>();
		
		
		service = new CustomerService(customerQueue, tellerQueue, parHandle);
		// creates a new GUI
		gui = new GUI(clock, parHandle, service, customerQueue, tellerQueue);
		service.addObserver(gui);
		clock.register(service);
		
		// registers the tellers
		int tellers = parHandle.get("maxNumTellers");
		for (int i = 0; i < tellers; i++){
			Teller t = new Teller(tellerQueue, parHandle);
			t.addObserver(gui);
			clock.register(t);
		}
		
		// creates a new Customer Generator
		generator = new CustomerGenerator(parHandle, customerQueue, tellerQueue, clock, gui);
		generator.addObserver(gui);
		gui.setGenerator(generator);
		clock.register(generator);
		
		
	}
	
	public static void main (String [] args)
	{
		@SuppressWarnings("unused")
		Driver d = new Driver();
		
	}
}