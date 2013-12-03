package bank;
/**
 * This class creates the basic GUI for searching for all accounts for a specific customer.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class SearchCustomer extends JDialog implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel1, panel2, panel3;
	private JComboBox customerBox;
	private JButton ok;
	private JTextArea text;
	private BankEngine engine;
	private String shenanigans;
	/*********************************************************************************************
    This constructor creates the basic GUI for the results of searching for accounts by Customer
    *********************************************************************************************/
	public SearchCustomer(JFrame frame, BankEngine engine)
	{
		// this creates the GUI for the new dialog box
		super(frame, "Customer Search", true);
		setLayout(new GridLayout(3,1));
		
		this.engine=engine;
		
		panel1 = new JPanel();
		panel2= new JPanel();
		panel3 = new JPanel();
		
		customerBox = new JComboBox();
		// this addds the customers to the drop down box
		for (Customer c : engine.getOwners())
        	customerBox.addItem(c);
		customerBox.addItemListener(this);
		
		
		ok = new JButton("OK");
		ok.addActionListener(this);
		text = new JTextArea();
		text.setEditable(false);
		
		panel1.add(customerBox);
		panel2.add(text);
		panel3.add(ok);
		
		add(panel1);
		add(panel2);
		add(panel3);
		
		setSize(600, 400);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (ok == arg0.getSource())
		{
			dispose();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (customerBox == arg0.getSource())
		{
			text.setText("");
			shenanigans = ((Customer) customerBox.getSelectedItem()).getName();
			if (customerBox.getSelectedItem() != null)	
			{
				ArrayList<Account> account = engine.customerSearch(shenanigans);
				// For each account, get the information.
				{
					
					for (int i=0; i<account.size(); i++)
					{
						Date newDate = account.get(i).getDate().getTime();
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
						String dateString = format.format(newDate);
					
						text.append("Number: " + account.get(i).getNumber() + " Type: " + account.get(i).getType() + " Date: " + dateString +
								" Balance: " + account.get(i).getBalance() + " Name: " + account.get(i).getOwner() + "\n");	
					}		
				}
			}
		}
	}
}