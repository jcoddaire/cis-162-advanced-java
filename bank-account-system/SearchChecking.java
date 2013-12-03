package bank;
/**
 * This class has the GUI used for searching for checking accounts.
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SearchChecking extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JButton ok;
	private JPanel panel;
	/*********************************************************************************************
    This constructor creates the basic GUI for the results of searching for Checking Accounts
    *********************************************************************************************/
	public SearchChecking(JFrame frame, BankEngine engine)
	{
		super (frame, "Checking Search", true);
		setLayout (new GridLayout (2,1));
		
		area = new JTextArea("");
		area.setEditable(false);
		ok = new JButton("OK");
		ok.addActionListener(this);
		panel = new JPanel();
		ArrayList<Account> acts = engine.search(0);
		
		for (int i = 0; i < acts.size(); i++)
		{
			
			Date newDate = acts.get(i).getDate().getTime();
			SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
			String dateString = format.format(newDate);
			
			area.append("Number: " + acts.get(i).getNumber() + " Type: " + acts.get(i).getType() + " Date: " + dateString +
					" Balance: " + acts.get(i).getBalance() + " Name: " + acts.get(i).getOwner() + "\n");
		}
		panel.add(ok);
		add(area);
		add(panel);
		
		
		setResizable(false);
		setSize(600, 450);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent arg0) {
		if(ok == arg0.getSource())
		{
			dispose();
		}
		
	}
}
