package examples.abstractListModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Main program window for demo
 * 
 * @author gerberma
 * 
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, MouseListener
{
    /**
     * Set up simple GUI
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // run program
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 300);
        gui.setVisible(true);
    }

    private Phonebook _phonebook;
    private JMenuItem _addEntryMI;
    private JList _entryList;

    /**
     * Constructor
     */
    public GUI()
    {
        // set up menu items     
        _addEntryMI = new JMenuItem("Add entry...");
        _addEntryMI.addActionListener(this);

        // set up menu
        JMenu phonebookMenu = new JMenu("Phonebook");
        phonebookMenu.add(_addEntryMI);

        // set up menubar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(phonebookMenu);
        setJMenuBar(menuBar);

        // create phonebook model
        _phonebook = new Phonebook();

        // create list with phonebook model
        _entryList = new JList(_phonebook);
        _entryList.addMouseListener(this);

        // create scroll pane that contains the entry list...the list will now scroll when needed
        JScrollPane scrollPane = new JScrollPane(_entryList);

        // divide content pane in half, with top being the jlist and bottom being a text area
        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().add(scrollPane);
        getContentPane().add(new JTextArea("Hello world"));
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == _addEntryMI)
            // add the same entry every time - this is just for demonstration purposes
            _phonebook.add(new Entry("Matt", "Gerber", 1231231234));
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        if (arg0.getSource() == _entryList)
        {
            // get index the user clicked on
            int selectedIndex = _entryList.locationToIndex(arg0.getPoint());

            // if they clicked a valid entry...
            if (selectedIndex >= 0)
            {
                // get the entry and display it
                Entry selectedEntry = (Entry) _phonebook.getElementAt(selectedIndex);
                JOptionPane.showMessageDialog(this, selectedEntry);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
    }
}
