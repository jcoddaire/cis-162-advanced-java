package myFiles;

import java.util.Observable;

import time.ClockListener;

/**
 *  This class should contain the main methods that all customers will have.
 *  It is the parent Customer class.
 *  The four customers it extends are
 *  	New
 *  	Dissatisfied
 *  	Busy
 *  	Regular
 *  Jacob A. Coddaire
 *  CIS 163-07
 *
 */
public abstract class Customer extends Observable implements ClockListener{
	
	private int waited;
	private int waitTolerance;

	// this class is required to provide the event() method.
	// this class is also required to provide the methods/constructor that all customers will use.
	
	public Customer()
	{
		setWaitTolerance(-1);
		setWaited(0);

	}

	public void setWaited(int waited) {
		this.waited = waited;
	}


	public int getWaited() {
		return waited;
	}
	
	public void setWaitTolerance(int waitTolerance) {
		this.waitTolerance = waitTolerance;
	}
	public int getWaitTolerance() {
		return waitTolerance;
	}
}
