package bank;
/**
 * This class will create the result dialog box, or rather the second dialog box 
 * for the Report menu. It displays the data in a JTextArea and has one option to quit.
 * 
 * Jacob A. Coddaire
 * CIS 163
 */
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AccountActivityResult extends JDialog implements ActionListener  {

	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame frame;
	private JPanel north;
	private JPanel south;
	
	private JTextArea information;
	
	private JButton OK;
	@SuppressWarnings("unused")
	private ArrayList <Transaction> resultList;
	private JScrollPane scroll;
	/********************************************************************************
    This constructor creates the basic GUI for the second Account activity dialog box
    ********************************************************************************/
	public AccountActivityResult(ArrayList <Transaction> resultList, JFrame frame, Account account)
	{
		super(frame, "Report Results", true);
		this.resultList=resultList;
		this.frame = frame;
		
		setLayout(new GridLayout(0,1));
		setSize(new Dimension(300,300));
		setResizable(false);
		
		north = new JPanel();
		information = new JTextArea();
		information.setEditable(false);
		
		//This adds the found results to the text area
		for (int i= 0; i<resultList.size(); i++)
		{
			Transaction trans = resultList.get(i);
			information.append(trans.transactionConverter());
		}
		
		scroll = new JScrollPane(information);
		
		north.add(scroll);
		
		south = new JPanel();
		OK = new JButton("OK");
		OK.addActionListener(this);
		south.add(OK);
		
		add(scroll);
		add(south);
		
		setVisible(true);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == OK)
		{
			dispose();
		}
		
	}
}
