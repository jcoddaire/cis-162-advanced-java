package bank;
/**
 * This class is responsible for all the logic that goes on in this project.
 * It organizes data, manipulates it according to each method, and ships it off to the GUI again.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;


public class BankEngine extends AbstractListModel
{

	/**
	 * This class should be the "Model" that does the logic.
	 * Jacob A. Coddaire
	 * CIS 163
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Account> accounts;
	private ArrayList<Customer> customers;
	private BankGUI bankGUI;
	
	/********************************************************************************
    This constructor creates the basic GUI for the first Account activity dialog box
    ********************************************************************************/
	public BankEngine()
	{
		accounts = new ArrayList<Account>();
		customers = new ArrayList<Customer>();
	}
	public ArrayList<Customer> clearCustomer()
	{
		customers = new ArrayList<Customer>();
		return customers;
	}
	public void setGUI(BankGUI g){
		bankGUI = g;
	}
	
	public int getSize()
	{
		return accounts.size();
	}
	@Override
	public Object getElementAt(int arg0) 
	{
		// TODO Auto-generated method stub
		return accounts.get(arg0);
	}
	/********************************************************************************
    This contains the methods for running everything in the File menu
    ********************************************************************************/
	// runs the open command
	@SuppressWarnings("unchecked")
	public void open(String filePath)
	{
			try
			{
				FileInputStream stream = new FileInputStream(filePath);
	            ObjectInputStream objectReader = new ObjectInputStream(stream);
				accounts = (ArrayList<Account>) objectReader.readObject();
				objectReader.close();
				//notify the JList that something has changed
				
				for (Account a : accounts){
					if (!customers.contains(a.getOwner()))
						customers.add(a.getOwner());
				}
				
				fireIntervalAdded(this,0 ,0);
			}
			catch(Exception e)
			{
				System.err.println("File input error");
			}
			
	}
	//  runs the save command
	public void save(String path)
	{
		    try 
		    {
		    	FileOutputStream stream = new FileOutputStream(path);
		    	ObjectOutputStream objectWriter = new ObjectOutputStream(stream);
		    	objectWriter.writeObject(accounts);
		    	objectWriter.close();
		    	stream.close();
		    }
		    catch(Exception e)
		    {
		    	System.err.println("File output error");
		    }
	}
	// runs the Exit command
	public int exit()
	{
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?", "Warning", JOptionPane.OK_CANCEL_OPTION);
		if (result == 0)
			System.exit(0);
		return result;
	}
	/********************************************************************************
    This runs the methods in the Account menu
    ********************************************************************************/
	public void add(Account act){
		// stores the data to the arraylist
		accounts.add(act);
		//notify the JList that the object has changed.
		if (!customers.contains(act.getOwner()))
			customers.add(act.getOwner());
		fireIntervalAdded(this, 0, accounts.size() -1);
	}

	public void remove(Account act)
	{
		// removes the data in the arraylist
		accounts.remove(act);
		// updates GUI
		fireIntervalRemoved(this, 0, accounts.size());
	}
	/********************************************************************************
    This runs the methods in the Transactions menu
    ********************************************************************************/
	public void deposit (double amount, Account act)
	{
		// this should update the balance associated with an account and send it to the GUI.
		act.depositInto(amount);
		if (amount >= 0)
		{
			fireContentsChanged(this, 0, accounts.size()-1);
		}
		else{
			bankGUI.guiDepositError();
		}
	}
	public void withdraw (double amount, Account act)
	{
		act.withdrawFrom(amount);
		fireContentsChanged (this, 0, accounts.size()-1);
	}
	
	public ArrayList<Customer> getOwners(){
		return customers;
	}
	public void chargeError()
	{
		bankGUI.guiChargeError();
	}
	
	/********************************************************************************
    This contains the method/logic for searching
    ********************************************************************************/
	public ArrayList <Account> search(int type)
	{
		ArrayList<Account> act = new ArrayList<Account>();
		for (int i=0; i<accounts.size(); i++)
		{
			// this compares the type and updates the GUI accordingly
			if (type == 0){
				if (accounts.get(i) instanceof CheckingAccount)
					act.add(accounts.get(i));
			}
			if (type == 1){
				if (accounts.get(i) instanceof SavingsAccount)
					act.add(accounts.get(i));
			}
			if (type == 2){
				if (accounts.get(i) instanceof CreditAccount)
					act.add(accounts.get(i));
			}
			if (type == 3)
			{
				if(accounts.get(i) instanceof CheckingAccount)
				{
					if (accounts.get(i).getBalance()<0)
						act.add(accounts.get(i));
				}
			}
		}
		return act;
	}
	public ArrayList<Account> customerSearch(String name)
	{
		ArrayList<Account> act = new ArrayList<Account>();
		for (int i=0; i<accounts.size(); i++)
		{
			if (name.equals(accounts.get(i).getOwner().getName()))
			{
				act.add(accounts.get(i));
			}
		}
		return act;
	}
}