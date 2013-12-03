package myFiles;
/*
 * This class handles the main GUI of the program.
 * 
 * Jacob A. Coddaire
 * CIS 163-07
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import time.Clock;
import time.ClockListener;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class GUI implements ActionListener, ChangeListener, Observer, ClockListener{

	private JFrame frame;
	
	private int tellerQueueLengthCount = 0;
	private int tellerQueueLengthTotal = 0;
	private double tellerQueueLengthAvg = 0;
	private int tellerQueueLengthMax = 0;
	
	private int tellerQueueWaitCount = 0;
	private int tellerQueueWaitTotal = 0;
	private double tellerQueueWaitAvg = 0;
	private int tellerQueueWaitMax = 0;
	
	private int serviceQueueWaitCount = 0;
	private int serviceQueueWaitTotal = 0;
	private double serviceQueueWaitAvg = 0;
	private int serviceQueueWaitMax = 0;
	
	private int serviceQueueLengthCount = 0;
	private int serviceQueueLengthTotal = 0;
	private double serviceQueueLengthAvg = 0;
	private int serviceQueueLengthMax = 0;
	
	private JPanel customerPanel;
	private JLabel newCustomer;
	private JLabel angryCustomer;
	private JLabel busyCustomer;
	private JLabel regularCustomer;
	private JLabel upsetCustomer;
	
	private JTextArea newText;
	private JTextArea angryText;
	private JTextArea busyText;
	private JTextArea regularText;
	private JTextArea upsetText;
	
	private int newCount = 0;
	private int angryCount = 0;
	private int busyCount = 0;
	private int regularCount = 0;
	private int upsetCount = 0;
	
	private JPanel buttons;
	private JButton step;
	private JButton next;
	private JButton run;
	private JButton pause;
	
	private JPanel sliders;
	private JSlider simulation;
	private JSlider arrival;
	
	private static final int sliderMin = 1;
	private static final int sliderMax = 50;
	private static final int sliderMid = 25; 
	
	private JTextArea normalOutput;
	private JTextArea error;
	// contains the service/ teller GUI
	private JPanel serviceTeller;
	
	private JPanel serviceQueue;
	private JPanel tellerQueuePanel;
	
	private JLabel serviceQueueLabelLength;
	private JLabel tellerQueueLabelLength;
	private JTextArea serviceQueueLengthTime;
	private JTextArea tellerQueueLengthTime;
	
	private JLabel serviceQueueWait;
	private JLabel tellerQueueWait;
	private JTextArea serviceQueueWaitTime;
	private JTextArea tellerQueueWaitTime;
	
	private CustomerGenerator generator;
	private CustomerService service;
	
	private Clock clock;
	private ParameterHandler parHandle;
	
	private LinkedList<Customer> customerQueue;
	private LinkedList<Customer> tellerQueue;
	
	private pieChart piechart;
	/********************************************************************************
    This constructor creates the basic GUI for the entire program
    ********************************************************************************/
	public GUI(Clock clock, ParameterHandler parHandle, CustomerService service, LinkedList<Customer> customerQueue, LinkedList<Customer> tellerQueue)
	{
		this.clock = clock;
		clock.register(this);
		this.parHandle = parHandle;
		this.service = service;
		this.customerQueue=customerQueue;
		this.tellerQueue=tellerQueue;
		
		piechart = new pieChart();
		
		frame = new JFrame();
		frame.setTitle("Bank Simulation");
		frame.setLayout(new GridLayout(7,1));
		
		customerPanel = new JPanel();
		customerPanel.setLayout(new GridLayout(5,2));
		
		/********************************************************************************
	    Contains the Labels and the text areas that should be updated as the program runs
	    ********************************************************************************/
		newCustomer = new JLabel("New Customers");
		newCustomer.setForeground(Color.GREEN);
		angryCustomer = new JLabel ("Unhappy Customers");
		angryCustomer.setForeground(Color.RED);
		busyCustomer = new JLabel("Busy Customers");
		busyCustomer.setForeground(Color.ORANGE);
		regularCustomer = new JLabel ("Regular Customers");
		regularCustomer.setForeground(Color.BLUE);
		upsetCustomer = new JLabel ("Upset Customers");
		
		newText = new JTextArea("0");
		newText.setEditable(false);
		newText.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		angryText = new JTextArea("0");
		angryText.setEditable(false);
		angryText.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		busyText = new JTextArea("0");
		busyText.setEditable(false);
		busyText.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		regularText = new JTextArea("0");
		regularText.setEditable(false);
		regularText.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		upsetText = new JTextArea("0");
		upsetText.setEditable(false);
		upsetText.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		
		customerPanel.add(newCustomer);
		customerPanel.add(newText);
		customerPanel.add(angryCustomer);
		customerPanel.add(angryText);
		customerPanel.add(busyCustomer);
		customerPanel.add(busyText);
		customerPanel.add(regularCustomer);
		customerPanel.add(regularText);
		customerPanel.add(upsetCustomer);
		customerPanel.add(upsetText);
		
		/********************************************************************************
	    Contains the four buttons for the program and the Pie Chart
	    ********************************************************************************/
		buttons = new JPanel();
		step = new JButton("Step");
		next = new JButton("Next");
		run = new JButton("Run");
		pause = new JButton("Pause");
		pause.setEnabled(false);
		
		step.addActionListener(this);
		next.addActionListener(this);
		run.addActionListener(this);
		pause.addActionListener(this);
		
		buttons.add(step);
		buttons.add(next);
		buttons.add(run);
		buttons.add(pause);
		
		/********************************************************************************
	    Contains the slider bars for the program
	    ********************************************************************************/
		sliders = new JPanel();
		sliders.setLayout(new GridLayout(1,2));
		
		simulation = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderMid);
		simulation.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Simulation Speed", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		
		// makes the slow/fast text visible
		Hashtable<Integer, JLabel> simulationTable = new Hashtable<Integer, JLabel>();
		simulationTable.put(new Integer(sliderMin), new JLabel("Slow"));
		simulationTable.put(new Integer(sliderMax), new JLabel("Fast"));
		
		simulation.setLabelTable(simulationTable);
		simulation.setPaintLabels(true);
		
		simulation.addChangeListener(this);
		
		arrival = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderMid);
		arrival.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Inter Arrival", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		
		// makes the numbers visible
		arrival.setMajorTickSpacing(5);
		arrival.setMinorTickSpacing(1);
		arrival.setPaintTicks(true);
		arrival.setPaintLabels(true);
		
		arrival.addChangeListener(this);
		
		sliders.add(simulation);
		sliders.add(arrival);
		
		/********************************************************************************
	    Contains the main two text boxes that display the information
	    ********************************************************************************/
		normalOutput = new JTextArea(4,40);
		normalOutput.setEditable(false);
		error = new JTextArea(2,40);
		error.setEditable(false);
		
		JScrollPane nor = new JScrollPane();
		JScrollPane err = new JScrollPane();
		
		nor.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Output", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		err.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Errors", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		
		nor.setViewportView(normalOutput);
		err.setViewportView(error);
		
		/********************************************************************************
	    Contains the teller/service queue information
	    ********************************************************************************/
		serviceTeller = new JPanel();
		serviceTeller.setLayout(new GridLayout (1,2));
		
		serviceQueue = new JPanel();
		serviceQueue.setLayout(new GridLayout (2,2));
		serviceQueue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Customer Service Queue", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		
		tellerQueuePanel = new JPanel();
		tellerQueuePanel.setLayout(new GridLayout (2,2));
		tellerQueuePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Teller Queue", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		
		
		serviceQueueLabelLength = new JLabel("Length (avg/max): ");
		tellerQueueLabelLength =  new JLabel("Length (avg/max): ");
		serviceQueueLengthTime = new JTextArea("0/0");
		serviceQueueLengthTime.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		tellerQueueLengthTime = new JTextArea("0/0");
		tellerQueueLengthTime.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		
		serviceQueueWait = new JLabel("Wait Time (avg/max): ");
		tellerQueueWait = new JLabel ("Wait Time (avg/max): ");
		serviceQueueWaitTime = new JTextArea("0/0");
		serviceQueueWaitTime.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		tellerQueueWaitTime = new JTextArea("0/0");
		tellerQueueWaitTime.setBackground(Color.getHSBColor(0, 0, (float)0.93));
		
		serviceQueue.add(serviceQueueLabelLength);
		serviceQueue.add(serviceQueueLengthTime);
		serviceQueue.add(serviceQueueWait);
		serviceQueue.add(serviceQueueWaitTime);
		
		tellerQueuePanel.add(tellerQueueLabelLength);
		tellerQueuePanel.add(tellerQueueLengthTime);
		tellerQueuePanel.add(tellerQueueWait);
		tellerQueuePanel.add(tellerQueueWaitTime);
		
		serviceTeller.add(serviceQueue);
		serviceTeller.add(tellerQueuePanel);
		
		frame.add(nor);
		frame.add(err);
		frame.add(customerPanel);
		frame.add(buttons);
		// should create the piechart. For some reason, it doesn't appear. :/
		frame.setBounds(30,30,50,50);
		frame.getContentPane().add(piechart);
		frame.add(serviceTeller);
		frame.add(sliders);
	
		frame.setVisible(true);
		frame.pack();
	}
	
	public void setGenerator(CustomerGenerator generator){
		this.generator = generator;
	}
	/********************************************************************************
    Contains the Action Listeners
    ********************************************************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == run)
		{
			clock.run(Clock.FOREVER);
			pause.setEnabled(true);
			run.setEnabled(false);
			step.setEnabled(false);
			next.setEnabled(false);
		}
		if (arg0.getSource() == pause)
		{
			clock.pause();
			pause.setEnabled(false);
			run.setEnabled(true);
			step.setEnabled(true);
			next.setEnabled(true);
		}
		if (arg0.getSource() == step)
		{
			clock.step();
		}
		if (arg0.getSource() == next)
		{
			while (!clock.step()){}
		}
	}

	// This method is called by the JVM and updates the GUI when the model / engine does something important.
	public void update (Observable s, Object parameter)
	{	
		/********************************************************************************
    	Updates the main GUI box
		 ********************************************************************************/
		if (s instanceof Teller)
		{
			Teller t = (Teller) s;
			int lastWaited = t.getWaitTime();
			tellerQueueWaitTotal += lastWaited;
			tellerQueueWaitCount++;
			if (tellerQueueWaitMax < lastWaited)
				tellerQueueWaitMax = lastWaited;
			tellerQueueWaitAvg = tellerQueueWaitTotal/tellerQueueWaitCount;
			
			tellerQueueWaitTime.setText(tellerQueueWaitAvg + " / " + tellerQueueWaitMax);
			normalOutput.setText(normalOutput.getText() + t.getUpdate());
		}
		if (s instanceof CustomerService)
		{
			CustomerService cs = (CustomerService) s;
			int lastWaited = cs.getWaitTime();
			serviceQueueWaitTotal += lastWaited;
			serviceQueueWaitCount++;
			if (serviceQueueWaitMax < lastWaited)
				serviceQueueWaitMax = lastWaited;
			serviceQueueWaitAvg = serviceQueueWaitTotal/serviceQueueWaitCount;
			
			
			serviceQueueWaitTime.setText(serviceQueueWaitAvg + " / " + serviceQueueWaitMax);
			normalOutput.setText(normalOutput.getText() + service.getDirection() + service.getTime());
		}
		
		/********************************************************************************
    	Updates the customer count error text area and the error box 
		 ********************************************************************************/
			if (s instanceof BusyCustomer) 
			{
				upsetCount++;
				String count = Integer.toString(upsetCount);
				upsetText.setText(count);
				String temp = "A Busy Customer has ran out of patience. He left the bank.\n";
				error.setText(error.getText() + temp);
				
			}
			if (s instanceof RegularCustomer) 
			{
				upsetCount++;
				String count = Integer.toString(upsetCount);
				upsetText.setText(count);
				String temp = "A Regular Customer has ran out of patience. He left the bank.\n";
				error.setText(error.getText() + temp);
			}
			/********************************************************************************
	    	Updates the customer count Text areas.
			 ********************************************************************************/
			if (s instanceof CustomerGenerator) 
			{
				String update = generator.getUpdate();
				if (update.equals("new"))
				{
					newCount++;
					String newCountString = Integer.toString(newCount);
					newText.setText(newCountString);
					String temp = "A new customer has entered the bank!\n";
					normalOutput.setText(normalOutput.getText() + temp);
				
					//piechart.repaint();
					//piechart.revalidate();
					piechart.paint(newCount, angryCount, regularCount, busyCount);
					
				}
				if (update.equals("angry"))
				{
					angryCount++;
					String angryCountString = Integer.toString(angryCount);
					angryText.setText(angryCountString);
					String temp = "An angry customer has entered the bank!\n";
					normalOutput.setText(normalOutput.getText() + temp);
					
					//piechart.repaint();
					//piechart.revalidate();
					piechart.paint(newCount, angryCount, regularCount, busyCount);
				}
				if (update.equals("busy"))
				{
					busyCount++;
					String busyCountString = Integer.toString(busyCount);
					busyText.setText(busyCountString);
					String temp = "A busy customer has entered the bank!\n";
					normalOutput.setText(normalOutput.getText() + temp);

					//piechart.repaint();
					//piechart.revalidate();
					piechart.paint(newCount, angryCount, regularCount, busyCount);
				}
				if (update.equals("regular"))
				{
					regularCount++;
					String regularCountString = Integer.toString(regularCount);
					regularText.setText(regularCountString);
					String test = "A regular customer has entered the bank!\n";
					normalOutput.setText(normalOutput.getText() + test);

					//piechart.repaint();
					//piechart.revalidate();
					piechart.paint(newCount, angryCount, regularCount, busyCount);
				}
				
			}
		}
		
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (source == arrival)
		{
			parHandle.put("meanCustomerArrivalTime", source.getValue());
		}
		else
		{
			double rate = (double)source.getValue()/(double)source.getMaximum(); // multiple fraction by max time? 
			if (rate > .50){
				clock.setRate((int)(1000-500*rate));
			}else if (rate == .50){
				clock.setRate(1000);
			}else{
				clock.setRate((int)(1000 + 1000*(1-rate)));
			}
		}
		
	}
	public int getNewCount()
	{
			return newCount;
	}
	public int getAngryCount()
	{
			return angryCount;
	}
	public int getBusyCount()
	{
			return busyCount;
	}
	public int getRegularCount()
	{
			return regularCount;
	}

	@Override
	public boolean event(int time) {
		// formats the numbers correctly
		String format = "#.##";
		DecimalFormat num = new DecimalFormat(format);
		
		// creates and sets the teller statistics
		int temp = tellerQueue.size();
		tellerQueueLengthTotal += temp;
		tellerQueueLengthCount++;
		if (temp > tellerQueueLengthMax)
			tellerQueueLengthMax = temp;
		tellerQueueLengthAvg = (double)tellerQueueLengthTotal/(double)tellerQueueLengthCount;
		
		tellerQueueLengthTime.setText(num.format(tellerQueueLengthAvg) + " / " + tellerQueueLengthMax);
		
		// creates and sets the CS statistics
		temp = customerQueue.size();
		serviceQueueLengthTotal += temp;
		serviceQueueLengthCount++;
		if (temp > serviceQueueLengthMax)
			serviceQueueLengthMax = temp;
		serviceQueueLengthAvg = (double)serviceQueueLengthTotal/(double)serviceQueueLengthCount;
		
		serviceQueueLengthTime.setText(num.format(serviceQueueLengthAvg) + " / " + serviceQueueLengthMax);
		
		return false;
	}

}
