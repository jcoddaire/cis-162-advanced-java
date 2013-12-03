package examples.menuCustomLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Simple GUI application with a menu and a label to click on
 * 
 * @author gerberma
 * 
 */
public class GUI implements ActionListener
{
    public static void main(String[] args)
    {
        new GUI();
    }
    
    /**
     * Need a reference to the quit menu item to check if it initiated an action
     */
    private JMenuItem _quitMenuItem;

    /**
     * Constructor
     */
    public GUI()
    {
        // program window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        _quitMenuItem = new JMenuItem("Quit");
        _quitMenuItem.addActionListener(this);

        fileMenu.add(_quitMenuItem);
        menuBar.add(fileMenu);

        // set menu bar on application window
        window.setJMenuBar(menuBar);
        
        // custom JLabel that knows how to draw itself
        CustomLabel label = new CustomLabel();
        window.getContentPane().add(label);

        // show window
        window.setPreferredSize(new Dimension(200, 200));
        window.pack();
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // if the action originated with the quit menu item, exit now.
        if (arg0.getSource() == _quitMenuItem)
            System.exit(0);
    }
}
