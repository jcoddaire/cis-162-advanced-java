package bank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.*;

/**
 * This class creates the withdraw dialog box.
 * 
 * Jacob Coddaire
 * CIS 163
 */
@SuppressWarnings("serial")
public class WithdrawDialogBox extends JDialog implements ActionListener
{
	private JPanel panel;
	private JPanel buttons;
	
	private JLabel account, transaction, date, amount, format;
	private JTextField dateText, amountText;
	
	private JButton ok, cancel;
	
	private double amountEntered;
	private Account act;
	private BankEngine engine;
	
	private Transaction trans;
	private TransactionType type;
	
	/*********************************************************************************************
    This constructor creates the basic GUI for the Withdraw Dialog Box
    *********************************************************************************************/
	public WithdrawDialogBox(Account act, BankEngine engine, JFrame frame)
	{	

		super(frame, "Account Transaction", true);
		this.engine=engine;
		this.act=act;
		setLayout(new BorderLayout());
				
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,5));
		
		account = new JLabel("Account: " +act.getNumber());
		transaction = new JLabel("Transaction: WITHDRAW");
		date = new JLabel("Date:");
		amount = new JLabel("Amount:");
		format = new JLabel("(mm/dd/yyyy)");
		
		dateText = new JTextField();
		dateText.addActionListener(this);
		amountText = new JTextField();
		amountText.addActionListener(this);
		
		buttons = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		panel.add(account);
		panel.add(new JLabel());
		panel.add(new JLabel());
		panel.add(transaction);
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
				SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
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
					JOptionPane.showMessageDialog(null, "You entered an incorrect amount.\nPlease enter a dollar amount.\nBy the way, that was an epic fail.");
					return;
				}
				if (act.getType().equals("CheckingAccount"))
				{
					CheckingAccount cAccount = (CheckingAccount) act;
					if (cAccount.getBalance() - amountEntered < (cAccount.getLimit()*-1))
					{
						engine.chargeError();
						return;
					}
					else
					{	
						engine.withdraw(amountEntered, cAccount);
						type = TransactionType.WITHDRAW;
						trans = new Transaction(type, sDate, amountEntered);
						cAccount.transactions(trans);
					}
				}
				else
				{
					SavingsAccount sAccount = (SavingsAccount) act;
					if(sAccount.getBalance() - amountEntered < sAccount.getSavingsBalance())
					{
						engine.chargeError();
						return;
					}
					else
					{
						
						engine.withdraw(amountEntered, sAccount);
						type = TransactionType.WITHDRAW;
						trans = new Transaction(type, sDate, amountEntered);
						sAccount.transactions(trans);
					}
				}
				
			}
			dispose();
			JOptionPane.showMessageDialog(null, "The amount of "+ amountEntered+ " was sucessfully withdrawn.");
		}
	}
}