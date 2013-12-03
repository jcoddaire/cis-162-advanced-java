package bank;
/**
 * This class will create the account activity dialog box.
 * It then checks the dates that have been entered and proceeds to show the results.
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountActivityDialog extends JDialog implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel panel;
	
	private JLabel date1;
	private JTextField date1Text;
	private JLabel format;
	
	private JLabel date2;
	private JTextField date2Text;
	private JLabel format2;
	
	private JPanel buttons;
	private JButton ok;
	private JButton cancel;
	
	private Account account;
	@SuppressWarnings("unused")
	private AccountActivityResult result;
	private ArrayList <Transaction> resultList;
	/********************************************************************************
    This constructor creates the basic GUI for the first Account activity dialog box
    ********************************************************************************/
	public AccountActivityDialog(Account account, JFrame frame)
	{
		super(frame, "Report", true);
		this.account=account;
		this.frame=frame;
		//frame = new JFrame();
		this.setLayout(new BorderLayout());
		//this.setTitle("Report");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,3));
		
		date1 = new JLabel("Begin Date:");
		date2 = new JLabel("End Date:");
		format = new JLabel("(mm/dd/yyyy)");
		format2 = new JLabel ("(mm/dd/yyyy)");
		
		date1Text = new JTextField();
		date2Text = new JTextField();
		
		buttons = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		panel.add(date1);
		panel.add(date1Text);
		panel.add(format);
		panel.add(date2);
		panel.add(date2Text);
		panel.add(format2);
		
		buttons.add(ok);
		buttons.add(cancel);
		
		add(panel, BorderLayout.NORTH);
		add(buttons, BorderLayout.SOUTH);
		pack();
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cancel)
		{
			dispose();
		}
		if(arg0.getSource() == ok)
		{
			if (date1Text != null && date2Text != null)
			{
				// handles the date 1 portion
				String date1 = date1Text.getText();
				SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
				java.util.Date df1 = null;
				try 
				{
					df1 = format.parse(date1);
				}
				catch (ParseException g) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid beginning date. Please enter a correct date\nin the format mm/dd/yyyy.",
						    "Date Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
        		// handles the date 2 portion
        		String date2 = date2Text.getText();
        		java.util.Date df2 = null;
				try 
				{
					df2 = format.parse(date2);
				}
				catch (ParseException g) 
				{
					JOptionPane.showMessageDialog(null,
						    "Invalid end date. Please enter a correct date\nin the format mm/dd/yyyy.",
						    "Date Error",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}

				if (df1.compareTo(df2) > 0)
				{
					JOptionPane.showMessageDialog(null, "The dates you entered are out of range.\nPlease enter a new range.");
					return;
				}
				ArrayList <Transaction> transactions = account.getTransactions();
				resultList= new ArrayList <Transaction>();
				for (int i=0; i<transactions.size(); i++)
				{
					Transaction trans = transactions.get(i);
					GregorianCalendar g = trans.getDate();
					Date date = g.getTime();
					if (date.compareTo(df1) >= 0 && date.compareTo(df2) <=0)
					{
						resultList.add(trans);
					}
				}
				if (resultList.size() <= 0)
				{
					JOptionPane.showMessageDialog(null, "There are no valid account transactions for\nthe selected date range.");
					return;
				}
				else
				{
					dispose();
					result = new AccountActivityResult(resultList, frame, account);
				}
			}
			
		}
	}
}