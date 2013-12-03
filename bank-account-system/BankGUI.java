package bank;
/**
 * This class creates the Main GUI for the program. It also has action listeners
 * for the jmenubar and implements other classes according to the user input.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class BankGUI extends JFrame implements ActionListener, MouseListener
{
	private JFrame frame;
	// items for File
	private JMenuItem New, open, save, exit;
	// items for Account
	private JMenuItem add, remove;
	// items for Transactions
	private JMenuItem deposit, withdraw, charge, payment;
	// items for Search
	private JMenuItem customer, overdrawn;
	// submenus for account
	private JMenu account;
	private JMenuItem checking, savings, credit;
	// items for Reports
	private JMenuItem accountActivity;
	// items for Help
	private JMenuItem about;
	
	private JList accountsList;
	//The text area that gets updated
	private JTextArea south;
	
	private BankEngine engine;
	// these create the dialog for adding/removing
	private AddAccountDialog dialog;
	private RemoveAccountDialog rmDialog;
	
	@SuppressWarnings("unused")
	private WithdrawDialogBox withdrawMoney;
	@SuppressWarnings("unused")
	private DepositDialogBox depositMoney;
	@SuppressWarnings("unused")
	private ChargeDialogBox chargeMoney;
	@SuppressWarnings("unused")
	private PaymentDialogBox payMoney;
	@SuppressWarnings("unused")
	private AccountActivityDialog activity;
	
	@SuppressWarnings("unused")
	private SearchChecking checkingBox;
	
	private Account selectedAccount;
	/********************************************************************************
    This constructor creates the basic GUI for the entire program
    ********************************************************************************/
	public BankGUI(String[] args, BankEngine engine)
	{
		this.engine = engine;
		engine.setGUI(this);
		frame = new JFrame();
		frame.setTitle("LBS - Lakers Bank System");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2,0));
		frame.setResizable(false);
		
		JMenuBar bar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		New = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		file.add(New);
		file.add(open);
		file.add(save);
		file.add(exit);
		
		New.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);
		
		JMenu accounts = new JMenu("Accounts");
		add = new JMenuItem("Add Account");
		remove = new JMenuItem("Remove Account");
		accounts.add(add);
		accounts.add(remove);
		
		add.addActionListener(this);
		remove.addActionListener(this);
		
		JMenu transactions = new JMenu("Transactions");
		deposit = new JMenuItem("Deposit");
		withdraw = new JMenuItem("Withdraw");
		charge = new JMenuItem("Charge");
		payment = new JMenuItem("Payment");
		
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		charge.addActionListener(this);
		payment.addActionListener(this);
		
		transactions.add(deposit);
		transactions.add(withdraw);
		transactions.add(charge);
		transactions.add(payment);

		JMenu search = new JMenu("Search");
		account = new JMenu("Accounts By Type");
			checking = new JMenuItem("Checking");
			checking.addActionListener(this);
			savings = new JMenuItem("Savings");
			savings.addActionListener(this);
			credit = new JMenuItem("Credit");
			credit.addActionListener(this);
		customer = new JMenuItem("Accounts By Customer");
		customer.addActionListener(this);
		overdrawn = new JMenuItem("Overdrawn Accounts");
		overdrawn.addActionListener(this);
			account.add(checking);
			account.add(savings);
			account.add(credit);
		search.add(account);
		search.add(customer);
		search.add(overdrawn);
		
		
		JMenu reports = new JMenu("Reports");
		accountActivity = new JMenuItem ("Account Activity");
		accountActivity.addActionListener(this);
		reports.add(accountActivity);
		
		JMenu help = new JMenu("Help");
		about = new JMenuItem("About");
		help.add(about);
		about.addActionListener(this);
		
		bar.add(file);
		bar.add(accounts);
		bar.add(transactions);
		bar.add(search);
		bar.add(reports);
		bar.add(help);
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		//Creates the areas that the information is displayed in.
		//-------------------------------------------------------------------------------------------------------------------------------------------------
		accountsList = new JList(engine);
		accountsList.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Accounts", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		// adds the data to the view
		getContentPane().add(accountsList);
		accountsList.addMouseListener(this);
		
		JScrollPane x = new JScrollPane(accountsList);
		getContentPane().add(x);
		
		south = new JTextArea("", 15, 45);
		south.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Account/Customer Details", 0, 0, Font.getFont("Times New Roman"), Color.BLUE));
		south.setEditable(false);
		
		accountsList.setBackground(Color.WHITE);
		south.setBackground(Color.WHITE);
		
		frame.add(accountsList);
		frame.add(south);
		
		frame.setJMenuBar(bar);
		frame.pack();
		frame.setVisible(true);
	}

