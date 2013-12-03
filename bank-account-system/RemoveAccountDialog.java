package bank;
/**
 * This class is responsible for the Delete dialog box, as well as the functionality of it.
 * 
 * It works with the BankEngine class to remove accounts, and it checks to make sure the accounts meet the proper parameters.
 * 
 * Jacob Coddaire 
 * CIS 163
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveAccountDialog extends JDialog implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	private JPanel north;
	private JList list;
	
	private JPanel buttons;
	private JButton remove;
	private JButton cancel;
	
	private BankEngine engine;

	private Account selectedAccount;
	/*****************************************************************
    This constructor creates the basic GUI for the frame
    *****************************************************************/
	public RemoveAccountDialog(JFrame frame, BankEngine pEngine)
    {
		
        super(frame, true);
        engine = pEngine;
		
        setLayout(new BorderLayout());
        setTitle("Remove Account");
        
        north = new JPanel();
        list = new JList(engine);
		list.addMouseListener(this);
        north.add(list);
        
        buttons = new JPanel();
        remove = new JButton("Remove");
        remove.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        
        buttons.add(remove);
        buttons.add(cancel);
        
        add(north, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        getContentPane().add(list);
        pack();
    }
	
	public void actionPerformed (ActionEvent arg0) {
		if (arg0.getSource() == cancel)
		{
			dispose();
		}
		if (arg0.getSource() == remove)
		{
			// This should check if it has a selected item
			if (selectedAccount != null)
			{
				// check the account type
				if (selectedAccount.getType().equals("SavingsAccount") || selectedAccount.getType().equals("CheckingAccount"))
				{
					// then check if it meets the correct parameters
					if (selectedAccount.getBalance() == 0)
					{
						// Then delete the account.
						engine.remove(selectedAccount);
						JOptionPane.showMessageDialog(null, "The customer's account has been removed.");
					} else
					if (selectedAccount.getBalance() > 0)
					{
						engine.remove(selectedAccount);
						JOptionPane.showMessageDialog(null, "The customer's refund is " + selectedAccount.getBalance());
					}
				}
				if (selectedAccount.getType().equals("CreditAccount"))
				{
					if (selectedAccount.getBalance() == 0)
					{
						engine.remove(selectedAccount);
						JOptionPane.showMessageDialog(null, "The customer's account has been removed.");
					} else
					if (selectedAccount.getBalance() <= 0)
					{
						engine.remove(selectedAccount);
						JOptionPane.showMessageDialog(null, "The customer's refund is " + selectedAccount.getBalance());
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == list)
		{
			int selectedItem = list.locationToIndex(arg0.getPoint());
			
			if (selectedItem >=0)
			{
				// gets the selected item
				selectedAccount = (Account) engine.getElementAt(selectedItem);
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
}
