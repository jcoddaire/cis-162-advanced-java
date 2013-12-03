package bank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

/**
 * This class creates the Payment dialog box.
 * 
 * Jacob Coddaire
 * CIS 163
 */
public class PaymentDialogBox extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel buttons;
	
	private JLabel account, transactionLabel, date, amount, format;
	private JTextField dateText, amountText;
	
	private JButton ok, cancel;
	
	private Account act;
	private double amountEntered;
	private BankEngine engine;
	
	private TransactionType type;
	/*********************************************************************************************
    This constructor creates the basic GUI for the Payment Dialog box and adds the functionality
    *********************************************************************************************/
	public PaymentDialogBox(Account act, BankEngine engine, JFrame frame)
	{	
		super(frame, "Account Transaction", true);
		this.act=act;
		this.engine=engine;
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,5));
		
		account = new JLabel("Account: "+act.getNumber());
		transactionLabel = new JLabel("Transaction: PAYMENT");
		date = new JLabel("Date:");
		amount = new JLabel("Amount:");
		format = new JLabel("(mm/dd/yyyy)");
		
		dateText = new JTextField();
		amountText = new JTextField();
		
		buttons = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		panel.add(account);
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(transactionLabel);
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(date);
		panel.add(dateText);
		panel.add(format);
		panel.add(amount);
		panel.add(amountText);
		panel.add(new JLabel());
		
		buttons.add(ok);
		buttons.add(cancel);
		
		add(panel, BorderLayout.NORTH);
		add(buttons, BorderLayout.SOUTH);
		pack();
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (cancel == e.getSource())
		{
			dispose();
		}
		if (ok == e.getSource())
		{
			if (dateText != null && amountText != null)
			{
				// handles the date portion
				String date = dateText.getText();
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        		java.util.Date df = null;
				try 
				{
					df = format.parse(date);
				}
				catch (ParseException g) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid date. Please enter a correct date\nin the format mm/dd/yyyy.",
						    "Date Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
        		GregorianCalendar sDate = new GregorianCalendar();
        		sDate.setTime(df);
        	
				// handles the amount portion
				String sAmount = amountText.getText();
				try
				{
					amountEntered = Double.parseDouble(sAmount);
				}
				catch(NumberFormatException f)
				{
					JOptionPane.showMessageDialog(null, "You entered an incorrect amount.\nPlease enter a dollar amount.");
					return;
				}
					if (act.getBalance() - amountEntered < 0)
					{
						engine.chargeError();
						return;
					}
					else
					{
						engine.withdraw(amountEntered, act);
						type = TransactionType.PAYMENT;
						Transaction trans = new Transaction(type, sDate, amountEntered);
						act.transactions(trans);
						JOptionPane.showMessageDialog(null, "The amount of "+ amountEntered+ " was sucessfully paid.");
					}
					
			}
			dispose();
		}
		
	}

}