//------------------------------------------------------------------------------------------------------------------------------
//This has all the action listeners of the Menu Items
//------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the File menu
		//------------------------------------------------------------------------------------------------------------------------------
		if (New == (JMenuItem)arg0.getSource())
		{
			//This should describe what happens when the New button is clicked
			//Creates a new Database file and saves it to a location on the HDD. Also loads it.

	        int a = JOptionPane.showConfirmDialog(null, "Do you want to save before creating\na new file?", "New", JOptionPane.YES_NO_OPTION);
		
			if (a == JOptionPane.YES_OPTION)
			{
					JFileChooser chooser = new JFileChooser();
					//Should serialize the file.
					 int result = chooser.showSaveDialog(null);
				        if (result == JFileChooser.APPROVE_OPTION)
				        {
				            String path = chooser.getSelectedFile().getAbsolutePath();
				            System.out.println("Saving to: " + path);
				            engine.save(path);
				        }
			        engine = new BankEngine();// prompt them later to save
					accountsList.setModel(engine);
					south.setText("");
					accountsList.clearSelection();
					
				}
				if (a == JOptionPane.NO_OPTION)// 1 is no
				{
					engine = new BankEngine();
					engine.clearCustomer();
					accountsList.setModel(engine);
					south.setText("");
					accountsList.clearSelection();
				}
				
			
		}
		if (open == (JMenuItem)arg0.getSource())
		{
			//Use the JfileChooser. 
			JFileChooser chooser = new JFileChooser();
			// show chooser and get the user's action
	        int result = chooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION)
	        {
	        	engine.clearCustomer();
	            String path = chooser.getSelectedFile().getAbsolutePath();
	            engine.open(path);
	            south.setText("");
	            accountsList.clearSelection();
	            
	        }
			
		}
		if (save == (JMenuItem)arg0.getSource())
		{
			//Use the JfileChooser. 
			JFileChooser chooser = new JFileChooser();
			//Should serialize the file.
			 int result = chooser.showSaveDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION)
		        {
		            String path = chooser.getSelectedFile().getAbsolutePath();
		            engine.save(path);
		        }
		}
		if (exit == (JMenuItem)arg0.getSource())
		{
			// create a JOptionPane that asks if they want to exit.
			engine.exit();
		}
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the Accounts menu
		//------------------------------------------------------------------------------------------------------------------------------
		if (add == (JMenuItem)arg0.getSource())
		{
			dialog = new AddAccountDialog((frame), engine);
			dialog.setVisible(true); 
			south.setText("");
			accountsList.clearSelection();
		}
		if (remove == (JMenuItem)arg0.getSource())
		{
			rmDialog = new RemoveAccountDialog((frame), engine);
			rmDialog.setVisible(true);
			south.setText("");
			accountsList.clearSelection();
		}
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the Transactions menu
		//------------------------------------------------------------------------------------------------------------------------------
		if (deposit == (JMenuItem)arg0.getSource())
		{
			if (selectedAccount != null)
			{
				if (selectedAccount.getType().equals("CheckingAccount") || selectedAccount.getType().equals("SavingsAccount"))
				{
					depositMoney = new DepositDialogBox(selectedAccount, engine, frame);
				}
			}
		}
		if (withdraw == (JMenuItem)arg0.getSource())
		{
			if (selectedAccount != null)
			{
				if (selectedAccount.getType().equals("CheckingAccount") || selectedAccount.getType().equals("SavingsAccount"))
				{
					withdrawMoney = new WithdrawDialogBox(selectedAccount, engine, frame);
				}
			}
		}
		if (charge == (JMenuItem)arg0.getSource())
		{
			if (selectedAccount != null)
			{
				if (selectedAccount.getType().equals("CreditAccount"))
				{
					chargeMoney = new ChargeDialogBox(selectedAccount, engine, frame);	
				}
			}
		}
		if (payment == (JMenuItem)arg0.getSource())
		{
			if (selectedAccount != null)
			{
				if (selectedAccount.getType().equals("CreditAccount"))
				{
					payMoney = new PaymentDialogBox(selectedAccount, engine, frame);
				}
			}	
		}
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the Search menu
		//------------------------------------------------------------------------------------------------------------------------------
		if (checking == (JMenuItem)arg0.getSource())
		{
			checkingBox = new SearchChecking(frame, engine);
		}
		if (savings == (JMenuItem)arg0.getSource())
		{
			@SuppressWarnings("unused")
			SearchSavings  savingsBox = new SearchSavings(frame, engine);
		}
		if (credit == (JMenuItem)arg0.getSource())
		{
			@SuppressWarnings("unused")
			SearchCredit creditBox = new SearchCredit(frame, engine);
		}
		if (customer == (JMenuItem)arg0.getSource())
		{
			@SuppressWarnings("unused")
			SearchCustomer customerBox = new SearchCustomer(frame, engine);
		}
		
		if (overdrawn == (JMenuItem)arg0.getSource())
		{
			@SuppressWarnings("unused")
			SearchOverdrawn overdrawnBox = new SearchOverdrawn(frame, engine);
		}
		
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the Report menu
		//------------------------------------------------------------------------------------------------------------------------------
		
		if (accountActivity == (JMenuItem)arg0.getSource())
		{
			if (selectedAccount != null)
			{
				activity = new AccountActivityDialog(selectedAccount, frame);
			}
		}
		//------------------------------------------------------------------------------------------------------------------------------
		//Contains the Action Performed for the About menu
		//------------------------------------------------------------------------------------------------------------------------------
		if (about == (JMenuItem)arg0.getSource())
		{
			//prints out a small list of data regarding the programmer and the functionality of the program.
		JOptionPane.showMessageDialog(frame, "Programmed By: Jacob A. Coddaire\nClass: CIS 163 \nProgram: Laker Bank System\n"+
		"This program allows a user to create various bank accounts and store the data. \n" +
		"It is probably the least secure electronic system created, so don't ACTUALLY put real data in it.\n"+
		"To create a new account, go to Accounts>Add Accounts.\n"+
		"To remove an account, go to Accounts>Remove Accounts.\n"+
		"To perform any and all transaction types, go to Transactions and all the options are listed there.\n"+
		"To perform a search, use the search button and specify what it is you are searching for.\n"+
		"To print a report of recent activity, go to Reports>Account Activity.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if (arg0.getSource() == accountsList)
		{
			int selectedItem = accountsList.locationToIndex(arg0.getPoint());
			
			if (selectedItem >=0)
			{
				// gets the selected item
				selectedAccount = (Account) engine.getElementAt(selectedItem);
				// we want to update south, the JTextArea, with the proper information
				south.setText(selectedAccount.details());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void guiChargeError()
	{
		JOptionPane.showMessageDialog(null, "You have exceeded the credit limit.\nThe transaction failed.");
	}
	public void guiDepositError()
	{
		JOptionPane.showMessageDialog(null, "You cannot withdraw more than you have.\nThe transaction failed.");
		return;
	}
}