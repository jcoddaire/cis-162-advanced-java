package bank;
/**
 * This is the class that creates the Add Account dialog box. It also imports the data that is entered
 * and checks it for consistency. It then throws errors when appropriate and saves the data to objects.
 * After that, it ships it off to the Bankengine.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AddAccountDialog extends JDialog implements ActionListener
{
    private String Alabama = "Alabama";
    private String Alaska = "Alaska";
    private String Arizona = "Arizona";
    private String Arkansas = "Arkansas";
    private String California = "California";
    private String Colorado = "Colorado";
    private String Connecticut = "Connecticut";
    private String Delaware = "Deleware";
    private String Florida = "Florida";
    private String Georgia = "Georgia";
    private String Hawaii = "Hawaii";
    private String Idaho = "Idaho";
    private String Illinois = "Illinois";
    private String Indiana = "Indiana";
    private String Iowa = "Iowa";
    private String Kansas = "Kansas";
    private String Kentucky = "Kentucky";
    private String Louisiana = "Louisiana";
    private String Maine = "Maine";
    private String Maryland = "Maryland";
    private String Massachusetts = "Massachusetts";
    private String Michigan = "Michigan";
    private String Minnesota = "Minnesota";
    private String Mississippi = "Mississippi";
    private String Missouri = "Missouri";
    private String Montana = "Montana";
    private String Nebraska = "Nebraska";
    private String Nevada = "Nevada";
    private String NewHampshire = "New Hampshire";
    private String NewJersey = "New Jersey";
    private String NewMexico = "New Mexico";
    private String NewYork = "New York";
    private String NorthCarolina = "North Carolina";
    private String NorthDakota = "North Dakota";
    private String Ohio = "Ohio";
    private String Oklahoma = "Oklahoma";
    private String Oregon = "Oregon";
    private String Pennsylvania = "Pennsylvania";
    private String RhodeIsland = "Rhode Island";
    private String SouthCarolina = "South Carolina";
    private String SouthDakota = "South Dakota";
    private String Tennessee = "Tennessee";
    private String Texas = "Texas";
    private String Utah = "Utah";
    private String Vermont = "Vermont";
    private String Virginia = "Virginia";
    private String Washington = "Washington";
    private String WestVirginia = "West Virginia";
    private String Wisconsin = "Wisconsin";
    private String Wyoming = "Wyoming";
    	
    // The variables for the Customer Information section
    private JPanel customerInformation;
    private TitledBorder customerBorder;
    
    private JRadioButton exist;
    private JComboBox existBox;
    private JRadioButton newCustomer;
    
    private JLabel nameLOCK;
    private JLabel ssnLOCK;
    private JLabel addressLOCK;
    private JLabel cityLOCK;
    private JLabel zipLOCK;
    private JLabel phoneLOCK;
    
    private JTextField name;
    private JTextField ssn;
    private JTextField address;
    private JTextField city;
    private JTextField zip;
    private JTextField phone;
    
    private JLabel stateLOCK;
    private JComboBox state;
    
    // The variables for the Account Information
    private JPanel AccountInformation;
    private TitledBorder Account;
    
    private JLabel numberLOCK;
    private JTextField number;
    private JLabel dateLOCK;
    private JTextField date;
    private JLabel balanceLOCK;
    private JTextField balance;
    private JLabel typeLOCK;
    private JComboBox type;
    
    //private String checkingAccount = "Checking", savingsAccount = "Savings", creditAccount = "Credit";
    
    private String[] accountArray = {"Checking", "Savings", "Credit"};
    
    // The variables for the Checking Account Panel
    private JPanel checkingPanel;
    private TitledBorder checkingBorder;
    
    private String[] yesNo = {"Yes", "No"};
    
    private JLabel overdraftLOCK;
    private JComboBox overdraft;
    private JLabel limitLOCK;
    private JTextField limit;
    private JLabel feeLOCK;
    private JTextField fee;
    
    // The variables for the Savings Account Panel
    private JPanel savingsPanel;
    private TitledBorder savingsBorder;
    
    private JLabel minLOCK;
    private JTextField min;
    private JLabel intrestLOCK;
    private JTextField interest;
    
    // The variables for the Credit Account Panel
    private JPanel creditPanel;
    private TitledBorder creditBorder;
    
    private JLabel creditLimitLOCK;
    private JTextField creditLimit;
    private JLabel inRateLOCK;
    private JTextField inRate;
    
    // The variables for the button panel
    private JPanel buttons;
    private JButton addButton;
    private JButton cancel;
    
    private BankEngine engine;
    private Customer temp;
    /********************************************************************************
    This constructor creates the basic GUI for the Add Account Dialog box
    ********************************************************************************/
    public AddAccountDialog(JFrame frame, BankEngine pEngine)
    {
        super(frame, true);

        engine = pEngine;
        setLayout(new GridLayout(6,1));
        setTitle("Add Account");
        
        customerInformation = new JPanel();
        customerBorder = BorderFactory.createTitledBorder("Customer Information");
        customerInformation.setBorder(customerBorder);
        customerInformation.setLayout(new GridLayout(5,4));
        
        exist = new JRadioButton("Existing Customer");
        exist.addActionListener(this);
        existBox = new JComboBox();
        
        for (Customer c : engine.getOwners())
        	existBox.addItem(c);
        
        existBox.addActionListener(this);
        existBox.setEnabled(false);
        
        newCustomer = new JRadioButton("New Customer");
        newCustomer.addActionListener(this);
        
        nameLOCK = new JLabel("Name:");
        ssnLOCK = new JLabel("SSN:");
        addressLOCK = new JLabel("Address:");
        cityLOCK = new JLabel("City:");
        zipLOCK = new JLabel("Zip:");
        phoneLOCK = new JLabel("Phone:");
        
        name = new JTextField("");
        ssn = new JTextField("");
        address = new JTextField("");
        city = new JTextField("");
        zip = new JTextField("");
        phone = new JTextField("");
        
        name.setEnabled(false);
        ssn.setEnabled(false);
        address.setEnabled(false);
        city.setEnabled(false);
        zip.setEnabled(false);
        phone.setEnabled(false);
        
        name.addActionListener(this);
        ssn.addActionListener(this);
        address.addActionListener(this);
        city.addActionListener(this);
        zip.addActionListener(this);
        phone.addActionListener(this);
        
        stateLOCK = new JLabel("State:");
        state = new JComboBox();
        state.addActionListener(this);
        state.setEnabled(false);
        
        // adds all the state items to the bar. Objects are of type String
        state.addItem(Alabama);
        state.addItem(Alaska);
        state.addItem(Arizona);
        state.addItem(Arkansas);
        state.addItem(California);
        state.addItem(Colorado);
        state.addItem(Connecticut);
        state.addItem(Delaware);
        state.addItem(Florida);
        state.addItem(Georgia);
        state.addItem(Hawaii);
        state.addItem(Idaho);
        state.addItem(Illinois);
        state.addItem(Indiana);
        state.addItem(Iowa);
        state.addItem(Kansas);
        state.addItem(Kentucky);
        state.addItem(Louisiana);
        state.addItem(Maine);
        state.addItem(Maryland);
        state.addItem(Massachusetts);
        state.addItem(Michigan);
        state.addItem(Minnesota);
        state.addItem(Mississippi);
        state.addItem(Missouri);
        state.addItem(Montana);
        state.addItem(Nebraska);
        state.addItem(Nevada);
        state.addItem(NewHampshire);
        state.addItem(NewJersey);
        state.addItem(NewMexico);
        state.addItem(NewYork);
        state.addItem(NorthCarolina);
        state.addItem(NorthDakota);
        state.addItem(Ohio);
        state.addItem(Oklahoma);
        state.addItem(Oregon);
        state.addItem(Pennsylvania);
        state.addItem(RhodeIsland);
        state.addItem(SouthCarolina);
        state.addItem(SouthDakota);
        state.addItem(Tennessee);
        state.addItem(Texas);
        state.addItem(Utah);
        state.addItem(Vermont);
        state.addItem(Virginia);
        state.addItem(Washington);
        state.addItem(WestVirginia);
        state.addItem(Wisconsin);
        state.addItem(Wyoming);
        
        customerInformation.add(newCustomer);
        customerInformation.add(exist);
        customerInformation.add(existBox);
        customerInformation.add(new JLabel());
        customerInformation.add(nameLOCK);
        customerInformation.add(name);
        customerInformation.add(ssnLOCK);
        customerInformation.add(ssn);
        customerInformation.add(addressLOCK);
        customerInformation.add(address);
        customerInformation.add(cityLOCK);
        customerInformation.add(city);
        customerInformation.add(stateLOCK);
        customerInformation.add(state);
        customerInformation.add(zipLOCK);
        customerInformation.add(zip);
        customerInformation.add(phoneLOCK);
        customerInformation.add(phone);
        
        add(customerInformation);
        
        //-------------------------------------------------------------------------------------------------------------
        //This adds the Account Information box
        //-------------------------------------------------------------------------------------------------------------
        AccountInformation = new JPanel();
        
        Account = BorderFactory.createTitledBorder("Account Information");
        AccountInformation.setBorder(Account);
        AccountInformation.setLayout(new GridLayout(5,4));
       
        numberLOCK = new JLabel("Number:");
        number = new JTextField("");
        number.setEnabled(false);
        
        dateLOCK = new JLabel("Date (mm/dd/yyyy):");
        date = new JTextField("");
        date.setEnabled(false);
        
        balanceLOCK = new JLabel("Balance:");
        balance = new JTextField("");
        balance.setEnabled(false);
        
        typeLOCK = new JLabel("Account Type:");
        type = new JComboBox(accountArray);
        type.setEnabled(false);
        
       type.addActionListener(this);
        
        AccountInformation.add(numberLOCK);
        AccountInformation.add(number);
        AccountInformation.add(dateLOCK);
        AccountInformation.add(date);
        AccountInformation.add(balanceLOCK);
        AccountInformation.add(balance);
        AccountInformation.add(typeLOCK);
        AccountInformation.add(type);
        
        add(AccountInformation);
        
        //-------------------------------------------------------------------------------------------------------------
        //This adds the Checking Account box
        //-------------------------------------------------------------------------------------------------------------
        
        checkingPanel = new JPanel();
        checkingBorder = BorderFactory.createTitledBorder("Checking Account");
        checkingPanel.setBorder(checkingBorder);
        checkingPanel.setLayout(new GridLayout(5,2));
        
        overdraftLOCK = new JLabel("Overdraft Protection:");
        overdraft = new JComboBox(yesNo);
        overdraft.setEnabled(false);
        overdraft.addActionListener(this);
        
        limitLOCK = new JLabel("Overdraft Limit:");
        limit = new JTextField("");
        limit.setEnabled(false);
        
        feeLOCK = new JLabel("Overdraft Fee:");
        fee = new JTextField("");
        fee.setEnabled(false);
        
        checkingPanel.add(overdraftLOCK);
        checkingPanel.add(overdraft);
        checkingPanel.add(limitLOCK);
        checkingPanel.add(limit);
        checkingPanel.add(feeLOCK);
        checkingPanel.add(fee);
        
        add(checkingPanel);
        
      	//-------------------------------------------------------------------------------------------------------------
        //This adds the Savings Account box
        //-------------------------------------------------------------------------------------------------------------
        
        savingsPanel = new JPanel();
        savingsBorder = BorderFactory.createTitledBorder("Savings Account");
        savingsPanel.setBorder(savingsBorder);
        savingsPanel.setLayout(new GridLayout(5,2));
        
        minLOCK = new JLabel ("Minimum Balance:");
        min = new JTextField();
        min.setEnabled(false);
        
        intrestLOCK = new JLabel ("Interest Rate:");
        interest = new JTextField();
        interest.setEnabled(false);
        
        savingsPanel.add(minLOCK);
        savingsPanel.add(min);
        savingsPanel.add(intrestLOCK);
        savingsPanel.add(interest);
        savingsPanel.add(new JLabel());
        savingsPanel.add(new JLabel());
        
        add(savingsPanel);
        //-------------------------------------------------------------------------------------------------------------
        //This adds the Credit Account box
        //-------------------------------------------------------------------------------------------------------------
        
        creditPanel = new JPanel();
        creditBorder = BorderFactory.createTitledBorder("Credit Account");
        creditPanel.setBorder(creditBorder);
        creditPanel.setLayout(new GridLayout(5,2));
        
        creditLimitLOCK = new JLabel ("Credit Limit:");
        creditLimit = new JTextField();
        creditLimit.setEnabled(false);
        
        inRateLOCK = new JLabel ("Interest Rate:");
        inRate = new JTextField();
        inRate.setEnabled(false);
        
        creditPanel.add(creditLimitLOCK);
        creditPanel.add(creditLimit);
        creditPanel.add(inRateLOCK);
        creditPanel.add(inRate);
        creditPanel.add(new JLabel());
        creditPanel.add(new JLabel());
        
        add(creditPanel);
        
        //-------------------------------------------------------------------------------------------------------------
        //This adds the two JButtons
        //-------------------------------------------------------------------------------------------------------------
        
        buttons = new JPanel();
        
        
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        buttons.add(addButton);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(cancel);
        
        add(buttons);
        
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
    	if (arg0.getSource() == newCustomer)
    	{
    		
    		if(exist.isSelected() == true)
			{
				exist.setSelected(false);
				exist.setEnabled(true);
			}   			
    			existBox.setEnabled(false);
    			
    			newCustomer.setEnabled(false);
    			name.setEnabled(true);
    			ssn.setEnabled(true);
    			address.setEnabled(true);
    			city.setEnabled(true);
    			zip.setEnabled(true);
    			phone.setEnabled(true);
    			state.setEnabled(true);
    	    
    			number.setEnabled(true);
    			date.setEnabled(true);
    			balance.setEnabled(true);
    			type.setEnabled(true);
    			
    			overdraft.setEnabled(true);
    			limit.setEnabled(true);
    			fee.setEnabled(true);
    	}
    	if (arg0.getSource() == exist)
    	{
    		if(newCustomer.isSelected() == true)
			{
				newCustomer.setSelected(false);
				newCustomer.setEnabled(true);
			}

    			existBox.setEnabled(true);
    			exist.setEnabled(false);
    		
    			name.setEnabled(false);
    			ssn.setEnabled(false);
    			address.setEnabled(false);
    			city.setEnabled(false);
    			zip.setEnabled(false);
    			phone.setEnabled(false);
    			state.setEnabled(false);
    	    
    			number.setEnabled(true);
    			date.setEnabled(true);
    			balance.setEnabled(true);
    			type.setEnabled(true);
    			
    			overdraft.setEnabled(true);
    			limit.setEnabled(true);
    			fee.setEnabled(true);
    	}
    	if (arg0.getSource() == type)
    	{
    		type.getSelectedItem();
    		if(type.getSelectedItem() == accountArray[0])
    		{
    			overdraft.setEnabled(true);
    			limit.setEnabled(true);
    			fee.setEnabled(true);
    			
    			min.setEnabled(false);
    			interest.setEnabled(false);
    			
    			creditLimit.setEnabled(false);
    			inRate.setEnabled(false);
    			
    		}else if(type.getSelectedItem() == accountArray[1])
    		{
    			overdraft.setEnabled(false);
    			limit.setEnabled(false);
    			fee.setEnabled(false);
    			
    			min.setEnabled(true);
    			interest.setEnabled(true);
    			
    			creditLimit.setEnabled(false);
    			inRate.setEnabled(false);
    			
    		}else if(type.getSelectedItem() == accountArray[2])
    		{
    			overdraft.setEnabled(false);
    			limit.setEnabled(false);
    			fee.setEnabled(false);
    			
    			min.setEnabled(false);
    			interest.setEnabled(false);
    			
    			creditLimit.setEnabled(true);
    			inRate.setEnabled(true);
    		}
    	}
     if(arg0.getSource() == addButton)
     {
        if (newCustomer != null)
        {
        	if (newCustomer.isSelected()){
        		// adds the customer information
        		String sName = name.getText();
            	String sSSN = ssn.getText();
            	String sAddress = address.getText();
            	String sCity = city.getText();
            	String sState = (String)state.getSelectedItem();
            	String sZip = zip.getText();
            	String sPhone = phone.getText();
        		temp = new Customer(sName, sSSN, sAddress, sCity, sState, sZip, sPhone);
        	}else{
        		// adds the predefined customer
        		temp = (Customer)existBox.getSelectedItem();
        	}
        	//--------------------------------------------------------------------------------------------------
    		//Get Account Information
    		//--------------------------------------------------------------------------------------------------
    		String finalNumber = number.getText();
    		// checks if the account number is an int value
    		try
    		{
    			@SuppressWarnings("unused")
				int actualNumber = Integer.parseInt(finalNumber);
    		}
    		catch(NumberFormatException e)
    		{
    			JOptionPane.showMessageDialog(null,
					    "Invalid account number. Please enter a correct account number.",
					    "Account Number Error",
					    JOptionPane.ERROR_MESSAGE);
				
				return;
    		}
    		
    		String stringDate = date.getText().trim();
    		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
    		java.util.Date df = null;
			try 
			{
				df = format.parse(stringDate);
			}
			catch (ParseException e) 
			{
				JOptionPane.showMessageDialog(null,
					    "Invalid date. Please enter a correct date\nin the format mm/dd/yyyy.",
					    "Date Error",
					    JOptionPane.ERROR_MESSAGE);
				
				return;
			}
    		GregorianCalendar sDate = new GregorianCalendar();
    		sDate.setTime(df);
    		
    		String sBalance = balance.getText();
    		double actualBalance = 0;
    		try 
			{
				actualBalance = Double.parseDouble(sBalance);
			}
			catch (NumberFormatException e) 
			{
				JOptionPane.showMessageDialog(null,
					    "Invalid balance. Please enter a correct balance.",
					    "Balance Error",
					    JOptionPane.ERROR_MESSAGE);
				
				return;
			}
    		//adds the specified account
    		if (type.getSelectedItem().equals(accountArray[0]))
    		{
    			boolean odEnabled=true;
    			if(overdraft.getSelectedItem() == yesNo[0])
    			{
    				odEnabled = true;
    			}
    			if(overdraft.getSelectedItem() == yesNo[1])
    			{
    				odEnabled = false;
    			}
    			
    			String sLimit = limit.getText();
    			double actualLimit = 0;
    			try 
				{
					actualLimit = Double.parseDouble(sLimit);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid limit. Please enter a correct limit.",
						    "Limit Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
    			String sFee = fee.getText();
    			double actualFee = 0;
    			try 
				{
					actualFee = Double.parseDouble(sFee);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid fee. Please enter a correct fee.",
						    "Fee Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
    			CheckingAccount act = new CheckingAccount(temp, finalNumber, actualBalance, sDate, odEnabled, actualLimit, actualFee);
    			engine.add(act);
    		}
    		if (type.getSelectedItem().equals(accountArray[1]))
    		{
    			String sMin = min.getText();
    			double actualMin = 0;
    			try 
				{
					actualMin = Double.parseDouble(sMin);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid minimum. Please enter a correct minimum.",
						    "Minimum Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
    			String sInterest = interest.getText();
    			double actualInterest = 0;
    			try 
				{
					actualInterest = Double.parseDouble(sInterest);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid interest. Please enter a number.",
						    "Interest Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
    			SavingsAccount account = new SavingsAccount(temp, finalNumber, actualBalance, sDate, actualMin, actualInterest);
    			engine.add(account);
    		}
    		if (type.getSelectedItem().equals(accountArray[2]))
    		{
    			String sLimit = creditLimit.getText();
    			double actualLimit;
    			try 
				{
					actualLimit = Double.parseDouble(sLimit);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid limit. Please enter a correct credit limit.",
						    "Limit Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
    			String sRate = inRate.getText();
    			double actualRate;
    			try 
				{
					actualRate = Double.parseDouble(sRate);
				}
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid interest rate. Please enter a correct interest rate.",
						    "Interest Rate Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				CreditAccount craccount = new CreditAccount(temp, finalNumber, actualBalance, sDate, actualLimit, actualRate);
				engine.add(craccount);
				
    		}
        }
    	
    	dispose();
    	
        }
        if(cancel == arg0.getSource() )
        {
        	dispose();
        }
    }
    public Customer getTemp()
    {
    	return temp;
    }
}