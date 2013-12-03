package examples.showConfirmDialog;

import javax.swing.JOptionPane;

/**
 * Demonstration of JOptionPane for getting simple yes/no feedback from the user
 * 
 * @author Matt
 * 
 */
public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // show dialog and get result (you can use JOptionPane.YES_NO_CANCEL_OPTION if you'd like the third choice)
        int result = JOptionPane.showConfirmDialog(null, "Please click Yes or No", "Pick!", JOptionPane.YES_NO_OPTION);

        // handle response appropriately
        if (result == JOptionPane.YES_OPTION)
            System.out.println("You clicked yes");
        else if (result == JOptionPane.NO_OPTION)
            System.out.println("You clicked no");
        else
            System.out.println("I have no idea what you clicked!");
    }
}
